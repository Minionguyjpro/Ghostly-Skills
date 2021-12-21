package com.mopub.nativeads;

import com.mopub.mobileads.MoPubError;

public enum NativeErrorCode implements MoPubError {
    AD_SUCCESS("ad successfully loaded."),
    EMPTY_AD_RESPONSE("Server returned empty response."),
    INVALID_RESPONSE("Unable to parse response from server."),
    IMAGE_DOWNLOAD_FAILURE("Unable to download images associated with ad."),
    INVALID_REQUEST_URL("Invalid request url."),
    UNEXPECTED_RESPONSE_CODE("Received unexpected response code from server."),
    SERVER_ERROR_RESPONSE_CODE("Server returned erroneous response code."),
    CONNECTION_ERROR("Network is unavailable."),
    UNSPECIFIED("Unspecified error occurred."),
    NETWORK_INVALID_REQUEST("Third-party network received invalid request."),
    NETWORK_TIMEOUT("Third-party network failed to respond in a timely manner."),
    NETWORK_NO_FILL("Third-party network failed to provide an ad."),
    NETWORK_INVALID_STATE("Third-party network failed due to invalid internal state."),
    NATIVE_RENDERER_CONFIGURATION_ERROR("A required renderer was not registered for the CustomEventNative."),
    NATIVE_ADAPTER_CONFIGURATION_ERROR("CustomEventNative was configured incorrectly."),
    NATIVE_ADAPTER_NOT_FOUND("Unable to find CustomEventNative.");
    
    private final String message;

    private NativeErrorCode(String str) {
        this.message = str;
    }

    public final String toString() {
        return this.message;
    }

    /* renamed from: com.mopub.nativeads.NativeErrorCode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeErrorCode = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.mopub.nativeads.NativeErrorCode[] r0 = com.mopub.nativeads.NativeErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$NativeErrorCode = r0
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.NETWORK_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.AD_SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.NativeErrorCode.AnonymousClass1.<clinit>():void");
        }
    }

    public int getIntCode() {
        int i = AnonymousClass1.$SwitchMap$com$mopub$nativeads$NativeErrorCode[ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 10000 : 0;
        }
        return 1;
    }
}
