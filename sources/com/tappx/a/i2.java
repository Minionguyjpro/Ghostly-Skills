package com.tappx.a;

class i2 {

    /* renamed from: a  reason: collision with root package name */
    private final v f468a;

    class a implements m<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f469a;

        a(i2 i2Var, c cVar) {
            this.f469a = cVar;
        }

        public void a(Void voidR) {
            this.f469a.a(true);
        }
    }

    class b implements h<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f470a;

        b(i2 i2Var, c cVar) {
            this.f470a = cVar;
        }

        public void a(Void voidR) {
            this.f470a.a(false);
        }
    }

    public interface c {
        void a(boolean z);
    }

    i2(v vVar) {
        this.f468a = vVar;
    }

    public void a(p2 p2Var, long j, c cVar) {
        this.f468a.a(j, p2Var, new a(this, cVar), new b(this, cVar));
    }
}
