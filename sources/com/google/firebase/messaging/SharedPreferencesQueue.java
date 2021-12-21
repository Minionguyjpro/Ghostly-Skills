package com.google.firebase.messaging;

import android.content.SharedPreferences;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
final class SharedPreferencesQueue {
    private boolean bulkOperation = false;
    private final ArrayDeque<String> internalQueue = new ArrayDeque<>();
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        this.sharedPreferences = sharedPreferences2;
        this.queueName = str;
        this.itemSeparator = str2;
        this.syncExecutor = executor;
    }

    static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        SharedPreferencesQueue sharedPreferencesQueue = new SharedPreferencesQueue(sharedPreferences2, str, str2, executor);
        sharedPreferencesQueue.initQueue();
        return sharedPreferencesQueue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initQueue() {
        /*
            r6 = this;
            java.util.ArrayDeque<java.lang.String> r0 = r6.internalQueue
            monitor-enter(r0)
            java.util.ArrayDeque<java.lang.String> r1 = r6.internalQueue     // Catch:{ all -> 0x004a }
            r1.clear()     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences r1 = r6.sharedPreferences     // Catch:{ all -> 0x004a }
            java.lang.String r2 = r6.queueName     // Catch:{ all -> 0x004a }
            java.lang.String r3 = ""
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ all -> 0x004a }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x004a }
            if (r2 != 0) goto L_0x0048
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x004a }
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x004a }
            if (r2 != 0) goto L_0x0021
            goto L_0x0048
        L_0x0021:
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x004a }
            r3 = -1
            java.lang.String[] r1 = r1.split(r2, r3)     // Catch:{ all -> 0x004a }
            int r2 = r1.length     // Catch:{ all -> 0x004a }
            if (r2 != 0) goto L_0x0032
            java.lang.String r2 = "FirebaseMessaging"
            java.lang.String r3 = "Corrupted queue. Please check the queue contents and item separator provided"
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x004a }
        L_0x0032:
            int r2 = r1.length     // Catch:{ all -> 0x004a }
            r3 = 0
        L_0x0034:
            if (r3 >= r2) goto L_0x0046
            r4 = r1[r3]     // Catch:{ all -> 0x004a }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x004a }
            if (r5 != 0) goto L_0x0043
            java.util.ArrayDeque<java.lang.String> r5 = r6.internalQueue     // Catch:{ all -> 0x004a }
            r5.add(r4)     // Catch:{ all -> 0x004a }
        L_0x0043:
            int r3 = r3 + 1
            goto L_0x0034
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004d:
            throw r1
        L_0x004e:
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SharedPreferencesQueue.initQueue():void");
    }

    private final boolean checkAndSyncState(boolean z) {
        if (z && !this.bulkOperation) {
            syncStateAsync();
        }
        return z;
    }

    private final void syncStateAsync() {
        this.syncExecutor.execute(new SharedPreferencesQueue$$Lambda$0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: syncState */
    public final void bridge$lambda$0$SharedPreferencesQueue() {
        synchronized (this.internalQueue) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    public final String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.internalQueue.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(this.itemSeparator);
        }
        return sb.toString();
    }

    public final boolean remove(Object obj) {
        boolean checkAndSyncState;
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.remove(obj));
        }
        return checkAndSyncState;
    }

    public final String peek() {
        String peek;
        synchronized (this.internalQueue) {
            peek = this.internalQueue.peek();
        }
        return peek;
    }
}
