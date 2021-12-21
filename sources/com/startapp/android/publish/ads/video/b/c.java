package com.startapp.android.publish.ads.video.b;

/* compiled from: StartAppSDK */
public interface c {

    /* compiled from: StartAppSDK */
    public interface a {
    }

    /* compiled from: StartAppSDK */
    public interface b {
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$c  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public interface C0002c {
        void a(int i);
    }

    /* compiled from: StartAppSDK */
    public interface d {
        void a();
    }

    /* compiled from: StartAppSDK */
    public interface e {
        boolean a(g gVar);
    }

    /* compiled from: StartAppSDK */
    public interface f {
        void a();
    }

    /* compiled from: StartAppSDK */
    public enum h {
        UNKNOWN,
        SERVER_DIED,
        BUFFERING_TIMEOUT,
        PLAYER_CREATION
    }

    void a();

    void a(int i);

    void a(a aVar);

    void a(b bVar);

    void a(C0002c cVar);

    void a(d dVar);

    void a(e eVar);

    void a(f fVar);

    void a(String str);

    void a(boolean z);

    void b();

    void c();

    int d();

    int e();

    boolean f();

    void g();

    /* compiled from: StartAppSDK */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        private h f114a;
        private String b;
        private int c;

        public g(h hVar, String str, int i) {
            this.f114a = hVar;
            this.b = str;
            this.c = i;
        }

        public h a() {
            return this.f114a;
        }

        public String b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }
}
