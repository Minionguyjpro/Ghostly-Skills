package com.tappx.a;

import android.view.MotionEvent;

class o3 {

    /* renamed from: a  reason: collision with root package name */
    private a f538a;

    public interface a {
        void a();
    }

    public void a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            a aVar = this.f538a;
            if (aVar != null) {
                aVar.a();
            } else {
                j4.a("No listener, click ignored");
            }
        }
    }

    public void a(a aVar) {
        this.f538a = aVar;
    }
}
