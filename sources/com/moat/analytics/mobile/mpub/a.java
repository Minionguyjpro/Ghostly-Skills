package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

class a {

    /* renamed from: a  reason: collision with root package name */
    static WeakReference<Activity> f1151a = null;
    private static boolean b = false;
    private static Application c = null;
    /* access modifiers changed from: private */
    public static int d = 0;
    /* access modifiers changed from: private */
    public static boolean e = false;

    /* renamed from: com.moat.analytics.mobile.mpub.a$a  reason: collision with other inner class name */
    private static class C0047a implements Application.ActivityLifecycleCallbacks {
        C0047a() {
        }

        private static void a(boolean z) {
            p.a(3, "ActivityState", (Object) null, z ? "App became visible" : "App became invisible");
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            int unused = a.d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(a.d == 3 || a.d == 5)) {
                    if (a.e) {
                        a(false);
                    }
                    boolean unused = a.e = false;
                }
                int unused2 = a.d = 6;
                p.a(3, "ActivityState", (Object) this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (a.b(activity)) {
                    a.f1151a = new WeakReference<>((Object) null);
                }
            } catch (Exception e) {
                n.a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                int unused = a.d = 4;
                if (a.b(activity)) {
                    a.f1151a = new WeakReference<>((Object) null);
                }
                p.a(3, "ActivityState", (Object) this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                n.a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                a.f1151a = new WeakReference<>(activity);
                int unused = a.d = 3;
                w.a().b();
                p.a(3, "ActivityState", (Object) this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((k) MoatAnalytics.getInstance()).b) {
                    f.a(activity);
                }
            } catch (Exception e) {
                n.a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                a.f1151a = new WeakReference<>(activity);
                int unused = a.d = 2;
                if (!a.e) {
                    a(true);
                }
                boolean unused2 = a.e = true;
                p.a(3, "ActivityState", (Object) this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                n.a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (a.d != 3) {
                    boolean unused = a.e = false;
                    a(false);
                }
                int unused2 = a.d = 5;
                if (a.b(activity)) {
                    a.f1151a = new WeakReference<>((Object) null);
                }
                p.a(3, "ActivityState", (Object) this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                n.a(e);
            }
        }
    }

    a() {
    }

    static Application a() {
        return c;
    }

    static void a(Application application) {
        c = application;
        if (!b) {
            b = true;
            application.registerActivityLifecycleCallbacks(new C0047a());
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(Activity activity) {
        WeakReference<Activity> weakReference = f1151a;
        return weakReference != null && weakReference.get() == activity;
    }
}
