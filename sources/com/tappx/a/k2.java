package com.tappx.a;

class k2 {

    /* renamed from: a  reason: collision with root package name */
    private final v f491a;
    /* access modifiers changed from: private */
    public final t b;

    class a implements m<j2> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f492a;

        a(c cVar) {
            this.f492a = cVar;
        }

        public void a(j2 j2Var) {
            String c = j2Var.c();
            if (c != null) {
                k2.this.b.a(c);
            }
            if (j2Var.d()) {
                this.f492a.a(j2Var.a(), j2Var.b());
            } else {
                this.f492a.b();
            }
        }
    }

    class b implements h<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f493a;

        b(k2 k2Var, c cVar) {
            this.f493a = cVar;
        }

        public void a(Void voidR) {
            this.f493a.a();
        }
    }

    public interface c {
        void a();

        void a(String str, String str2);

        void b();
    }

    public k2(v vVar, t tVar) {
        this.f491a = vVar;
        this.b = tVar;
    }

    public void a(c cVar) {
        this.f491a.a(new a(cVar), new b(this, cVar));
    }
}
