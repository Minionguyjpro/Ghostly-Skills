package com.mopub.nativeads;

import android.content.Context;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubRequestUtils;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import com.mopub.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PositioningRequest extends JsonRequest<MoPubNativeAdPositioning.MoPubClientPositioning> {
    private static final String FIXED_KEY = "fixed";
    private static final String INTERVAL_KEY = "interval";
    private static final int MAX_VALUE = 65536;
    private static final String POSITION_KEY = "position";
    private static final String REPEATING_KEY = "repeating";
    private static final String SECTION_KEY = "section";
    private final Context mContext;
    private final String mOriginalUrl;

    public PositioningRequest(Context context, String str, Response.Listener<MoPubNativeAdPositioning.MoPubClientPositioning> listener, Response.ErrorListener errorListener) {
        super(MoPubRequestUtils.chooseMethod(str), MoPubRequestUtils.truncateQueryParamsIfPost(str), (String) null, listener, errorListener);
        this.mOriginalUrl = str;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        super.deliverResponse(moPubClientPositioning);
    }

    /* access modifiers changed from: protected */
    public Response<MoPubNativeAdPositioning.MoPubClientPositioning> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode != 200) {
            return Response.error(new VolleyError(networkResponse));
        }
        if (networkResponse.data.length == 0) {
            return Response.error(new VolleyError("Empty positioning response", new JSONException("Empty response")));
        }
        try {
            return Response.success(parseJson(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError("Couldn't parse JSON from Charset", e));
        } catch (JSONException e2) {
            return Response.error(new VolleyError("JSON Parsing Error", e2));
        } catch (MoPubNetworkError e3) {
            return Response.error(e3);
        }
    }

    /* access modifiers changed from: package-private */
    public MoPubNativeAdPositioning.MoPubClientPositioning parseJson(String str) throws JSONException, MoPubNetworkError {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("error", (String) null);
        if (optString == null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(FIXED_KEY);
            JSONObject optJSONObject = jSONObject.optJSONObject(REPEATING_KEY);
            if (optJSONArray == null && optJSONObject == null) {
                throw new JSONException("Must contain fixed or repeating positions");
            }
            MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning = new MoPubNativeAdPositioning.MoPubClientPositioning();
            if (optJSONArray != null) {
                parseFixedJson(optJSONArray, moPubClientPositioning);
            }
            if (optJSONObject != null) {
                parseRepeatingJson(optJSONObject, moPubClientPositioning);
            }
            return moPubClientPositioning;
        } else if (optString.equalsIgnoreCase("WARMING_UP")) {
            throw new MoPubNetworkError(MoPubNetworkError.Reason.WARMING_UP);
        } else {
            throw new JSONException(optString);
        }
    }

    private void parseFixedJson(JSONArray jSONArray, MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) throws JSONException {
        int i = 0;
        while (i < jSONArray.length()) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int optInt = jSONObject.optInt(SECTION_KEY, 0);
            if (optInt >= 0) {
                if (optInt <= 0) {
                    int i2 = jSONObject.getInt(POSITION_KEY);
                    if (i2 < 0 || i2 > MAX_VALUE) {
                        throw new JSONException("Invalid position " + i2 + " in JSON response");
                    }
                    moPubClientPositioning.addFixedPosition(i2);
                }
                i++;
            } else {
                throw new JSONException("Invalid section " + optInt + " in JSON response");
            }
        }
    }

    private void parseRepeatingJson(JSONObject jSONObject, MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) throws JSONException {
        int i = jSONObject.getInt("interval");
        if (i < 2 || i > MAX_VALUE) {
            throw new JSONException("Invalid interval " + i + " in JSON response");
        }
        moPubClientPositioning.enableRepeatingPositions(i);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() {
        if (!MoPubRequestUtils.isMoPubRequest(getUrl())) {
            return null;
        }
        return MoPubRequestUtils.convertQueryToMap(this.mContext, this.mOriginalUrl);
    }

    public byte[] getBody() {
        String generateBodyFromParams = MoPubRequestUtils.generateBodyFromParams(getParams(), getUrl());
        if (generateBodyFromParams == null) {
            return null;
        }
        return generateBodyFromParams.getBytes();
    }
}
