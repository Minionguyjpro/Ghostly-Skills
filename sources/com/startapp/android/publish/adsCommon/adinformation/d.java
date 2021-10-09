package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.adsCommon.adinformation.b;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class d extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f213a;
    private RelativeLayout b;
    private View.OnClickListener c = null;
    private AdInformationConfig d;
    private e e;
    private AdPreferences.Placement f;
    private AdInformationPositions.Position g;

    public d(Context context, b.C0003b bVar, AdPreferences.Placement placement, c cVar, final View.OnClickListener onClickListener) {
        super(context);
        this.f = placement;
        this.c = new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        };
        a(bVar, cVar);
    }

    /* access modifiers changed from: protected */
    public void a(b.C0003b bVar, c cVar) {
        AdInformationConfig a2 = b.a(getContext());
        this.d = a2;
        if (a2 == null) {
            this.d = AdInformationConfig.a();
        }
        this.e = this.d.a(bVar.a());
        if (cVar == null || !cVar.d()) {
            this.g = this.d.a(this.f);
        } else {
            this.g = cVar.c();
        }
        ImageView imageView = new ImageView(getContext());
        this.f213a = imageView;
        imageView.setContentDescription("info");
        this.f213a.setId(AdsConstants.AD_INFORMATION_ID);
        this.f213a.setImageBitmap(this.e.a(getContext()));
        this.b = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(h.a(getContext(), (int) (((float) this.e.b()) * this.d.e())), h.a(getContext(), (int) (((float) this.e.c()) * this.d.e())));
        this.b.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(h.a(getContext(), this.e.b()), h.a(getContext(), this.e.c()));
        layoutParams2.setMargins(0, 0, 0, 0);
        this.f213a.setPadding(0, 0, 0, 0);
        this.g.addRules(layoutParams2);
        this.b.addView(this.f213a, layoutParams2);
        this.b.setOnClickListener(this.c);
        addView(this.b, layoutParams);
    }
}
