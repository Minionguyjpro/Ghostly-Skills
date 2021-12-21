package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.model.people.PersonBuffer;

final class zzq implements People.LoadPeopleResult {
    private final /* synthetic */ Status zzan;

    zzq(zzp zzp, Status status) {
        this.zzan = status;
    }

    public final String getNextPageToken() {
        return null;
    }

    public final PersonBuffer getPersonBuffer() {
        return null;
    }

    public final Status getStatus() {
        return this.zzan;
    }

    public final void release() {
    }
}
