package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class b extends ArrayAdapter<ListItem> {

    /* renamed from: a  reason: collision with root package name */
    private String f68a;
    private String b;

    public b(Context context, List<ListItem> list, String str, String str2, String str3) {
        super(context, 0, list);
        this.f68a = str2;
        this.b = str3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        d dVar;
        if (view == null) {
            dVar = new d(getContext());
            view2 = dVar.a();
        } else {
            view2 = view;
            dVar = (d) view.getTag();
        }
        ListItem listItem = (ListItem) getItem(i);
        dVar.a(com.startapp.android.publish.adsCommon.b.a().a(listItem.n()));
        dVar.c().setText(listItem.g());
        dVar.d().setText(listItem.h());
        Bitmap a2 = f.a().a(this.b).a(i, listItem.a(), listItem.i());
        if (a2 == null) {
            dVar.b().setImageResource(17301651);
            dVar.b().setTag("tag_error");
        } else {
            dVar.b().setImageBitmap(a2);
            dVar.b().setTag("tag_ok");
        }
        dVar.e().setRating(listItem.k());
        dVar.a(listItem.q());
        f.a().a(this.b).a(getContext(), listItem.a(), listItem.c(), new com.startapp.android.publish.adsCommon.d.b(this.f68a), a(listItem));
        return view2;
    }

    private long a(ListItem listItem) {
        if (listItem.r() != null) {
            return TimeUnit.SECONDS.toMillis(listItem.r().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}
