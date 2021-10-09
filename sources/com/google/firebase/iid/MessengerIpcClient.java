package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.appnext.base.b.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.p000firebaseiid.zza;
import com.google.android.gms.internal.p000firebaseiid.zze;
import com.google.android.gms.internal.p000firebaseiid.zzf;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public class MessengerIpcClient {
    private static MessengerIpcClient instance;
    private Connection connection = new Connection();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService executor;
    private int nextRequestId = 1;

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    public static class RequestFailedException extends Exception {
        private final int errorCode;

        public RequestFailedException(int i, String str) {
            super(str);
            this.errorCode = i;
        }
    }

    public static synchronized MessengerIpcClient getInstance(Context context2) {
        MessengerIpcClient messengerIpcClient;
        synchronized (MessengerIpcClient.class) {
            if (instance == null) {
                instance = new MessengerIpcClient(context2, zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), zzf.zza));
            }
            messengerIpcClient = instance;
        }
        return messengerIpcClient;
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    private static class OneWayRequest extends Request<Void> {
        OneWayRequest(int i, int i2, Bundle bundle) {
            super(i, i2, bundle);
        }

        /* access modifiers changed from: package-private */
        public boolean isOneWay() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public void handleResponseInternal(Bundle bundle) {
            if (bundle.getBoolean("ack", false)) {
                finish(null);
            } else {
                fail(new RequestFailedException(4, "Invalid response to one way request"));
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    private static class TwoWayRequest extends Request<Bundle> {
        TwoWayRequest(int i, int i2, Bundle bundle) {
            super(i, i2, bundle);
        }

        /* access modifiers changed from: package-private */
        public boolean isOneWay() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void handleResponseInternal(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(c.DATA);
            if (bundle2 == null) {
                bundle2 = Bundle.EMPTY;
            }
            finish(bundle2);
        }
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    private class Connection implements ServiceConnection {
        final Messenger appMessenger;
        MessengerWrapper gmsCoreMessenger;
        final Queue<Request<?>> requestsToBeSent;
        final SparseArray<Request<?>> requestsWaitingForResponse;
        int state;

        private Connection() {
            this.state = 0;
            this.appMessenger = new Messenger(new zze(Looper.getMainLooper(), new MessengerIpcClient$Connection$$Lambda$0(this)));
            this.requestsToBeSent = new ArrayDeque();
            this.requestsWaitingForResponse = new SparseArray<>();
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean enqueueRequest(Request<?> request) {
            int i = this.state;
            if (i == 0) {
                this.requestsToBeSent.add(request);
                startConnection();
                return true;
            } else if (i == 1) {
                this.requestsToBeSent.add(request);
                return true;
            } else if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        int i2 = this.state;
                        StringBuilder sb = new StringBuilder(26);
                        sb.append("Unknown state: ");
                        sb.append(i2);
                        throw new IllegalStateException(sb.toString());
                    }
                }
                return false;
            } else {
                this.requestsToBeSent.add(request);
                scheduleSendingRequests();
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void startConnection() {
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (!ConnectionTracker.getInstance().bindService(MessengerIpcClient.this.context, intent, this, 1)) {
                handleDisconnect(0, "Unable to bind to service");
            } else {
                MessengerIpcClient.this.executor.schedule(new MessengerIpcClient$Connection$$Lambda$1(this), 30, TimeUnit.SECONDS);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean receivedResponse(Message message) {
            int i = message.arg1;
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("Received response to request: ");
                sb.append(i);
                Log.d("MessengerIpcClient", sb.toString());
            }
            synchronized (this) {
                Request request = this.requestsWaitingForResponse.get(i);
                if (request == null) {
                    StringBuilder sb2 = new StringBuilder(50);
                    sb2.append("Received response for unknown request: ");
                    sb2.append(i);
                    Log.w("MessengerIpcClient", sb2.toString());
                    return true;
                }
                this.requestsWaitingForResponse.remove(i);
                unbindIfFinished();
                request.handleResponse(message.getData());
                return true;
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Service connected");
            }
            MessengerIpcClient.this.executor.execute(new MessengerIpcClient$Connection$$Lambda$2(this, iBinder));
        }

        /* access modifiers changed from: package-private */
        public void scheduleSendingRequests() {
            MessengerIpcClient.this.executor.execute(new MessengerIpcClient$Connection$$Lambda$3(this));
        }

        /* access modifiers changed from: package-private */
        public void sendRequestOverMessenger(Request<?> request) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String valueOf = String.valueOf(request);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
                sb.append("Sending ");
                sb.append(valueOf);
                Log.d("MessengerIpcClient", sb.toString());
            }
            try {
                this.gmsCoreMessenger.send(request.createMessage(MessengerIpcClient.this.context, this.appMessenger));
            } catch (RemoteException e) {
                handleDisconnect(2, e.getMessage());
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Service disconnected");
            }
            MessengerIpcClient.this.executor.execute(new MessengerIpcClient$Connection$$Lambda$4(this));
        }

        /* access modifiers changed from: package-private */
        public synchronized void handleDisconnect(int i, String str) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String valueOf = String.valueOf(str);
                Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
            }
            int i2 = this.state;
            if (i2 == 0) {
                throw new IllegalStateException();
            } else if (i2 == 1 || i2 == 2) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(MessengerIpcClient.this.context, this);
                failAllPendingReqests(new RequestFailedException(i, str));
            } else if (i2 == 3) {
                this.state = 4;
            } else if (i2 != 4) {
                int i3 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            }
        }

        /* access modifiers changed from: package-private */
        public void failAllPendingReqests(RequestFailedException requestFailedException) {
            for (Request fail : this.requestsToBeSent) {
                fail.fail(requestFailedException);
            }
            this.requestsToBeSent.clear();
            for (int i = 0; i < this.requestsWaitingForResponse.size(); i++) {
                this.requestsWaitingForResponse.valueAt(i).fail(requestFailedException);
            }
            this.requestsWaitingForResponse.clear();
        }

        /* access modifiers changed from: package-private */
        public synchronized void unbindIfFinished() {
            if (this.state == 2 && this.requestsToBeSent.isEmpty() && this.requestsWaitingForResponse.size() == 0) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
                }
                this.state = 3;
                ConnectionTracker.getInstance().unbindService(MessengerIpcClient.this.context, this);
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void timeoutConnection() {
            if (this.state == 1) {
                handleDisconnect(1, "Timed out while binding");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void timeoutRequest(int i) {
            Request request = this.requestsWaitingForResponse.get(i);
            if (request != null) {
                StringBuilder sb = new StringBuilder(31);
                sb.append("Timing out request: ");
                sb.append(i);
                Log.w("MessengerIpcClient", sb.toString());
                this.requestsWaitingForResponse.remove(i);
                request.fail(new RequestFailedException(3, "Timed out waiting for response"));
                unbindIfFinished();
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onServiceDisconnected$3$MessengerIpcClient$Connection() {
            handleDisconnect(2, "Service disconnected");
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$scheduleSendingRequests$2$MessengerIpcClient$Connection() {
            Request poll;
            while (true) {
                synchronized (this) {
                    if (this.state == 2) {
                        if (this.requestsToBeSent.isEmpty()) {
                            unbindIfFinished();
                            return;
                        }
                        poll = this.requestsToBeSent.poll();
                        this.requestsWaitingForResponse.put(poll.requestId, poll);
                        MessengerIpcClient.this.executor.schedule(new MessengerIpcClient$Connection$$Lambda$5(this, poll), 30, TimeUnit.SECONDS);
                    } else {
                        return;
                    }
                }
                sendRequestOverMessenger(poll);
            }
            while (true) {
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$scheduleSendingRequests$1$MessengerIpcClient$Connection(Request request) {
            timeoutRequest(request.requestId);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x001d, code lost:
            handleDisconnect(0, r3.getMessage());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0025, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0027, code lost:
            throw r3;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0006, B:8:0x000b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void lambda$onServiceConnected$0$MessengerIpcClient$Connection(android.os.IBinder r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                r0 = 0
                if (r3 != 0) goto L_0x000b
                java.lang.String r3 = "Null service connection"
                r2.handleDisconnect(r0, r3)     // Catch:{ all -> 0x001a }
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                return
            L_0x000b:
                com.google.firebase.iid.MessengerIpcClient$MessengerWrapper r1 = new com.google.firebase.iid.MessengerIpcClient$MessengerWrapper     // Catch:{ RemoteException -> 0x001c }
                r1.<init>(r3)     // Catch:{ RemoteException -> 0x001c }
                r2.gmsCoreMessenger = r1     // Catch:{ RemoteException -> 0x001c }
                r3 = 2
                r2.state = r3     // Catch:{ all -> 0x001a }
                r2.scheduleSendingRequests()     // Catch:{ all -> 0x001a }
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                return
            L_0x001a:
                r3 = move-exception
                goto L_0x0026
            L_0x001c:
                r3 = move-exception
                java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x001a }
                r2.handleDisconnect(r0, r3)     // Catch:{ all -> 0x001a }
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                return
            L_0x0026:
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.MessengerIpcClient.Connection.lambda$onServiceConnected$0$MessengerIpcClient$Connection(android.os.IBinder):void");
        }
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    private static abstract class Request<T> {
        final Bundle data;
        final int requestId;
        final TaskCompletionSource<T> taskCompletionSource = new TaskCompletionSource<>();
        final int what;

        Request(int i, int i2, Bundle bundle) {
            this.requestId = i;
            this.what = i2;
            this.data = bundle;
        }

        /* access modifiers changed from: package-private */
        public abstract void handleResponseInternal(Bundle bundle);

        /* access modifiers changed from: package-private */
        public abstract boolean isOneWay();

        /* access modifiers changed from: package-private */
        public Message createMessage(Context context, Messenger messenger) {
            Message obtain = Message.obtain();
            obtain.what = this.what;
            obtain.arg1 = this.requestId;
            obtain.replyTo = messenger;
            Bundle bundle = new Bundle();
            bundle.putBoolean("oneWay", isOneWay());
            bundle.putString("pkg", context.getPackageName());
            bundle.putBundle(c.DATA, this.data);
            obtain.setData(bundle);
            return obtain;
        }

        /* access modifiers changed from: package-private */
        public Task<T> getTask() {
            return this.taskCompletionSource.getTask();
        }

        /* access modifiers changed from: package-private */
        public void finish(T t) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String valueOf = String.valueOf(this);
                String valueOf2 = String.valueOf(t);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
                sb.append("Finishing ");
                sb.append(valueOf);
                sb.append(" with ");
                sb.append(valueOf2);
                Log.d("MessengerIpcClient", sb.toString());
            }
            this.taskCompletionSource.setResult(t);
        }

        /* access modifiers changed from: package-private */
        public void fail(RequestFailedException requestFailedException) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String valueOf = String.valueOf(this);
                String valueOf2 = String.valueOf(requestFailedException);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
                sb.append("Failing ");
                sb.append(valueOf);
                sb.append(" with ");
                sb.append(valueOf2);
                Log.d("MessengerIpcClient", sb.toString());
            }
            this.taskCompletionSource.setException(requestFailedException);
        }

        /* access modifiers changed from: package-private */
        public void handleResponse(Bundle bundle) {
            if (bundle.getBoolean("unsupported", false)) {
                fail(new RequestFailedException(4, "Not supported by GmsCore"));
            } else {
                handleResponseInternal(bundle);
            }
        }

        public String toString() {
            int i = this.what;
            int i2 = this.requestId;
            boolean isOneWay = isOneWay();
            StringBuilder sb = new StringBuilder(55);
            sb.append("Request { what=");
            sb.append(i);
            sb.append(" id=");
            sb.append(i2);
            sb.append(" oneWay=");
            sb.append(isOneWay);
            sb.append("}");
            return sb.toString();
        }
    }

    MessengerIpcClient(Context context2, ScheduledExecutorService scheduledExecutorService) {
        this.executor = scheduledExecutorService;
        this.context = context2.getApplicationContext();
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    static class MessengerWrapper {
        private final Messenger messenger;
        private final FirebaseIidMessengerCompat messengerCompat;

        MessengerWrapper(IBinder iBinder) throws RemoteException {
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if ("android.os.IMessenger".equals(interfaceDescriptor)) {
                this.messenger = new Messenger(iBinder);
                this.messengerCompat = null;
            } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
                this.messengerCompat = new FirebaseIidMessengerCompat(iBinder);
                this.messenger = null;
            } else {
                String valueOf = String.valueOf(interfaceDescriptor);
                Log.w("MessengerIpcClient", valueOf.length() != 0 ? "Invalid interface descriptor: ".concat(valueOf) : new String("Invalid interface descriptor: "));
                throw new RemoteException();
            }
        }

        /* access modifiers changed from: package-private */
        public void send(Message message) throws RemoteException {
            Messenger messenger2 = this.messenger;
            if (messenger2 != null) {
                messenger2.send(message);
                return;
            }
            FirebaseIidMessengerCompat firebaseIidMessengerCompat = this.messengerCompat;
            if (firebaseIidMessengerCompat != null) {
                firebaseIidMessengerCompat.send(message);
                return;
            }
            throw new IllegalStateException("Both messengers are null");
        }
    }

    public Task<Void> sendOneWayRequest(int i, Bundle bundle) {
        return sendRequest(new OneWayRequest(getNextRequestId(), i, bundle));
    }

    public Task<Bundle> sendRequestForResponse(int i, Bundle bundle) {
        return sendRequest(new TwoWayRequest(getNextRequestId(), i, bundle));
    }

    private synchronized <T> Task<T> sendRequest(Request<T> request) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(request);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.connection.enqueueRequest(request)) {
            Connection connection2 = new Connection();
            this.connection = connection2;
            connection2.enqueueRequest(request);
        }
        return request.getTask();
    }

    private synchronized int getNextRequestId() {
        int i;
        i = this.nextRequestId;
        this.nextRequestId = i + 1;
        return i;
    }
}
