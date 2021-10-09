package com.startapp.common.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView;
import com.startapp.android.publish.common.metaData.InfoEventService;
import com.startapp.android.publish.common.metaData.PeriodicJobService;
import com.startapp.common.b.a.b;
import com.startapp.common.b.a.c;
import com.startapp.common.b.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
public class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static volatile a f347a = null;
    private static volatile c b = null;
    private static volatile Integer c = null;
    private static volatile int d = 60000;
    private static final ExecutorService j = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService k = Executors.newScheduledThreadPool(1);
    /* access modifiers changed from: private */
    public Context e;
    private List<com.startapp.common.b.a.a> f = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public Map<Integer, Integer> g = new ConcurrentHashMap();
    private AtomicInteger h = new AtomicInteger(0);
    private boolean i;

    private static int b(int i2) {
        return i2 & Integer.MAX_VALUE;
    }

    private static int b(int i2, boolean z) {
        return z ? i2 | RecyclerView.UNDEFINED_DURATION : i2;
    }

    private a(Context context) {
        this.e = context.getApplicationContext();
        this.i = d(context);
    }

    public static a a(Context context) {
        if (f347a == null) {
            synchronized (a.class) {
                if (f347a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    f347a = new a(context);
                    try {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("RunnerManager", 0);
                        String str = null;
                        String string = sharedPreferences.getString("RegisteredClassesNames", (String) null);
                        if (string != null) {
                            String[] split = string.split(",");
                            StringBuilder sb = new StringBuilder(string.length());
                            for (String str2 : split) {
                                try {
                                    a(3, "RunnerManager", "create CLS: " + str2);
                                    Class<?> cls = Class.forName(str2);
                                    if (com.startapp.common.b.a.a.class.isAssignableFrom(cls)) {
                                        f347a.f.add((com.startapp.common.b.a.a) cls.newInstance());
                                        if (sb.length() > 0) {
                                            sb.append(',');
                                        }
                                        sb.append(str2);
                                    }
                                } catch (ClassNotFoundException unused) {
                                } catch (Throwable th) {
                                    a(6, "RunnerManager", "create :" + str2, th);
                                }
                            }
                            if (!sb.toString().equals(string)) {
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                if (sb.length() > 0) {
                                    str = sb.toString();
                                }
                                edit.putString("RegisteredClassesNames", str).commit();
                            }
                        }
                    } catch (Exception e2) {
                        a(6, "RunnerManager", "create", (Throwable) e2);
                    }
                }
            }
        }
        return f347a;
    }

    public static void a(com.startapp.common.b.a.a aVar) {
        f347a.f.add(aVar);
        String name = aVar.getClass().getName();
        SharedPreferences sharedPreferences = f347a.e.getSharedPreferences("RunnerManager", 0);
        String string = sharedPreferences.getString("RegisteredClassesNames", (String) null);
        if (string == null) {
            sharedPreferences.edit().putString("RegisteredClassesNames", name).commit();
        } else if (!string.contains(name)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("RegisteredClassesNames", string + "," + name).commit();
        }
    }

    public static void a(c cVar) {
        b = cVar;
    }

    public static boolean a(b bVar) {
        try {
            int b2 = b(bVar.a(), bVar.e());
            a(3, "RunnerManager", "schedule " + b2 + " " + bVar);
            if (!c()) {
                return c(b2, bVar);
            }
            if (b()) {
                return a(b2, bVar);
            }
            return b(b2, bVar);
        } catch (Exception e2) {
            a(6, "RunnerManager", "schedule error", (Throwable) e2);
            return false;
        }
    }

    private static boolean a(int i2, b bVar) {
        JobScheduler c2 = c(f347a.e);
        if (c2 == null) {
            return false;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        Map<String, String> b2 = bVar.b();
        for (String next : b2.keySet()) {
            persistableBundle.putString(next, b2.get(next));
        }
        persistableBundle.putInt("__RUNNER_RECURRING_ID__", bVar.e() ? 1 : 0);
        persistableBundle.putLong("__RUNNER_TRIGGER_ID__", bVar.c());
        JobInfo.Builder builder = new JobInfo.Builder(i2, new ComponentName(f347a.e, PeriodicJobService.class));
        builder.setExtras(persistableBundle);
        if (bVar.e()) {
            builder.setPeriodic(bVar.c());
        } else {
            builder.setMinimumLatency(bVar.c()).setOverrideDeadline(bVar.c() + ((long) d));
        }
        builder.setRequiredNetworkType(bVar.f() ? 1 : 0);
        int schedule = c2.schedule(builder.build());
        a(3, "RunnerManager", "jobScheduler.schedule " + schedule);
        if (schedule == 1) {
            return true;
        }
        return false;
    }

    private static boolean b(int i2, b bVar) {
        AlarmManager b2 = b(f347a.e);
        if (b2 == null) {
            return false;
        }
        Intent intent = new Intent(f347a.e, InfoEventService.class);
        Map<String, String> b3 = bVar.b();
        for (String next : b3.keySet()) {
            intent.putExtra(next, b3.get(next));
        }
        intent.putExtra("__RUNNER_TASK_ID__", i2);
        intent.putExtra("__RUNNER_RECURRING_ID__", bVar.e());
        intent.putExtra("__RUNNER_TRIGGER_ID__", bVar.c());
        PendingIntent service = PendingIntent.getService(f347a.e, i2, intent, 134217728);
        b2.cancel(service);
        if (bVar.e()) {
            b2.setRepeating(0, System.currentTimeMillis() + bVar.d(), bVar.c(), service);
            return true;
        }
        b2.set(3, SystemClock.elapsedRealtime() + bVar.c(), service);
        return true;
    }

    private static boolean c(final int i2, final b bVar) {
        final int incrementAndGet = f347a.h.incrementAndGet();
        AnonymousClass1 r2 = new Runnable() {
            public void run() {
                Integer num = (Integer) a.f347a.g.get(Integer.valueOf(i2));
                if (num != null && num.intValue() == incrementAndGet) {
                    if (!bVar.e()) {
                        a.f347a.g.remove(Integer.valueOf(i2));
                    }
                    boolean unused = a.b(bVar, (b.C0011b) new b.C0011b() {
                        public void a(b.a aVar) {
                        }
                    });
                }
            }
        };
        if (bVar.e()) {
            k.scheduleAtFixedRate(r2, bVar.d(), bVar.d(), TimeUnit.MILLISECONDS);
        } else {
            k.schedule(r2, bVar.c(), TimeUnit.MILLISECONDS);
        }
        f347a.g.put(Integer.valueOf(i2), Integer.valueOf(incrementAndGet));
        return true;
    }

    public static void a(int i2, boolean z) {
        a(3, "RunnerManager", "cancelAlarm " + i2);
        try {
            int b2 = b(i2, z);
            if (!f347a.i) {
                f347a.g.remove(Integer.valueOf(b2));
            } else if (b()) {
                JobScheduler c2 = c(f347a.e);
                if (c2 != null) {
                    c2.cancel(b2);
                }
            } else {
                AlarmManager b3 = b(f347a.e);
                if (b3 != null) {
                    a(f347a.e, new Intent(f347a.e, InfoEventService.class), b3, b2);
                }
            }
        } catch (Exception e2) {
            a(6, "RunnerManager", "cancelAlarm " + i2, (Throwable) e2);
        }
    }

    public static void a(int i2, String str, String str2) {
        a(i2, str, str2, (Throwable) null);
    }

    public static void a(int i2, String str, String str2, Throwable th) {
        if (b != null) {
            b.a(i2, str, str2, th);
        }
    }

    public static boolean a(Intent intent, b.C0011b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("runJob ");
        sb.append(intent != null ? intent : "NULL");
        a(3, "RunnerManager", sb.toString());
        return intent != null && b(a(intent), bVar);
    }

    public static boolean a(JobParameters jobParameters, b.C0011b bVar) {
        a(3, "RunnerManager", "runJob " + jobParameters);
        return b(a(jobParameters), bVar);
    }

    /* access modifiers changed from: private */
    public static boolean b(final b bVar, final b.C0011b bVar2) {
        a(3, "RunnerManager", "RunnerJob " + bVar.a() + " " + b(bVar.a()));
        final int b2 = b(bVar.a());
        final b a2 = a(b2);
        if (a2 != null) {
            j.execute(new Runnable() {
                public void run() {
                    a2.a(a.f347a.e, b2, bVar.b(), new b.C0011b() {
                        public void a(b.a aVar) {
                            a.a(3, "RunnerManager", "job.execute " + bVar.a() + " " + aVar);
                            if (aVar == b.a.RESCHEDULE && !bVar.e()) {
                                a.a(bVar);
                            }
                            bVar2.a(aVar);
                        }
                    });
                }
            });
            return true;
        }
        a(5, "RunnerManager", "runJob: failed to get job for ID " + bVar.a());
        bVar2.a(b.a.FAILED);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0009 A[LOOP:0: B:1:0x0009->B:4:0x0019, LOOP_START, PHI: r1 
      PHI: (r1v1 com.startapp.common.b.a.b) = (r1v0 com.startapp.common.b.a.b), (r1v5 com.startapp.common.b.a.b) binds: [B:0:0x0000, B:4:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.startapp.common.b.a.b a(int r3) {
        /*
            com.startapp.common.b.a r0 = f347a
            java.util.List<com.startapp.common.b.a.a> r0 = r0.f
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0009:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001b
            java.lang.Object r1 = r0.next()
            com.startapp.common.b.a.a r1 = (com.startapp.common.b.a.a) r1
            com.startapp.common.b.a.b r1 = r1.create(r3)
            if (r1 == 0) goto L_0x0009
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.b.a.a(int):com.startapp.common.b.a.b");
    }

    private static void a(Context context, Intent intent, AlarmManager alarmManager, int i2) {
        PendingIntent service = PendingIntent.getService(context, i2, intent, 134217728);
        if (PendingIntent.getService(context, 0, intent, 268435456) != null) {
            alarmManager.cancel(service);
            service.cancel();
        }
    }

    private static b a(Intent intent) {
        HashMap hashMap;
        int intExtra = intent.getIntExtra("__RUNNER_TASK_ID__", -1);
        boolean booleanExtra = intent.getBooleanExtra("__RUNNER_RECURRING_ID__", false);
        long longExtra = intent.getLongExtra("__RUNNER_TRIGGER_ID__", 0);
        if (intent.getExtras() != null) {
            hashMap = new HashMap(intent.getExtras().keySet().size());
            for (String str : intent.getExtras().keySet()) {
                Object obj = intent.getExtras().get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                }
            }
        } else {
            hashMap = null;
        }
        return new b.a(intExtra).a((Map<String, String>) hashMap).a(booleanExtra).a(longExtra).a();
    }

    private static b a(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        boolean z = true;
        if (extras.getInt("__RUNNER_RECURRING_ID__") != 1) {
            z = false;
        }
        long j2 = extras.getLong("__RUNNER_TRIGGER_ID__", 0);
        HashMap hashMap = new HashMap(extras.keySet().size());
        for (String str : extras.keySet()) {
            Object obj = extras.get(str);
            if (obj instanceof String) {
                hashMap.put(str, (String) obj);
            }
        }
        return new b.a(jobParameters.getJobId()).a((Map<String, String>) hashMap).a(z).a(j2).a();
    }

    private static AlarmManager b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager == null) {
            a(6, "RunnerManager", "failed to get AlarmManager");
        }
        return alarmManager;
    }

    private static JobScheduler c(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            a(6, "RunnerManager", "failed to get JobScheduler");
        }
        return jobScheduler;
    }

    private static boolean b() {
        int i2 = Build.VERSION.SDK_INT;
        if (c != null) {
            i2 = c.intValue();
        }
        return i2 >= 21;
    }

    private boolean d(Context context) {
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services) {
                if (serviceInfo.name.equals(InfoEventService.class.getName())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            a(6, "RunnerManager", "servicesDefined", th);
        }
        return false;
    }

    private static boolean c() {
        return f347a.i;
    }
}
