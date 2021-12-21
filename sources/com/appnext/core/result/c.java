package com.appnext.core.result;

import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.p;
import org.json.JSONException;
import org.json.JSONObject;

public interface c {
    String A();

    String B();

    p C();

    Ad D();

    a E();

    JSONObject getConfigParams() throws JSONException;

    String getPlacementId();

    AppnextAd getSelectedAd();

    String z();
}
