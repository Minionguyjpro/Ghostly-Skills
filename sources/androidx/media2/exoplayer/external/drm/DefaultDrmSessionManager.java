package androidx.media2.exoplayer.external.drm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.drm.DefaultDrmSession;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.drm.DrmSession;
import androidx.media2.exoplayer.external.drm.ExoMediaCrypto;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.EventDispatcher;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DefaultDrmSessionManager<T extends ExoMediaCrypto> implements DefaultDrmSession.ProvisioningManager<T>, DrmSessionManager<T> {
    private final MediaDrmCallback callback;
    private final EventDispatcher<DefaultDrmSessionEventListener> eventDispatcher;
    private final int initialDrmRequestRetryCount;
    private final ExoMediaDrm<T> mediaDrm;
    volatile DefaultDrmSessionManager<T>.MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    private byte[] offlineLicenseKeySetId;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private Looper playbackLooper;
    private final List<DefaultDrmSession<T>> provisioningSessions;
    /* access modifiers changed from: private */
    public final List<DefaultDrmSession<T>> sessions;
    private final UUID uuid;

    public static final class MissingSchemeDataException extends Exception {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private MissingSchemeDataException(java.util.UUID r3) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                java.lang.String r0 = java.lang.String.valueOf(r3)
                int r0 = r0.length()
                int r0 = r0 + 29
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Media does not support uuid: "
                r1.append(r0)
                r1.append(r3)
                java.lang.String r3 = r1.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.drm.DefaultDrmSessionManager.MissingSchemeDataException.<init>(java.util.UUID):void");
        }
    }

    public final void addListener(Handler handler, DefaultDrmSessionEventListener defaultDrmSessionEventListener) {
        this.eventDispatcher.addListener(handler, defaultDrmSessionEventListener);
    }

    public boolean canAcquireSession(DrmInitData drmInitData) {
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (getSchemeDatas(drmInitData, this.uuid, true).isEmpty()) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            String valueOf = String.valueOf(this.uuid);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 72);
            sb.append("DrmInitData only contains common PSSH SchemeData. Assuming support for: ");
            sb.append(valueOf);
            Log.w("DefaultDrmSessionMgr", sb.toString());
        }
        String str = drmInitData.schemeType;
        if (str == null || "cenc".equals(str)) {
            return true;
        }
        if (("cbc1".equals(str) || "cbcs".equals(str) || "cens".equals(str)) && Util.SDK_INT < 25) {
            return false;
        }
        return true;
    }

    public DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData) {
        List<DrmInitData.SchemeData> list;
        Looper looper2 = looper;
        Looper looper3 = this.playbackLooper;
        Assertions.checkState(looper3 == null || looper3 == looper2);
        if (this.sessions.isEmpty()) {
            this.playbackLooper = looper2;
            if (this.mediaDrmHandler == null) {
                this.mediaDrmHandler = new MediaDrmHandler(looper2);
            }
        }
        DefaultDrmSession defaultDrmSession = null;
        if (this.offlineLicenseKeySetId == null) {
            List<DrmInitData.SchemeData> schemeDatas = getSchemeDatas(drmInitData, this.uuid, false);
            if (schemeDatas.isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                this.eventDispatcher.dispatch(new DefaultDrmSessionManager$$Lambda$0(missingSchemeDataException));
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException));
            }
            list = schemeDatas;
        } else {
            list = null;
        }
        if (this.multiSession) {
            Iterator<DefaultDrmSession<T>> it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession next = it.next();
                if (Util.areEqual(next.schemeDatas, list)) {
                    defaultDrmSession = next;
                    break;
                }
            }
        } else if (!this.sessions.isEmpty()) {
            defaultDrmSession = this.sessions.get(0);
        }
        if (defaultDrmSession == null) {
            DefaultDrmSession defaultDrmSession2 = new DefaultDrmSession(this.uuid, this.mediaDrm, this, new DefaultDrmSessionManager$$Lambda$1(this), list, this.mode, this.offlineLicenseKeySetId, this.optionalKeyRequestParameters, this.callback, looper, this.eventDispatcher, this.initialDrmRequestRetryCount);
            this.sessions.add(defaultDrmSession2);
            defaultDrmSession = defaultDrmSession2;
        }
        defaultDrmSession.acquire();
        return defaultDrmSession;
    }

    public void releaseSession(DrmSession<T> drmSession) {
        if (!(drmSession instanceof ErrorStateDrmSession)) {
            ((DefaultDrmSession) drmSession).release();
        }
    }

    public void provisionRequired(DefaultDrmSession<T> defaultDrmSession) {
        if (!this.provisioningSessions.contains(defaultDrmSession)) {
            this.provisioningSessions.add(defaultDrmSession);
            if (this.provisioningSessions.size() == 1) {
                defaultDrmSession.provision();
            }
        }
    }

    public void onProvisionCompleted() {
        for (DefaultDrmSession<T> onProvisionCompleted : this.provisioningSessions) {
            onProvisionCompleted.onProvisionCompleted();
        }
        this.provisioningSessions.clear();
    }

    public void onProvisionError(Exception exc) {
        for (DefaultDrmSession<T> onProvisionError : this.provisioningSessions) {
            onProvisionError.onProvisionError(exc);
        }
        this.provisioningSessions.clear();
    }

    /* access modifiers changed from: private */
    /* renamed from: onSessionReleased */
    public void bridge$lambda$0$DefaultDrmSessionManager(DefaultDrmSession<T> defaultDrmSession) {
        this.sessions.remove(defaultDrmSession);
        if (this.provisioningSessions.size() > 1 && this.provisioningSessions.get(0) == defaultDrmSession) {
            this.provisioningSessions.get(1).provision();
        }
        this.provisioningSessions.remove(defaultDrmSession);
    }

    private static List<DrmInitData.SchemeData> getSchemeDatas(DrmInitData drmInitData, UUID uuid2, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            DrmInitData.SchemeData schemeData = drmInitData.get(i);
            if ((schemeData.matches(uuid2) || (C.CLEARKEY_UUID.equals(uuid2) && schemeData.matches(C.COMMON_PSSH_UUID))) && (schemeData.data != null || z)) {
                arrayList.add(schemeData);
            }
        }
        return arrayList;
    }

    private class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            if (bArr != null) {
                for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                    if (defaultDrmSession.hasSessionId(bArr)) {
                        defaultDrmSession.onMediaDrmEvent(message.what);
                        return;
                    }
                }
            }
        }
    }
}
