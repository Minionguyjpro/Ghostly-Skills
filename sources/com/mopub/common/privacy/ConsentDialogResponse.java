package com.mopub.common.privacy;

import com.mopub.common.Preconditions;

class ConsentDialogResponse {
    private final String mHtml;

    ConsentDialogResponse(String str) {
        Preconditions.checkNotNull(str);
        this.mHtml = str;
    }

    public String getHtml() {
        return this.mHtml;
    }
}
