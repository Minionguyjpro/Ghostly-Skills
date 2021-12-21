package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class z {

    /* renamed from: a  reason: collision with root package name */
    String f1197a = "{}";
    private c b = new c();
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Map<String, Object> h = new HashMap();

    static class a {

        /* renamed from: a  reason: collision with root package name */
        int f1198a = 0;
        final Set<Rect> b = new HashSet();
        boolean c = false;

        a() {
        }
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        final View f1199a;
        final Rect b;

        b(View view, b bVar) {
            this.f1199a = view;
            this.b = bVar != null ? z.b(view, bVar.b.left, bVar.b.top) : z.k(view);
        }
    }

    private static class c {

        /* renamed from: a  reason: collision with root package name */
        Rect f1200a = new Rect(0, 0, 0, 0);
        double b = 0.0d;
        double c = 0.0d;

        c() {
        }
    }

    z() {
    }

    static int a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList<Rect> arrayList = new ArrayList<>();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() {
            /* renamed from: a */
            public int compare(Rect rect, Rect rect2) {
                return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        while (i < arrayList2.size() - 1) {
            int i3 = i + 1;
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i3))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i3)).intValue(), rect.bottom);
                int i4 = rect.top;
                for (Rect rect4 : arrayList) {
                    if (Rect.intersects(rect4, rect3)) {
                        if (rect4.bottom > i4) {
                            i2 += rect3.width() * (rect4.bottom - Math.max(i4, rect4.top));
                            i4 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i = i3;
        }
        return i2;
    }

    private static Rect a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect a(View view) {
        return view != null ? k(view) : new Rect(0, 0, 0, 0);
    }

    static a a(Rect rect, View view) {
        a aVar = new a();
        try {
            ArrayDeque<View> i = i(view);
            if (i != null) {
                if (!i.isEmpty()) {
                    p.b(2, "VisibilityInfo", view, "starting covering rect search");
                    b bVar = null;
                    loop0:
                    while (true) {
                        if (i.isEmpty()) {
                            break;
                        }
                        View pollLast = i.pollLast();
                        b bVar2 = new b(pollLast, bVar);
                        if (pollLast.getParent() != null) {
                            if (pollLast.getParent() instanceof ViewGroup) {
                                ViewGroup viewGroup = (ViewGroup) pollLast.getParent();
                                int childCount = viewGroup.getChildCount();
                                boolean z = false;
                                for (int i2 = 0; i2 < childCount; i2++) {
                                    if (aVar.f1198a >= 500) {
                                        p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting cover retrieval, reached max");
                                        break loop0;
                                    }
                                    View childAt = viewGroup.getChildAt(i2);
                                    if (childAt == pollLast) {
                                        z = true;
                                    } else {
                                        aVar.f1198a++;
                                        if (a(childAt, pollLast, z)) {
                                            b(new b(childAt, bVar), rect, aVar);
                                            if (aVar.c) {
                                                return aVar;
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        }
                        bVar = bVar2;
                    }
                    return aVar;
                }
            }
            return aVar;
        } catch (Exception e2) {
            n.a(e2);
        }
    }

    private static c a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        c cVar = new c();
        int b2 = b(rect);
        if (view != null && z && z2 && !z3 && b2 > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (a(view, rect2)) {
                int b3 = b(rect2);
                if (b3 < b2) {
                    p.b(2, "VisibilityInfo", (Object) null, "Ad is clipped");
                }
                if (view.getRootView() instanceof ViewGroup) {
                    cVar.f1200a = rect2;
                    a a2 = a(rect2, view);
                    if (a2.c) {
                        cVar.c = 1.0d;
                    } else {
                        int a3 = a(rect2, a2.b);
                        if (a3 > 0) {
                            double d2 = (double) a3;
                            double d3 = (double) b3;
                            Double.isNaN(d3);
                            Double.isNaN(d2);
                            cVar.c = d2 / (d3 * 1.0d);
                        }
                        double d4 = (double) (b3 - a3);
                        double d5 = (double) b2;
                        Double.isNaN(d5);
                        Double.isNaN(d4);
                        cVar.b = d4 / (d5 * 1.0d);
                    }
                }
            }
        }
        return cVar;
    }

    private static Map<String, String> a(Rect rect) {
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_X, String.valueOf(rect.left));
        hashMap.put(AvidJSONUtil.KEY_Y, String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Map<String, String> a(Rect rect, DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }

    private static void a(b bVar, Rect rect, a aVar) {
        Rect rect2 = bVar.b;
        if (rect2.setIntersect(rect, rect2)) {
            if (Build.VERSION.SDK_INT >= 22) {
                Rect rect3 = new Rect(0, 0, 0, 0);
                if (a(bVar.f1199a, rect3)) {
                    Rect rect4 = bVar.b;
                    if (rect4.setIntersect(rect3, rect4)) {
                        rect2 = rect4;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (w.a().c) {
                p.b(2, "VisibilityInfo", bVar.f1199a, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", new Object[]{bVar.f1199a.getClass().getName(), rect2.toString(), Float.valueOf(bVar.f1199a.getAlpha())}));
            }
            aVar.b.add(rect2);
            if (rect2.contains(rect)) {
                aVar.c = true;
            }
        }
    }

    private static boolean a(View view, Rect rect) {
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        int[] iArr = {RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION};
        view.getLocationInWindow(iArr);
        int[] iArr2 = {RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    private static boolean a(View view, View view2, boolean z) {
        return z ? Build.VERSION.SDK_INT < 21 || view.getZ() >= view2.getZ() : Build.VERSION.SDK_INT >= 21 && view.getZ() > view2.getZ();
    }

    private static int b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect b(Rect rect, DisplayMetrics displayMetrics) {
        float f2 = displayMetrics.density;
        if (f2 == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f2), Math.round(((float) rect.top) / f2), Math.round(((float) rect.right) / f2), Math.round(((float) rect.bottom) / f2));
    }

    /* access modifiers changed from: private */
    public static Rect b(View view, int i, int i2) {
        int left = i + view.getLeft();
        int top = i2 + view.getTop();
        return new Rect(left, top, view.getWidth() + left, view.getHeight() + top);
    }

    private static void b(b bVar, Rect rect, a aVar) {
        if (h(bVar.f1199a)) {
            boolean z = true;
            if (bVar.f1199a instanceof ViewGroup) {
                int i = 0;
                boolean z2 = !ViewGroup.class.equals(bVar.f1199a.getClass().getSuperclass()) || !j(bVar.f1199a);
                ViewGroup viewGroup = (ViewGroup) bVar.f1199a;
                int childCount = viewGroup.getChildCount();
                while (i < childCount) {
                    int i2 = aVar.f1198a + 1;
                    aVar.f1198a = i2;
                    if (i2 <= 500) {
                        b(new b(viewGroup.getChildAt(i), bVar), rect, aVar);
                        if (!aVar.c) {
                            i++;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                z = z2;
            }
            if (z) {
                a(bVar, rect, aVar);
            }
        }
    }

    private static boolean c(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean d(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean e(View view) {
        return view == null || !view.isShown();
    }

    private static float f(View view) {
        if (view == null) {
            return 0.0f;
        }
        return g(view);
    }

    private static float g(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean h(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static ArrayDeque<View> i(View view) {
        ArrayDeque<View> arrayDeque = new ArrayDeque<>();
        int i = 0;
        View view2 = view;
        while (true) {
            if (view2.getParent() == null && view2 != view.getRootView()) {
                break;
            }
            i++;
            if (i <= 50) {
                arrayDeque.add(view2);
                if (!(view2.getParent() instanceof View)) {
                    break;
                }
                view2 = (View) view2.getParent();
            } else {
                p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting chain retrieval, reached max");
                break;
            }
        }
        return arrayDeque;
    }

    private static boolean j(View view) {
        return Build.VERSION.SDK_INT < 19 || view.getBackground() == null || view.getBackground().getAlpha() == 0;
    }

    /* access modifiers changed from: private */
    public static Rect k(View view) {
        int[] iArr = {RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    private static DisplayMetrics l(View view) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 17 || a.f1151a == null || (activity = (Activity) a.f1151a.get()) == null) {
            return view.getContext().getResources().getDisplayMetrics();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107 A[Catch:{ Exception -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r12, android.view.View r13) {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "{}"
            if (r13 == 0) goto L_0x0144
            android.util.DisplayMetrics r2 = l(r13)     // Catch:{ Exception -> 0x013e }
            boolean r3 = c(r13)     // Catch:{ Exception -> 0x013e }
            boolean r4 = d(r13)     // Catch:{ Exception -> 0x013e }
            boolean r5 = e(r13)     // Catch:{ Exception -> 0x013e }
            float r6 = f(r13)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "dr"
            float r8 = r2.density     // Catch:{ Exception -> 0x013e }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ Exception -> 0x013e }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "dv"
            com.moat.analytics.mobile.mpub.l r8 = com.moat.analytics.mobile.mpub.l.a()     // Catch:{ Exception -> 0x013e }
            double r8 = r8.b()     // Catch:{ Exception -> 0x013e }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ Exception -> 0x013e }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "adKey"
            r0.put(r7, r12)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "isAttached"
            r7 = 0
            r8 = 1
            if (r3 == 0) goto L_0x0046
            r9 = 1
            goto L_0x0047
        L_0x0046:
            r9 = 0
        L_0x0047:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "inFocus"
            if (r4 == 0) goto L_0x0054
            r9 = 1
            goto L_0x0055
        L_0x0054:
            r9 = 0
        L_0x0055:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "isHidden"
            if (r5 == 0) goto L_0x0062
            r9 = 1
            goto L_0x0063
        L_0x0062:
            r9 = 0
        L_0x0063:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "opacity"
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r6)     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r12 = a((android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r6 = a((android.view.View) r13)     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r13 = a(r13, r6, r3, r4, r5)     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r3 = r11.c     // Catch:{ Exception -> 0x013e }
            if (r3 == 0) goto L_0x00a3
            double r3 = r13.b     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r5 = r11.b     // Catch:{ Exception -> 0x013e }
            double r9 = r5.b     // Catch:{ Exception -> 0x013e }
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x00a3
            android.graphics.Rect r3 = r13.f1200a     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r4 = r11.b     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r4 = r4.f1200a     // Catch:{ Exception -> 0x013e }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x013e }
            if (r3 == 0) goto L_0x00a3
            double r3 = r13.c     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r5 = r11.b     // Catch:{ Exception -> 0x013e }
            double r9 = r5.c     // Catch:{ Exception -> 0x013e }
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x00b5
        L_0x00a3:
            r11.b = r13     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r4 = r11.b     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r4 = r4.f1200a     // Catch:{ Exception -> 0x013e }
            java.util.Map r4 = a((android.graphics.Rect) r4, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r3.<init>(r4)     // Catch:{ Exception -> 0x013e }
            r11.c = r3     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00b5:
            java.lang.String r3 = "coveredPercent"
            double r4 = r13.c     // Catch:{ Exception -> 0x013e }
            java.lang.Double r13 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x013e }
            r0.put(r3, r13)     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r13 = r11.g     // Catch:{ Exception -> 0x013e }
            if (r13 == 0) goto L_0x00cc
            android.graphics.Rect r13 = r11.e     // Catch:{ Exception -> 0x013e }
            boolean r13 = r12.equals(r13)     // Catch:{ Exception -> 0x013e }
            if (r13 != 0) goto L_0x00da
        L_0x00cc:
            r11.e = r12     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map r12 = a((android.graphics.Rect) r12, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r13.<init>(r12)     // Catch:{ Exception -> 0x013e }
            r11.g = r13     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00da:
            org.json.JSONObject r12 = r11.f     // Catch:{ Exception -> 0x013e }
            if (r12 == 0) goto L_0x00e6
            android.graphics.Rect r12 = r11.d     // Catch:{ Exception -> 0x013e }
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x013e }
            if (r12 != 0) goto L_0x00f4
        L_0x00e6:
            r11.d = r6     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map r13 = a((android.graphics.Rect) r6, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r12.<init>(r13)     // Catch:{ Exception -> 0x013e }
            r11.f = r12     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00f4:
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.h     // Catch:{ Exception -> 0x013e }
            if (r12 == 0) goto L_0x0103
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.h     // Catch:{ Exception -> 0x013e }
            boolean r12 = r0.equals(r12)     // Catch:{ Exception -> 0x013e }
            if (r12 != 0) goto L_0x0101
            goto L_0x0103
        L_0x0101:
            r8 = r7
            goto L_0x0105
        L_0x0103:
            r11.h = r0     // Catch:{ Exception -> 0x013e }
        L_0x0105:
            if (r8 == 0) goto L_0x0144
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map<java.lang.String, java.lang.Object> r13 = r11.h     // Catch:{ Exception -> 0x013e }
            r12.<init>(r13)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "screen"
            org.json.JSONObject r0 = r11.g     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "view"
            org.json.JSONObject r0 = r11.f     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "visible"
            org.json.JSONObject r0 = r11.c     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "maybe"
            org.json.JSONObject r0 = r11.c     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "visiblePercent"
            com.moat.analytics.mobile.mpub.z$c r0 = r11.b     // Catch:{ Exception -> 0x013e }
            double r2 = r0.b     // Catch:{ Exception -> 0x013e }
            java.lang.Double r0 = java.lang.Double.valueOf(r2)     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r1 = r12.toString()     // Catch:{ Exception -> 0x013e }
            r11.f1197a = r1     // Catch:{ Exception -> 0x013e }
            goto L_0x0144
        L_0x013e:
            r12 = move-exception
            com.moat.analytics.mobile.mpub.n.a(r12)
            r11.f1197a = r1
        L_0x0144:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.z.a(java.lang.String, android.view.View):void");
    }
}
