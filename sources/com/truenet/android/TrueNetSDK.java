package com.truenet.android;

import a.a.a.g;
import a.a.b.b.h;
import a.a.b.b.i;
import a.a.b.b.m;
import a.a.b.b.n;
import a.a.j;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.mopub.common.AdType;
import com.startapp.common.b.a.b;
import com.startapp.common.b.b;
import com.truenet.android.b;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public final class TrueNetSDK implements com.startapp.common.b.a.a {
    private static final String BASE_INIT_URL = "https://validation-engine.truenet.ai";
    private static final String BASE_RESULT_URL = "https://result-api.truenet.ai";
    public static final a Companion = new a((e) null);
    private static final String INIT_URL = (BASE_INIT_URL + "/api/initial");
    private static final int JOB_ID = 97764;
    public static final String JOB_TAG = "TruenetCheckLinksJob";
    private static final String PREFS_ENABLED = "PrefsEnabled";
    private static final String PREFS_PUBLISHER_ID = "PrefsPublisherId";
    public static final String PREFS_TAG = "TruenetJobKey";
    private static final String RESULT_URL = (BASE_RESULT_URL + "/api/result");
    /* access modifiers changed from: private */
    public static final URL initUrl = new URL(INIT_URL);
    /* access modifiers changed from: private */
    public static int intervalPosition;
    /* access modifiers changed from: private */
    public static final List<Long> intervals = g.a((T[]) new Long[]{15L, 30L, 60L, 120L, 240L, 480L});
    /* access modifiers changed from: private */
    public static long requestDelay;
    /* access modifiers changed from: private */
    public static final URL resultUrl = new URL(RESULT_URL);
    /* access modifiers changed from: private */
    public static ThreadFactory threadFactory = b.f664a;
    /* access modifiers changed from: private */
    public static boolean wasInitCalled;

    public static final void enable(Context context, boolean z) {
        Companion.a(context, z);
    }

    public static final void with(Context context, String str) {
        Companion.a(context, str);
    }

    /* compiled from: StartAppSDK */
    static final class c implements com.startapp.common.b.a.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TrueNetSDK f665a;

        c(TrueNetSDK trueNetSDK) {
            this.f665a = trueNetSDK;
        }

        /* compiled from: StartAppSDK */
        static final class a extends i implements a.a.b.a.a<j> {
            final /* synthetic */ Context $context$inlined;
            final /* synthetic */ Map $extras$inlined;
            final /* synthetic */ int $jobId$inlined;
            final /* synthetic */ b.C0011b $runnerListener$inlined;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            a(Map map, Context context, int i, b.C0011b bVar) {
                super(0);
                this.$extras$inlined = map;
                this.$context$inlined = context;
                this.$jobId$inlined = i;
                this.$runnerListener$inlined = bVar;
            }

            public /* synthetic */ Object a() {
                b();
                return j.f974a;
            }

            public final void b() {
                Log.d("JobManager", "finished " + String.valueOf(this.$jobId$inlined));
                this.$runnerListener$inlined.a(b.a.SUCCESS);
            }
        }

        public final void a(Context context, int i, Map<String, String> map, b.C0011b bVar) {
            synchronized (this.f665a) {
                if (h.a((Object) map.get(TrueNetSDK.JOB_TAG), (Object) TrueNetSDK.PREFS_TAG)) {
                    a aVar = TrueNetSDK.Companion;
                    h.a((Object) context, "context");
                    aVar.a(context, (a.a.b.a.a<j>) new a(map, context, i, bVar));
                }
                j jVar = j.f974a;
            }
        }
    }

    /* compiled from: StartAppSDK */
    public static final class a {

        /* renamed from: com.truenet.android.TrueNetSDK$a$a  reason: collision with other inner class name */
        /* compiled from: StartAppSDK */
        static final class C0019a extends i implements a.a.b.a.a<j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ a.a.b.a.a $finish;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            C0019a(LinksData linksData, ConcurrentLinkedQueue concurrentLinkedQueue, Context context, a.a.b.a.a aVar) {
                super(0);
                this.$links = linksData;
                this.$bulkQueue = concurrentLinkedQueue;
                this.$context = context;
                this.$finish = aVar;
            }

            public /* synthetic */ Object a() {
                b();
                return j.f974a;
            }

            public final void b() {
                if (this.$links.getBulkResponse()) {
                    String a2 = com.startapp.common.c.b.a(new ValidationResults(g.a(this.$bulkQueue)));
                    URL access$getResultUrl$cp = TrueNetSDK.resultUrl;
                    h.a((Object) a2, AdType.STATIC_NATIVE);
                    com.truenet.android.a.g.b(access$getResultUrl$cp, a2, this.$context);
                }
                TrueNetSDK.Companion.a(this.$context, this.$links.getSleep());
                if (this.$links.getSleep() != 0) {
                    this.$finish.a();
                }
            }
        }

        /* compiled from: StartAppSDK */
        static final class b extends i implements a.a.b.a.b<b, Integer, j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            b(LinksData linksData, Context context, ConcurrentLinkedQueue concurrentLinkedQueue) {
                super(2);
                this.$links = linksData;
                this.$context = context;
                this.$bulkQueue = concurrentLinkedQueue;
            }

            public /* synthetic */ Object a(Object obj, Object obj2) {
                a((b) obj, ((Number) obj2).intValue());
                return j.f974a;
            }

            public final void a(b bVar, int i) {
                String str;
                h.b(bVar, "info");
                Iterable<b.C0021b> d = bVar.d();
                Collection arrayList = new ArrayList(g.a(d, 10));
                for (b.C0021b bVar2 : d) {
                    String a2 = bVar2.a();
                    long b = bVar2.b();
                    int c = bVar2.c();
                    List<String> d2 = bVar2.d();
                    if (d2 == null) {
                        d2 = g.a();
                    }
                    arrayList.add(new RedirectsResult(a2, b, c, d2));
                }
                List list = (List) arrayList;
                Link link = this.$links.getValidation().get(i);
                String instanceId = link.getInstanceId();
                int b2 = bVar.b();
                long c2 = bVar.c();
                String e = bVar.e();
                String f = bVar.f();
                String htmlStorage = (f == null || !com.truenet.android.a.g.a(new URL(link.getHtmlStorage()), f, this.$context)) ? "" : link.getHtmlStorage();
                Bitmap a3 = bVar.a();
                if (a3 == null || !com.truenet.android.a.a.a(a3, link.getImageStorage())) {
                    str = "";
                } else {
                    str = link.getImageStorage();
                }
                ValidationResult validationResult = new ValidationResult(instanceId, b2, c2, list, e, htmlStorage, str, TrueNetSDK.Companion.c(this.$context), link.getMetaData());
                if (this.$links.getBulkResponse()) {
                    this.$bulkQueue.add(validationResult);
                    return;
                }
                String a4 = com.startapp.common.c.b.a(new ValidationResults(g.a(validationResult)));
                URL access$getResultUrl$cp = TrueNetSDK.resultUrl;
                h.a((Object) a4, AdType.STATIC_NATIVE);
                com.truenet.android.a.g.b(access$getResultUrl$cp, a4, this.$context);
            }
        }

        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final void a(Thread thread, Throwable th) {
            Log.e("TrueNetSDK", "Something went wrong in thread: " + String.valueOf(thread.getId()), th);
        }

        public final void a(Context context, String str) {
            h.b(context, "context");
            h.b(str, "publisherID");
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0);
                sharedPreferences.edit().putString(TrueNetSDK.PREFS_PUBLISHER_ID, str).apply();
                if (sharedPreferences.getBoolean(TrueNetSDK.PREFS_ENABLED, true) && !TrueNetSDK.wasInitCalled) {
                    a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        public final void a(Context context, boolean z) {
            h.b(context, "context");
            try {
                context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).edit().putBoolean(TrueNetSDK.PREFS_ENABLED, z).apply();
                if (z && !TrueNetSDK.wasInitCalled) {
                    a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        private final void a(Context context) {
            com.startapp.common.b.a.a(context);
            com.startapp.common.b.a.a((com.startapp.common.b.a.a) new TrueNetSDK());
            a(this, context, 0, 2, (Object) null);
        }

        static /* bridge */ /* synthetic */ void a(a aVar, Context context, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = 0;
            }
            aVar.a(context, j);
        }

        /* compiled from: StartAppSDK */
        static final class d implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ long f662a;
            final /* synthetic */ Context b;

            d(long j, Context context) {
                this.f662a = j;
                this.b = context;
            }

            public final void run() {
                final m.a aVar = new m.a();
                aVar.element = (String) null;
                if (this.f662a != 0 || new a.a.b.a.a<String>(this) {
                    final /* synthetic */ d this$0;

                    {
                        this.this$0 = r1;
                    }

                    /* renamed from: b */
                    public final String a() {
                        aVar.element = com.truenet.android.a.g.b(TrueNetSDK.initUrl, TrueNetSDK.Companion.b(this.this$0.b), this.this$0.b);
                        return (String) aVar.element;
                    }
                }.a() == null) {
                    TrueNetSDK.Companion.a(0, this.f662a);
                    return;
                }
                a aVar2 = TrueNetSDK.Companion;
                Context context = this.b;
                String str = (String) aVar.element;
                if (str == null) {
                    h.a();
                }
                aVar2.a(context, str, (a.a.b.a.a<j>) AnonymousClass2.f663a);
            }
        }

        /* access modifiers changed from: private */
        public final void a(Context context, long j) {
            Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new d(j, context));
        }

        /* access modifiers changed from: private */
        public final void a(int i, long j) {
            TrueNetSDK.requestDelay = j;
            TrueNetSDK.intervalPosition = a.a.c.a.a(i, TrueNetSDK.intervals.size() - 1);
            if (!(j != 0)) {
                j = TimeUnit.MINUTES.toMillis(((Number) TrueNetSDK.intervals.get(TrueNetSDK.intervalPosition)).longValue());
            }
            Log.d("JobManager", "scheduled millis: " + String.valueOf(j));
            com.startapp.common.b.a.a((int) TrueNetSDK.JOB_ID, false);
            com.startapp.common.b.a.a(new b.a(TrueNetSDK.JOB_ID).a(j).a(false).a(TrueNetSDK.JOB_TAG, TrueNetSDK.PREFS_TAG).b(true).a());
        }

        public final void a(Context context, a.a.b.a.a<j> aVar) {
            h.b(context, "context");
            h.b(aVar, "finish");
            try {
                if (!context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getBoolean(TrueNetSDK.PREFS_ENABLED, true)) {
                    com.startapp.common.b.a.a((int) TrueNetSDK.JOB_ID, false);
                    aVar.a();
                    return;
                }
                Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new c(context, aVar));
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        /* compiled from: StartAppSDK */
        static final class c implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Context f661a;
            final /* synthetic */ a.a.b.a.a b;

            c(Context context, a.a.b.a.a aVar) {
                this.f661a = context;
                this.b = aVar;
            }

            public final void run() {
                boolean z = TrueNetSDK.requestDelay != 0;
                Log.d("JobManager", "sending initial request");
                String b2 = com.truenet.android.a.g.b(TrueNetSDK.initUrl, TrueNetSDK.Companion.b(this.f661a), this.f661a);
                if (b2 != null) {
                    TrueNetSDK.Companion.a(this.f661a, b2, (a.a.b.a.a<j>) this.b);
                    return;
                }
                TrueNetSDK.Companion.a(z ? TrueNetSDK.intervalPosition : TrueNetSDK.intervalPosition + 1, 0);
                this.b.a();
            }
        }

        /* access modifiers changed from: private */
        public final String b(Context context) {
            DeviceInfo a2 = new a(context, (TelephonyManager) null, 2, (e) null).a();
            a2.setPublisherId(c(context));
            String a3 = com.startapp.common.c.b.a(a2);
            h.a((Object) a3, "JSONParser.toJson(info)");
            return a3;
        }

        /* access modifiers changed from: private */
        public final String c(Context context) {
            String string = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getString(TrueNetSDK.PREFS_PUBLISHER_ID, "Undefined");
            h.a((Object) string, "prefs.getString(PREFS_PUBLISHER_ID, \"Undefined\")");
            return string;
        }

        /* access modifiers changed from: private */
        public final void a(Context context, String str, a.a.b.a.a<j> aVar) {
            TrueNetSDK.intervalPosition = 0;
            TrueNetSDK.requestDelay = 0;
            LinksData linksData = (LinksData) com.startapp.common.c.b.a(str, LinksData.class);
            if (linksData.getValidation().size() != 0) {
                h.a((Object) linksData, "response");
                a(context, linksData, aVar);
                return;
            }
            a(context, linksData.getSleep());
            if (linksData.getSleep() != 0) {
                aVar.a();
            }
        }

        private final void a(Context context, LinksData linksData, a.a.b.a.a<j> aVar) {
            Iterable<Link> validation = linksData.getValidation();
            Collection arrayList = new ArrayList(g.a(validation, 10));
            for (Link validationUrl : validation) {
                arrayList.add(validationUrl.getValidationUrl());
            }
            c cVar = new c(context, (List) arrayList, TrueNetSDK.threadFactory, linksData.getMaxRedirectTime(), linksData.getNumOfRedirect(), linksData.getValidateParallel());
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            cVar.a((a.a.b.a.a<j>) new C0019a(linksData, concurrentLinkedQueue, context, aVar));
            cVar.a((a.a.b.a.b<? super b, ? super Integer, j>) new b(linksData, context, concurrentLinkedQueue));
        }
    }

    /* compiled from: StartAppSDK */
    static final class b implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        public static final b f664a = new b();

        b() {
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler(new d(new a.a.b.a.b<Thread, Throwable, j>(TrueNetSDK.Companion) {
                public final a.a.d.c a() {
                    return n.a(a.class);
                }

                public final String b() {
                    return "uncaughtExceptionHandler";
                }

                public final String c() {
                    return "uncaughtExceptionHandler(Ljava/lang/Thread;Ljava/lang/Throwable;)V";
                }

                public /* bridge */ /* synthetic */ Object a(Object obj, Object obj2) {
                    a((Thread) obj, (Throwable) obj2);
                    return j.f974a;
                }

                public final void a(Thread thread, Throwable th) {
                    h.b(thread, "p1");
                    h.b(th, "p2");
                    ((a) this.receiver).a(thread, th);
                }
            }));
            return thread;
        }
    }

    public com.startapp.common.b.a.b create(int i) {
        if (i != JOB_ID) {
            return null;
        }
        Log.d("JobManager", "addJobCreator");
        return new c(this);
    }
}
