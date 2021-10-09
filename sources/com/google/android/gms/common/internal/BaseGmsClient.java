package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public abstract class BaseGmsClient<T extends IInterface> {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzd = new Feature[0];
    final Handler zza;
    /* access modifiers changed from: private */
    public ConnectionResult zzaa;
    /* access modifiers changed from: private */
    public boolean zzab;
    private volatile zzc zzac;
    protected ConnectionProgressReportCallbacks zzb;
    protected AtomicInteger zzc;
    private int zze;
    private long zzf;
    private long zzg;
    private int zzh;
    private long zzi;
    private volatile String zzj;
    private zzk zzk;
    private final Context zzl;
    private final Looper zzm;
    private final GmsClientSupervisor zzn;
    private final GoogleApiAvailabilityLight zzo;
    private final Object zzp;
    /* access modifiers changed from: private */
    public final Object zzq;
    /* access modifiers changed from: private */
    public IGmsServiceBroker zzr;
    private T zzs;
    /* access modifiers changed from: private */
    public final ArrayList<zzc<?>> zzt;
    private zzd zzu;
    private int zzv;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzw;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzx;
    private final int zzy;
    private final String zzz;

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface BaseConnectionCallbacks {
        public static final int CAUSE_DEAD_OBJECT_EXCEPTION = 3;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService((IAccountAccessor) null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.zzx != null) {
                BaseGmsClient.this.zzx.onConnectionFailed(connectionResult);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    /* access modifiers changed from: protected */
    public abstract T createServiceInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    public boolean enableLocalFallback() {
        return false;
    }

    public Account getAccount() {
        return null;
    }

    public Bundle getConnectionHint() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getLocalStartServiceAction() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getServiceDescriptor();

    /* access modifiers changed from: protected */
    public abstract String getStartServiceAction();

    /* access modifiers changed from: protected */
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    public boolean getUseDynamicLookup() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onSetConnectState(int i, T t) {
    }

    public boolean providesSignIn() {
        return false;
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean requiresSignIn() {
        return false;
    }

    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    final class zzb extends zzi {
        public zzb(Looper looper) {
            super(looper);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.app.PendingIntent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.util.concurrent.atomic.AtomicInteger r0 = r0.zzc
                int r0 = r0.get()
                int r1 = r8.arg1
                if (r0 == r1) goto L_0x0016
                boolean r0 = zzb(r8)
                if (r0 == 0) goto L_0x0015
                zza(r8)
            L_0x0015:
                return
            L_0x0016:
                int r0 = r8.what
                r1 = 4
                r2 = 1
                r3 = 5
                if (r0 == r2) goto L_0x0032
                int r0 = r8.what
                r4 = 7
                if (r0 == r4) goto L_0x0032
                int r0 = r8.what
                if (r0 != r1) goto L_0x002e
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.enableLocalFallback()
                if (r0 == 0) goto L_0x0032
            L_0x002e:
                int r0 = r8.what
                if (r0 != r3) goto L_0x003e
            L_0x0032:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.isConnecting()
                if (r0 != 0) goto L_0x003e
                zza(r8)
                return
            L_0x003e:
                int r0 = r8.what
                r4 = 8
                r5 = 3
                r6 = 0
                if (r0 != r1) goto L_0x0089
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r1 = new com.google.android.gms.common.ConnectionResult
                int r8 = r8.arg2
                r1.<init>(r8)
                com.google.android.gms.common.ConnectionResult unused = r0.zzaa = r1
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r8 = r8.zzc()
                if (r8 == 0) goto L_0x0068
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r8 = r8.zzab
                if (r8 != 0) goto L_0x0068
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                r8.zza((int) r5, null)
                return
            L_0x0068:
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzaa
                if (r8 == 0) goto L_0x0077
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzaa
                goto L_0x007c
            L_0x0077:
                com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
                r8.<init>(r4)
            L_0x007c:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzb
                r0.onReportServiceBinding(r8)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.onConnectionFailed(r8)
                return
            L_0x0089:
                int r0 = r8.what
                if (r0 != r3) goto L_0x00ae
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzaa
                if (r8 == 0) goto L_0x009c
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzaa
                goto L_0x00a1
            L_0x009c:
                com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
                r8.<init>(r4)
            L_0x00a1:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzb
                r0.onReportServiceBinding(r8)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.onConnectionFailed(r8)
                return
            L_0x00ae:
                int r0 = r8.what
                if (r0 != r5) goto L_0x00d1
                java.lang.Object r0 = r8.obj
                boolean r0 = r0 instanceof android.app.PendingIntent
                if (r0 == 0) goto L_0x00bd
                java.lang.Object r0 = r8.obj
                r6 = r0
                android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            L_0x00bd:
                com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult
                int r8 = r8.arg2
                r0.<init>(r8, r6)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r8 = r8.zzb
                r8.onReportServiceBinding(r0)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                r8.onConnectionFailed(r0)
                return
            L_0x00d1:
                int r0 = r8.what
                r1 = 6
                if (r0 != r1) goto L_0x00fb
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.zza((int) r3, null)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r0 = r0.zzw
                if (r0 == 0) goto L_0x00ee
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r0 = r0.zzw
                int r1 = r8.arg2
                r0.onConnectionSuspended(r1)
            L_0x00ee:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                int r8 = r8.arg2
                r0.onConnectionSuspended(r8)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean unused = r8.zza((int) r3, (int) r2, r6)
                return
            L_0x00fb:
                int r0 = r8.what
                r1 = 2
                if (r0 != r1) goto L_0x010c
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.isConnected()
                if (r0 != 0) goto L_0x010c
                zza(r8)
                return
            L_0x010c:
                boolean r0 = zzb(r8)
                if (r0 == 0) goto L_0x011a
                java.lang.Object r8 = r8.obj
                com.google.android.gms.common.internal.BaseGmsClient$zzc r8 = (com.google.android.gms.common.internal.BaseGmsClient.zzc) r8
                r8.zzc()
                return
            L_0x011a:
                int r8 = r8.what
                r0 = 45
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Don't know how to handle message: "
                r1.append(r0)
                r1.append(r8)
                java.lang.String r8 = r1.toString()
                java.lang.Exception r0 = new java.lang.Exception
                r0.<init>()
                java.lang.String r1 = "GmsClient"
                android.util.Log.wtf(r1, r8, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzb.handleMessage(android.os.Message):void");
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzb();
            zzc.zzd();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    protected final class zzg extends zza {
        public zzg(int i, Bundle bundle) {
            super(i, (Bundle) null);
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (!BaseGmsClient.this.enableLocalFallback() || !BaseGmsClient.this.zzc()) {
                BaseGmsClient.this.zzb.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
                return;
            }
            BaseGmsClient.this.zza(16);
        }

        /* access modifiers changed from: protected */
        public final boolean zza() {
            BaseGmsClient.this.zzb.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    protected abstract class zzc<TListener> {
        private TListener zza;
        private boolean zzb = false;

        public zzc(TListener tlistener) {
            this.zza = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void zzb();

        public final void zzc() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.zza;
                if (this.zzb) {
                    String valueOf = String.valueOf(this);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Callback proxy ");
                    sb.append(valueOf);
                    sb.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb.toString());
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e) {
                    zzb();
                    throw e;
                }
            } else {
                zzb();
            }
            synchronized (this) {
                this.zzb = true;
            }
            zzd();
        }

        public final void zzd() {
            zze();
            synchronized (BaseGmsClient.this.zzt) {
                BaseGmsClient.this.zzt.remove(this);
            }
        }

        public final void zze() {
            synchronized (this) {
                this.zza = null;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public final class zzd implements ServiceConnection {
        private final int zza;

        public zzd(int i) {
            this.zza = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            if (iBinder == null) {
                BaseGmsClient.this.zza(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzq) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new IGmsServiceBroker.Stub.zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                IGmsServiceBroker unused = baseGmsClient.zzr = iGmsServiceBroker;
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zza);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzq) {
                IGmsServiceBroker unused = BaseGmsClient.this.zzr = null;
            }
            BaseGmsClient.this.zza.sendMessage(BaseGmsClient.this.zza.obtainMessage(6, this.zza, 1));
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static final class zze extends IGmsCallbacks.zza {
        private BaseGmsClient zza;
        private final int zzb;

        public zze(BaseGmsClient baseGmsClient, int i) {
            this.zza = baseGmsClient;
            this.zzb = i;
        }

        public final void zza(int i, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.zza, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zza.onPostInitHandler(i, iBinder, bundle, this.zzb);
            this.zza = null;
        }

        public final void zza(int i, IBinder iBinder, zzc zzc) {
            Preconditions.checkNotNull(this.zza, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzc);
            this.zza.zza(zzc);
            onPostInitComplete(i, iBinder, zzc.zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    protected final class zzf extends zza {
        private final IBinder zza;

        public zzf(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zza = iBinder;
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzx != null) {
                BaseGmsClient.this.zzx.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public final boolean zza() {
            try {
                String interfaceDescriptor = this.zza.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    StringBuilder sb = new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length());
                    sb.append("service descriptor mismatch: ");
                    sb.append(serviceDescriptor);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zza);
                if (createServiceInterface == null || (!BaseGmsClient.this.zza(2, 4, createServiceInterface) && !BaseGmsClient.this.zza(3, 4, createServiceInterface))) {
                    return false;
                }
                ConnectionResult unused = BaseGmsClient.this.zzaa = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzw == null) {
                    return true;
                }
                BaseGmsClient.this.zzw.onConnected(connectionHint);
                return true;
            } catch (RemoteException unused2) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    private abstract class zza extends zzc<Boolean> {
        private final int zza;
        private final Bundle zzb;

        protected zza(int i, Bundle bundle) {
            super(true);
            this.zza = i;
            this.zzb = bundle;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zza();

        /* access modifiers changed from: protected */
        public final void zzb() {
        }

        /* JADX WARNING: type inference failed for: r5v11, types: [android.os.Parcelable] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void zza(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                r0 = 1
                r1 = 0
                if (r5 != 0) goto L_0x000c
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                return
            L_0x000c:
                int r5 = r4.zza
                if (r5 == 0) goto L_0x0061
                r2 = 10
                if (r5 == r2) goto L_0x0031
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                android.os.Bundle r5 = r4.zzb
                if (r5 == 0) goto L_0x0026
                java.lang.String r0 = "pendingIntent"
                android.os.Parcelable r5 = r5.getParcelable(r0)
                r1 = r5
                android.app.PendingIntent r1 = (android.app.PendingIntent) r1
            L_0x0026:
                com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
                int r0 = r4.zza
                r5.<init>(r0, r1)
                r4.zza((com.google.android.gms.common.ConnectionResult) r5)
                goto L_0x0076
            L_0x0031:
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                r1 = 3
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r2 = 0
                java.lang.Class r3 = r4.getClass()
                java.lang.String r3 = r3.getSimpleName()
                r1[r2] = r3
                com.google.android.gms.common.internal.BaseGmsClient r2 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.lang.String r2 = r2.getStartServiceAction()
                r1[r0] = r2
                r0 = 2
                com.google.android.gms.common.internal.BaseGmsClient r2 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.lang.String r2 = r2.getServiceDescriptor()
                r1[r0] = r2
                java.lang.String r0 = "A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. "
                java.lang.String r0 = java.lang.String.format(r0, r1)
                r5.<init>(r0)
                throw r5
            L_0x0061:
                boolean r5 = r4.zza()
                if (r5 != 0) goto L_0x0076
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
                r0 = 8
                r5.<init>(r0, r1)
                r4.zza((com.google.android.gms.common.ConnectionResult) r5)
            L_0x0076:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zza.zza(java.lang.Object):void");
        }
    }

    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.zzj = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList<>();
        this.zzv = 1;
        this.zzaa = null;
        this.zzab = false;
        this.zzac = null;
        this.zzc = new AtomicInteger(0);
        this.zzl = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzm = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzn = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzo = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zza = new zzb(looper);
        this.zzy = i;
        this.zzw = baseConnectionCallbacks;
        this.zzx = baseOnConnectionFailedListener;
        this.zzz = str;
    }

    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.zzj = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList<>();
        this.zzv = 1;
        this.zzaa = null;
        this.zzab = false;
        this.zzac = null;
        this.zzc = new AtomicInteger(0);
        this.zzl = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zza = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzm = handler.getLooper();
        this.zzn = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzo = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzy = i;
        this.zzw = baseConnectionCallbacks;
        this.zzx = baseOnConnectionFailedListener;
        this.zzz = null;
    }

    private final String zza() {
        String str = this.zzz;
        return str == null ? this.zzl.getClass().getName() : str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzc zzc2) {
        this.zzac = zzc2;
    }

    public final Feature[] getAvailableFeatures() {
        zzc zzc2 = this.zzac;
        if (zzc2 == null) {
            return null;
        }
        return zzc2.zzb;
    }

    /* access modifiers changed from: protected */
    public void onConnectedLocked(T t) {
        this.zzg = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onConnectionSuspended(int i) {
        this.zze = i;
        this.zzf = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzh = connectionResult.getErrorCode();
        this.zzi = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzk zzk2;
        boolean z = false;
        if ((i == 4) == (t != null)) {
            z = true;
        }
        Preconditions.checkArgument(z);
        synchronized (this.zzp) {
            this.zzv = i;
            this.zzs = t;
            onSetConnectState(i, t);
            if (i != 1) {
                if (i == 2 || i == 3) {
                    if (!(this.zzu == null || this.zzk == null)) {
                        String zza2 = this.zzk.zza();
                        String zzb2 = this.zzk.zzb();
                        StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 70 + String.valueOf(zzb2).length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(zza2);
                        sb.append(" on ");
                        sb.append(zzb2);
                        Log.e("GmsClient", sb.toString());
                        this.zzn.zza(this.zzk.zza(), this.zzk.zzb(), this.zzk.zzc(), this.zzu, zza(), this.zzk.zzd());
                        this.zzc.incrementAndGet();
                    }
                    this.zzu = new zzd(this.zzc.get());
                    if (this.zzv != 3 || getLocalStartServiceAction() == null) {
                        zzk2 = new zzk(getStartServicePackage(), getStartServiceAction(), false, GmsClientSupervisor.getDefaultBindFlags(), getUseDynamicLookup());
                    } else {
                        zzk2 = new zzk(getContext().getPackageName(), getLocalStartServiceAction(), true, GmsClientSupervisor.getDefaultBindFlags(), false);
                    }
                    this.zzk = zzk2;
                    if (!zzk2.zzd() || getMinApkVersion() >= 17895000) {
                        if (!this.zzn.zza(new GmsClientSupervisor.zza(this.zzk.zza(), this.zzk.zzb(), this.zzk.zzc(), this.zzk.zzd()), this.zzu, zza())) {
                            String zza3 = this.zzk.zza();
                            String zzb3 = this.zzk.zzb();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(zza3).length() + 34 + String.valueOf(zzb3).length());
                            sb2.append("unable to connect to service: ");
                            sb2.append(zza3);
                            sb2.append(" on ");
                            sb2.append(zzb3);
                            Log.e("GmsClient", sb2.toString());
                            zza(16, (Bundle) null, this.zzc.get());
                        }
                    } else {
                        String valueOf = String.valueOf(this.zzk.zza());
                        throw new IllegalStateException(valueOf.length() != 0 ? "Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(valueOf) : new String("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: "));
                    }
                } else if (i == 4) {
                    onConnectedLocked(t);
                }
            } else if (this.zzu != null) {
                this.zzn.zza(this.zzk.zza(), this.zzk.zzb(), this.zzk.zzc(), this.zzu, zza(), this.zzk.zzd());
                this.zzu = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        synchronized (this.zzp) {
            if (this.zzv != i) {
                return false;
            }
            zza(i2, t);
            return true;
        }
    }

    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzo.isGooglePlayServicesAvailable(this.zzl, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzb = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzp) {
            z = this.zzv == 4;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzp) {
            if (this.zzv != 2) {
                if (this.zzv != 3) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    private final boolean zzb() {
        boolean z;
        synchronized (this.zzp) {
            z = this.zzv == 3;
        }
        return z;
    }

    public void disconnect(String str) {
        this.zzj = str;
        disconnect();
    }

    public void disconnect() {
        this.zzc.incrementAndGet();
        synchronized (this.zzt) {
            int size = this.zzt.size();
            for (int i = 0; i < size; i++) {
                this.zzt.get(i).zze();
            }
            this.zzt.clear();
        }
        synchronized (this.zzq) {
            this.zzr = null;
        }
        zza(1, (IInterface) null);
    }

    public String getLastDisconnectMessage() {
        return this.zzj;
    }

    public void triggerConnectionSuspended(int i) {
        Handler handler = this.zza;
        handler.sendMessage(handler.obtainMessage(6, this.zzc.get(), i));
    }

    /* access modifiers changed from: private */
    public final void zza(int i) {
        int i2;
        if (zzb()) {
            i2 = 5;
            this.zzab = true;
        } else {
            i2 = 4;
        }
        Handler handler = this.zza;
        handler.sendMessage(handler.obtainMessage(i2, this.zzc.get(), 16));
    }

    /* access modifiers changed from: protected */
    public void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, PendingIntent pendingIntent) {
        this.zzb = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        Handler handler = this.zza;
        handler.sendMessage(handler.obtainMessage(3, this.zzc.get(), i, pendingIntent));
    }

    public final Context getContext() {
        return this.zzl;
    }

    public final Looper getLooper() {
        return this.zzm;
    }

    public Feature[] getApiFeatures() {
        return zzd;
    }

    /* access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    /* access modifiers changed from: protected */
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.zza;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new zzf(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, Bundle bundle, int i2) {
        Handler handler = this.zza;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new zzg(i, (Bundle) null)));
    }

    /* access modifiers changed from: protected */
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.zzp) {
            if (this.zzv != 5) {
                checkConnected();
                Preconditions.checkState(this.zzs != null, "Client is connected but service is null");
                t = this.zzs;
            } else {
                throw new DeadObjectException();
            }
        }
        return t;
    }

    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzy);
        getServiceRequest.zza = this.zzl.getPackageName();
        getServiceRequest.zzd = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzc = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zze = getAccount() != null ? getAccount() : new Account("<<default account>>", AccountType.GOOGLE);
            if (iAccountAccessor != null) {
                getServiceRequest.zzb = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zze = getAccount();
        }
        getServiceRequest.zzf = zzd;
        getServiceRequest.zzg = getApiFeatures();
        try {
            synchronized (this.zzq) {
                if (this.zzr != null) {
                    this.zzr.getService(new zze(this, this.zzc.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(3);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, (IBinder) null, (Bundle) null, this.zzc.get());
        }
    }

    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    public Set<Scope> getScopes() {
        return Collections.emptySet();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.zzp) {
            i = this.zzv;
            t = this.zzs;
        }
        synchronized (this.zzq) {
            iGmsServiceBroker = this.zzr;
        }
        printWriter.append(str).append("mConnectState=");
        if (i == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i == 4) {
            printWriter.print("CONNECTED");
        } else if (i != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzg > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzg;
            String format = simpleDateFormat.format(new Date(this.zzg));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.zzf > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i2 = this.zze;
            if (i2 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i2 == 2) {
                printWriter.append("CAUSE_NETWORK_LOST");
            } else if (i2 != 3) {
                printWriter.append(String.valueOf(i2));
            } else {
                printWriter.append("CAUSE_DEAD_OBJECT_EXCEPTION");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzf;
            String format2 = simpleDateFormat.format(new Date(this.zzf));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.zzi > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzh));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzi;
            String format3 = simpleDateFormat.format(new Date(this.zzi));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzq) {
            if (this.zzr == null) {
                return null;
            }
            IBinder asBinder = this.zzr.asBinder();
            return asBinder;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzc() {
        if (this.zzab || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public String getEndpointPackageName() {
        zzk zzk2;
        if (isConnected() && (zzk2 = this.zzk) != null) {
            return zzk2.zzb();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
