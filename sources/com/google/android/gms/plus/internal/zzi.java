package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.model.people.PersonBuffer;

final class zzi implements People.LoadPeopleResult {
    private final Status mStatus;
    private final String zzt;
    private final PersonBuffer zzu;

    public zzi(Status status, DataHolder dataHolder, String str) {
        this.mStatus = status;
        this.zzt = str;
        this.zzu = dataHolder != null ? new PersonBuffer(dataHolder) : null;
    }

    public final String getNextPageToken() {
        return this.zzt;
    }

    public final PersonBuffer getPersonBuffer() {
        return this.zzu;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final void release() {
        PersonBuffer personBuffer = this.zzu;
        if (personBuffer != null) {
            personBuffer.release();
        }
    }
}
