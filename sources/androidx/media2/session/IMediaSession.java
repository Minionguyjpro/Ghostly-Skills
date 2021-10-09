package androidx.media2.session;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import androidx.versionedparcelable.ParcelImpl;
import java.util.List;

public interface IMediaSession extends IInterface {
    void addPlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException;

    void adjustVolume(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void connect(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void deselectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void fastForward(IMediaController iMediaController, int i) throws RemoteException;

    void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException;

    void getItem(IMediaController iMediaController, int i, String str) throws RemoteException;

    void getLibraryRoot(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException;

    void onControllerResult(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void onCustomCommand(IMediaController iMediaController, int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException;

    void pause(IMediaController iMediaController, int i) throws RemoteException;

    void play(IMediaController iMediaController, int i) throws RemoteException;

    void playFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void playFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void playFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException;

    void prepare(IMediaController iMediaController, int i) throws RemoteException;

    void prepareFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException;

    void release(IMediaController iMediaController, int i) throws RemoteException;

    void removePlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void replacePlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException;

    void rewind(IMediaController iMediaController, int i) throws RemoteException;

    void search(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException;

    void selectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void setMediaItem(IMediaController iMediaController, int i, String str) throws RemoteException;

    void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException;

    void setPlaylist(IMediaController iMediaController, int i, List<String> list, ParcelImpl parcelImpl) throws RemoteException;

    void setRating(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setShuffleMode(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException;

    void setVolumeTo(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void skipBackward(IMediaController iMediaController, int i) throws RemoteException;

    void skipForward(IMediaController iMediaController, int i) throws RemoteException;

    void skipToNextItem(IMediaController iMediaController, int i) throws RemoteException;

    void skipToPlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void skipToPreviousItem(IMediaController iMediaController, int i) throws RemoteException;

    void subscribe(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException;

    void updatePlaylistMetadata(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        public IBinder asBinder() {
            return this;
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.media2.session.IMediaSession");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v33, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v37, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v41, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v44, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v47, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v50 */
        /* JADX WARNING: type inference failed for: r0v53, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v57 */
        /* JADX WARNING: type inference failed for: r0v60, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v64, types: [android.view.Surface] */
        /* JADX WARNING: type inference failed for: r0v67, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v70, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v73 */
        /* JADX WARNING: type inference failed for: r0v74 */
        /* JADX WARNING: type inference failed for: r0v75 */
        /* JADX WARNING: type inference failed for: r0v76 */
        /* JADX WARNING: type inference failed for: r0v77 */
        /* JADX WARNING: type inference failed for: r0v78 */
        /* JADX WARNING: type inference failed for: r0v79 */
        /* JADX WARNING: type inference failed for: r0v80 */
        /* JADX WARNING: type inference failed for: r0v81 */
        /* JADX WARNING: type inference failed for: r0v82 */
        /* JADX WARNING: type inference failed for: r0v83 */
        /* JADX WARNING: type inference failed for: r0v84 */
        /* JADX WARNING: type inference failed for: r0v85 */
        /* JADX WARNING: type inference failed for: r0v86 */
        /* JADX WARNING: type inference failed for: r0v87 */
        /* JADX WARNING: type inference failed for: r0v88 */
        /* JADX WARNING: type inference failed for: r0v89 */
        /* JADX WARNING: type inference failed for: r0v90 */
        /* JADX WARNING: type inference failed for: r0v91 */
        /* JADX WARNING: type inference failed for: r0v92 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
            /*
                r9 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "androidx.media2.session.IMediaSession"
                if (r10 == r0) goto L_0x051e
                r0 = 0
                switch(r10) {
                    case 1: goto L_0x04fc;
                    case 2: goto L_0x04e9;
                    case 3: goto L_0x04ce;
                    case 4: goto L_0x04b3;
                    case 5: goto L_0x04a0;
                    case 6: goto L_0x048d;
                    case 7: goto L_0x047a;
                    case 8: goto L_0x0467;
                    case 9: goto L_0x0454;
                    case 10: goto L_0x0441;
                    case 11: goto L_0x042e;
                    case 12: goto L_0x0417;
                    case 13: goto L_0x03e5;
                    case 14: goto L_0x03b3;
                    case 15: goto L_0x038d;
                    case 16: goto L_0x0367;
                    case 17: goto L_0x0335;
                    case 18: goto L_0x030f;
                    case 19: goto L_0x02e9;
                    case 20: goto L_0x02c3;
                    case 21: goto L_0x02ac;
                    case 22: goto L_0x0286;
                    case 23: goto L_0x026f;
                    case 24: goto L_0x024d;
                    case 25: goto L_0x0232;
                    case 26: goto L_0x021b;
                    case 27: goto L_0x0200;
                    case 28: goto L_0x01e9;
                    case 29: goto L_0x01d6;
                    case 30: goto L_0x01c3;
                    case 31: goto L_0x01ac;
                    case 32: goto L_0x0195;
                    case 33: goto L_0x0173;
                    case 34: goto L_0x0151;
                    case 35: goto L_0x013a;
                    case 36: goto L_0x010a;
                    case 37: goto L_0x00e4;
                    case 38: goto L_0x00b4;
                    case 39: goto L_0x008e;
                    case 40: goto L_0x0077;
                    case 41: goto L_0x0055;
                    case 42: goto L_0x0033;
                    case 43: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r10 = super.onTransact(r10, r11, r12, r13)
                return r10
            L_0x0011:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x002f
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x002f:
                r9.deselectTrack(r10, r12, r0)
                return r1
            L_0x0033:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x0051
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x0051:
                r9.selectTrack(r10, r12, r0)
                return r1
            L_0x0055:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x0073
                android.os.Parcelable$Creator r13 = android.view.Surface.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                android.view.Surface r0 = (android.view.Surface) r0
            L_0x0073:
                r9.setSurface(r10, r12, r0)
                return r1
            L_0x0077:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r11 = r11.readString()
                r9.unsubscribe(r10, r12, r11)
                return r1
            L_0x008e:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x00b0
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x00b0:
                r9.subscribe(r10, r12, r13, r0)
                return r1
            L_0x00b4:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r3 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r4 = r11.readInt()
                java.lang.String r5 = r11.readString()
                int r6 = r11.readInt()
                int r7 = r11.readInt()
                int r10 = r11.readInt()
                if (r10 == 0) goto L_0x00de
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r10 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r10.createFromParcel(r11)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x00de:
                r8 = r0
                r2 = r9
                r2.getSearchResult(r3, r4, r5, r6, r7, r8)
                return r1
            L_0x00e4:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x0106
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x0106:
                r9.search(r10, r12, r13, r0)
                return r1
            L_0x010a:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r3 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r4 = r11.readInt()
                java.lang.String r5 = r11.readString()
                int r6 = r11.readInt()
                int r7 = r11.readInt()
                int r10 = r11.readInt()
                if (r10 == 0) goto L_0x0134
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r10 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r10.createFromParcel(r11)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x0134:
                r8 = r0
                r2 = r9
                r2.getChildren(r3, r4, r5, r6, r7, r8)
                return r1
            L_0x013a:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r11 = r11.readString()
                r9.getItem(r10, r12, r11)
                return r1
            L_0x0151:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x016f
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x016f:
                r9.getLibraryRoot(r10, r12, r0)
                return r1
            L_0x0173:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x0191
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x0191:
                r9.onControllerResult(r10, r12, r0)
                return r1
            L_0x0195:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r11 = r11.readInt()
                r9.setShuffleMode(r10, r12, r11)
                return r1
            L_0x01ac:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r11 = r11.readInt()
                r9.setRepeatMode(r10, r12, r11)
                return r1
            L_0x01c3:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.skipToNextItem(r10, r11)
                return r1
            L_0x01d6:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.skipToPreviousItem(r10, r11)
                return r1
            L_0x01e9:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r11 = r11.readInt()
                r9.skipToPlaylistItem(r10, r12, r11)
                return r1
            L_0x0200:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                java.lang.String r11 = r11.readString()
                r9.replacePlaylistItem(r10, r12, r13, r11)
                return r1
            L_0x021b:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r11 = r11.readInt()
                r9.removePlaylistItem(r10, r12, r11)
                return r1
            L_0x0232:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                java.lang.String r11 = r11.readString()
                r9.addPlaylistItem(r10, r12, r13, r11)
                return r1
            L_0x024d:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x026b
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x026b:
                r9.updatePlaylistMetadata(r10, r12, r0)
                return r1
            L_0x026f:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r11 = r11.readString()
                r9.setMediaItem(r10, r12, r11)
                return r1
            L_0x0286:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.util.ArrayList r13 = r11.createStringArrayList()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x02a8
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x02a8:
                r9.setPlaylist(r10, r12, r13, r0)
                return r1
            L_0x02ac:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                float r11 = r11.readFloat()
                r9.setPlaybackSpeed(r10, r12, r11)
                return r1
            L_0x02c3:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x02e5
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x02e5:
                r9.setRating(r10, r12, r13, r0)
                return r1
            L_0x02e9:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x030b
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x030b:
                r9.playFromMediaId(r10, r12, r13, r0)
                return r1
            L_0x030f:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x0331
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0331:
                r9.playFromSearch(r10, r12, r13, r0)
                return r1
            L_0x0335:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x0353
                android.os.Parcelable$Creator r13 = android.net.Uri.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r11)
                android.net.Uri r13 = (android.net.Uri) r13
                goto L_0x0354
            L_0x0353:
                r13 = r0
            L_0x0354:
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x0363
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0363:
                r9.playFromUri(r10, r12, r13, r0)
                return r1
            L_0x0367:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x0389
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0389:
                r9.prepareFromMediaId(r10, r12, r13, r0)
                return r1
            L_0x038d:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                java.lang.String r13 = r11.readString()
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x03af
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x03af:
                r9.prepareFromSearch(r10, r12, r13, r0)
                return r1
            L_0x03b3:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x03d1
                android.os.Parcelable$Creator r13 = android.net.Uri.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r11)
                android.net.Uri r13 = (android.net.Uri) r13
                goto L_0x03d2
            L_0x03d1:
                r13 = r0
            L_0x03d2:
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x03e1
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x03e1:
                r9.prepareFromUri(r10, r12, r13, r0)
                return r1
            L_0x03e5:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x0403
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r11)
                androidx.versionedparcelable.ParcelImpl r13 = (androidx.versionedparcelable.ParcelImpl) r13
                goto L_0x0404
            L_0x0403:
                r13 = r0
            L_0x0404:
                int r2 = r11.readInt()
                if (r2 == 0) goto L_0x0413
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r0.createFromParcel(r11)
                r0 = r11
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0413:
                r9.onCustomCommand(r10, r12, r13, r0)
                return r1
            L_0x0417:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                long r2 = r11.readLong()
                r9.seekTo(r10, r12, r2)
                return r1
            L_0x042e:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.skipBackward(r10, r11)
                return r1
            L_0x0441:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.skipForward(r10, r11)
                return r1
            L_0x0454:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.rewind(r10, r11)
                return r1
            L_0x0467:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.fastForward(r10, r11)
                return r1
            L_0x047a:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.prepare(r10, r11)
                return r1
            L_0x048d:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.pause(r10, r11)
                return r1
            L_0x04a0:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.play(r10, r11)
                return r1
            L_0x04b3:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                int r11 = r11.readInt()
                r9.adjustVolume(r10, r12, r13, r11)
                return r1
            L_0x04ce:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                int r11 = r11.readInt()
                r9.setVolumeTo(r10, r12, r13, r11)
                return r1
            L_0x04e9:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r11 = r11.readInt()
                r9.release(r10, r11)
                return r1
            L_0x04fc:
                r11.enforceInterface(r2)
                android.os.IBinder r10 = r11.readStrongBinder()
                androidx.media2.session.IMediaController r10 = androidx.media2.session.IMediaController.Stub.asInterface(r10)
                int r12 = r11.readInt()
                int r13 = r11.readInt()
                if (r13 == 0) goto L_0x051a
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r13 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r13.createFromParcel(r11)
                r0 = r11
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x051a:
                r9.connect(r10, r12, r0)
                return r1
            L_0x051e:
                r12.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.IMediaSession.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaSession {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void connect(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void release(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setVolumeTo(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void adjustVolume(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void play(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void pause(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepare(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void fastForward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void rewind(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipForward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipBackward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomCommand(IMediaController iMediaController, int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
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
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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

            public void playFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void playFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void playFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRating(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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

            public void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeFloat(f);
                    this.mRemote.transact(21, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlaylist(IMediaController iMediaController, int i, List<String> list, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItem(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void updatePlaylistMetadata(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
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

            public void addPlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(25, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void removePlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(26, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void replacePlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(27, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToPlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(28, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToPreviousItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(29, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToNextItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(30, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(31, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setShuffleMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(32, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(41, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void selectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(42, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void deselectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(43, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onControllerResult(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(33, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getLibraryRoot(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getItem(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(35, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void search(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(37, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(38, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void subscribe(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(40, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
