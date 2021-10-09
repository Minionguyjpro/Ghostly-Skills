package androidx.media2.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.media2.common.ParcelImplListSlice;
import androidx.versionedparcelable.ParcelImpl;
import java.util.List;

public interface IMediaController extends IInterface {
    void onAllowedCommandsChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onBufferingStateChanged(int i, ParcelImpl parcelImpl, int i2, long j, long j2, long j3) throws RemoteException;

    void onChildrenChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException;

    void onConnected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onCurrentMediaItemChanged(int i, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException;

    void onCustomCommand(int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException;

    void onDisconnected(int i) throws RemoteException;

    void onLibraryResult(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onPlaybackCompleted(int i) throws RemoteException;

    void onPlaybackInfoChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException;

    void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException;

    void onPlaylistChanged(int i, ParcelImplListSlice parcelImplListSlice, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException;

    void onPlaylistMetadataChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onSearchResultChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException;

    void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException;

    void onSessionResult(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onSetCustomLayout(int i, List<ParcelImpl> list) throws RemoteException;

    void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onSubtitleData(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3) throws RemoteException;

    void onTrackDeselected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onTrackInfoChanged(int i, List<ParcelImpl> list, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3, ParcelImpl parcelImpl4) throws RemoteException;

    void onTrackSelected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onVideoSizeChanged(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController {
        public IBinder asBinder() {
            return this;
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.media2.session.IMediaController");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaController)) {
                return new Proxy(iBinder);
            }
            return (IMediaController) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r2v1 */
        /* JADX WARNING: type inference failed for: r2v2, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v3 */
        /* JADX WARNING: type inference failed for: r2v8, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v9 */
        /* JADX WARNING: type inference failed for: r2v11, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v15, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v25, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v30, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v38, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v42, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v46, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v50, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v54, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v59, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v63, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v67, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v71 */
        /* JADX WARNING: type inference failed for: r2v72 */
        /* JADX WARNING: type inference failed for: r2v73 */
        /* JADX WARNING: type inference failed for: r2v74 */
        /* JADX WARNING: type inference failed for: r2v75 */
        /* JADX WARNING: type inference failed for: r2v76 */
        /* JADX WARNING: type inference failed for: r2v77 */
        /* JADX WARNING: type inference failed for: r2v78 */
        /* JADX WARNING: type inference failed for: r2v79 */
        /* JADX WARNING: type inference failed for: r2v80 */
        /* JADX WARNING: type inference failed for: r2v81 */
        /* JADX WARNING: type inference failed for: r2v82 */
        /* JADX WARNING: type inference failed for: r2v83 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r15, android.os.Parcel r16, android.os.Parcel r17, int r18) throws android.os.RemoteException {
            /*
                r14 = this;
                r10 = r14
                r0 = r15
                r1 = r16
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r11 = 1
                java.lang.String r3 = "androidx.media2.session.IMediaController"
                if (r0 == r2) goto L_0x036b
                r2 = 0
                switch(r0) {
                    case 1: goto L_0x0340;
                    case 2: goto L_0x0324;
                    case 3: goto L_0x0308;
                    case 4: goto L_0x02d8;
                    case 5: goto L_0x0298;
                    case 6: goto L_0x027e;
                    case 7: goto L_0x0264;
                    case 8: goto L_0x0243;
                    case 9: goto L_0x0222;
                    case 10: goto L_0x0217;
                    case 11: goto L_0x01fb;
                    case 12: goto L_0x01e1;
                    case 13: goto L_0x01d6;
                    case 14: goto L_0x01c5;
                    case 15: goto L_0x01ab;
                    case 16: goto L_0x0181;
                    case 17: goto L_0x0167;
                    case 18: goto L_0x014d;
                    case 19: goto L_0x012b;
                    case 20: goto L_0x0109;
                    case 21: goto L_0x00df;
                    case 22: goto L_0x0083;
                    case 23: goto L_0x0069;
                    case 24: goto L_0x004f;
                    case 25: goto L_0x0015;
                    default: goto L_0x0010;
                }
            L_0x0010:
                boolean r0 = super.onTransact(r15, r16, r17, r18)
                return r0
            L_0x0015:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x002b
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r3 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r3 = (androidx.versionedparcelable.ParcelImpl) r3
                goto L_0x002c
            L_0x002b:
                r3 = r2
            L_0x002c:
                int r4 = r16.readInt()
                if (r4 == 0) goto L_0x003b
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r4 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r4 = (androidx.versionedparcelable.ParcelImpl) r4
                goto L_0x003c
            L_0x003b:
                r4 = r2
            L_0x003c:
                int r5 = r16.readInt()
                if (r5 == 0) goto L_0x004b
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x004b:
                r14.onSubtitleData(r0, r3, r4, r2)
                return r11
            L_0x004f:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x0065
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0065:
                r14.onTrackDeselected(r0, r2)
                return r11
            L_0x0069:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x007f
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x007f:
                r14.onTrackSelected(r0, r2)
                return r11
            L_0x0083:
                r1.enforceInterface(r3)
                int r3 = r16.readInt()
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r4 = r1.createTypedArrayList(r0)
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x00a0
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r5 = r0
                goto L_0x00a1
            L_0x00a0:
                r5 = r2
            L_0x00a1:
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x00b1
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r6 = r0
                goto L_0x00b2
            L_0x00b1:
                r6 = r2
            L_0x00b2:
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x00c2
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r7 = r0
                goto L_0x00c3
            L_0x00c2:
                r7 = r2
            L_0x00c3:
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x00d3
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r8 = r0
                goto L_0x00d4
            L_0x00d3:
                r8 = r2
            L_0x00d4:
                r0 = r14
                r1 = r3
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r7
                r6 = r8
                r0.onTrackInfoChanged(r1, r2, r3, r4, r5, r6)
                return r11
            L_0x00df:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x00f5
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r3 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r3 = (androidx.versionedparcelable.ParcelImpl) r3
                goto L_0x00f6
            L_0x00f5:
                r3 = r2
            L_0x00f6:
                int r4 = r16.readInt()
                if (r4 == 0) goto L_0x0105
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0105:
                r14.onVideoSizeChanged(r0, r3, r2)
                return r11
            L_0x0109:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                java.lang.String r3 = r16.readString()
                int r4 = r16.readInt()
                int r5 = r16.readInt()
                if (r5 == 0) goto L_0x0127
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0127:
                r14.onSearchResultChanged(r0, r3, r4, r2)
                return r11
            L_0x012b:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                java.lang.String r3 = r16.readString()
                int r4 = r16.readInt()
                int r5 = r16.readInt()
                if (r5 == 0) goto L_0x0149
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0149:
                r14.onChildrenChanged(r0, r3, r4, r2)
                return r11
            L_0x014d:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x0163
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0163:
                r14.onLibraryResult(r0, r2)
                return r11
            L_0x0167:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x017d
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x017d:
                r14.onSessionResult(r0, r2)
                return r11
            L_0x0181:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x0197
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r3 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r3 = (androidx.versionedparcelable.ParcelImpl) r3
                goto L_0x0198
            L_0x0197:
                r3 = r2
            L_0x0198:
                int r4 = r16.readInt()
                if (r4 == 0) goto L_0x01a7
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x01a7:
                r14.onCustomCommand(r0, r3, r2)
                return r11
            L_0x01ab:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x01c1
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x01c1:
                r14.onAllowedCommandsChanged(r0, r2)
                return r11
            L_0x01c5:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r1 = r1.createTypedArrayList(r2)
                r14.onSetCustomLayout(r0, r1)
                return r11
            L_0x01d6:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                r14.onDisconnected(r0)
                return r11
            L_0x01e1:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x01f7
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x01f7:
                r14.onConnected(r0, r2)
                return r11
            L_0x01fb:
                r1.enforceInterface(r3)
                int r2 = r16.readInt()
                long r3 = r16.readLong()
                long r5 = r16.readLong()
                long r7 = r16.readLong()
                r0 = r14
                r1 = r2
                r2 = r3
                r4 = r5
                r6 = r7
                r0.onSeekCompleted(r1, r2, r4, r6)
                return r11
            L_0x0217:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                r14.onPlaybackCompleted(r0)
                return r11
            L_0x0222:
                r1.enforceInterface(r3)
                int r2 = r16.readInt()
                int r3 = r16.readInt()
                int r4 = r16.readInt()
                int r5 = r16.readInt()
                int r6 = r16.readInt()
                r0 = r14
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r0.onShuffleModeChanged(r1, r2, r3, r4, r5)
                return r11
            L_0x0243:
                r1.enforceInterface(r3)
                int r2 = r16.readInt()
                int r3 = r16.readInt()
                int r4 = r16.readInt()
                int r5 = r16.readInt()
                int r6 = r16.readInt()
                r0 = r14
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r0.onRepeatModeChanged(r1, r2, r3, r4, r5)
                return r11
            L_0x0264:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x027a
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x027a:
                r14.onPlaybackInfoChanged(r0, r2)
                return r11
            L_0x027e:
                r1.enforceInterface(r3)
                int r0 = r16.readInt()
                int r3 = r16.readInt()
                if (r3 == 0) goto L_0x0294
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0294:
                r14.onPlaylistMetadataChanged(r0, r2)
                return r11
            L_0x0298:
                r1.enforceInterface(r3)
                int r3 = r16.readInt()
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x02af
                android.os.Parcelable$Creator<androidx.media2.common.ParcelImplListSlice> r0 = androidx.media2.common.ParcelImplListSlice.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.media2.common.ParcelImplListSlice r0 = (androidx.media2.common.ParcelImplListSlice) r0
                r4 = r0
                goto L_0x02b0
            L_0x02af:
                r4 = r2
            L_0x02b0:
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x02c0
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r5 = r0
                goto L_0x02c1
            L_0x02c0:
                r5 = r2
            L_0x02c1:
                int r6 = r16.readInt()
                int r7 = r16.readInt()
                int r8 = r16.readInt()
                r0 = r14
                r1 = r3
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r7
                r6 = r8
                r0.onPlaylistChanged(r1, r2, r3, r4, r5, r6)
                return r11
            L_0x02d8:
                r1.enforceInterface(r3)
                int r3 = r16.readInt()
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x02ee
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r2 = r0
            L_0x02ee:
                int r4 = r16.readInt()
                long r5 = r16.readLong()
                long r7 = r16.readLong()
                long r12 = r16.readLong()
                r0 = r14
                r1 = r3
                r3 = r4
                r4 = r5
                r6 = r7
                r8 = r12
                r0.onBufferingStateChanged(r1, r2, r3, r4, r6, r8)
                return r11
            L_0x0308:
                r1.enforceInterface(r3)
                int r2 = r16.readInt()
                long r3 = r16.readLong()
                long r5 = r16.readLong()
                float r7 = r16.readFloat()
                r0 = r14
                r1 = r2
                r2 = r3
                r4 = r5
                r6 = r7
                r0.onPlaybackSpeedChanged(r1, r2, r4, r6)
                return r11
            L_0x0324:
                r1.enforceInterface(r3)
                int r2 = r16.readInt()
                long r3 = r16.readLong()
                long r5 = r16.readLong()
                int r7 = r16.readInt()
                r0 = r14
                r1 = r2
                r2 = r3
                r4 = r5
                r6 = r7
                r0.onPlayerStateChanged(r1, r2, r4, r6)
                return r11
            L_0x0340:
                r1.enforceInterface(r3)
                int r3 = r16.readInt()
                int r0 = r16.readInt()
                if (r0 == 0) goto L_0x0356
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r2 = r0
            L_0x0356:
                int r4 = r16.readInt()
                int r5 = r16.readInt()
                int r6 = r16.readInt()
                r0 = r14
                r1 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r0.onCurrentMediaItemChanged(r1, r2, r3, r4, r5)
                return r11
            L_0x036b:
                r0 = r17
                r0.writeString(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.IMediaController.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaController {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onCurrentMediaItemChanged(int i, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeFloat(f);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onBufferingStateChanged(int i, ParcelImpl parcelImpl, int i2, long j, long j2, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeLong(j3);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaylistChanged(int i, ParcelImplListSlice parcelImplListSlice, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImplListSlice != null) {
                        obtain.writeInt(1);
                        parcelImplListSlice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaylistMetadataChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaybackInfoChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaybackCompleted(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeLong(j3);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onVideoSizeChanged(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(21, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSubtitleData(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl3 != null) {
                        obtain.writeInt(1);
                        parcelImpl3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(25, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onConnected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDisconnected(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSetCustomLayout(int i, List<ParcelImpl> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAllowedCommandsChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomCommand(int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSessionResult(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onLibraryResult(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onTrackInfoChanged(int i, List<ParcelImpl> list, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3, ParcelImpl parcelImpl4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl3 != null) {
                        obtain.writeInt(1);
                        parcelImpl3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl4 != null) {
                        obtain.writeInt(1);
                        parcelImpl4.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onTrackSelected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onTrackDeselected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(24, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onChildrenChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSearchResultChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
