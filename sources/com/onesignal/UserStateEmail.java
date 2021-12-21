package com.onesignal;

import com.google.android.gms.common.Scopes;

class UserStateEmail extends UserState {
    /* access modifiers changed from: protected */
    public void addDependFields() {
    }

    /* access modifiers changed from: package-private */
    public boolean isSubscribed() {
        return true;
    }

    UserStateEmail(String str, boolean z) {
        super(Scopes.EMAIL + str, z);
    }

    /* access modifiers changed from: package-private */
    public UserState newInstance(String str) {
        return new UserStateEmail(str, false);
    }
}
