package com.mopub.network;

import android.net.Uri;
import com.mopub.common.ClientMetadata;
import com.mopub.common.MoPub;
import com.mopub.common.privacy.AdvertisingId;
import com.mopub.volley.toolbox.HurlStack;

public class PlayServicesUrlRewriter implements HurlStack.UrlRewriter {
    public static final String DO_NOT_TRACK_TEMPLATE = "mp_tmpl_do_not_track";
    public static final String MOPUB_ID_TEMPLATE = "mp_tmpl_mopub_id";
    public static final String UDID_TEMPLATE = "mp_tmpl_advertising_id";

    public String rewriteUrl(String str) {
        ClientMetadata instance;
        if ((!str.contains(UDID_TEMPLATE) && !str.contains(DO_NOT_TRACK_TEMPLATE)) || (instance = ClientMetadata.getInstance()) == null) {
            return str;
        }
        AdvertisingId advertisingInfo = instance.getMoPubIdentifier().getAdvertisingInfo();
        return str.replace(UDID_TEMPLATE, Uri.encode(advertisingInfo.getIdWithPrefix(MoPub.canCollectPersonalInformation()))).replace(DO_NOT_TRACK_TEMPLATE, advertisingInfo.isDoNotTrack() ? "1" : "0").replace(MOPUB_ID_TEMPLATE, Uri.encode(advertisingInfo.getIdentifier(false)));
    }
}
