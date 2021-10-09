package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p;
import java.util.LinkedList;

public class y extends aa<af> {

    /* renamed from: a  reason: collision with root package name */
    private final ao f942a;
    private final ae b;
    private final am c;
    private final ar d;
    private final at e;
    private final aw f;
    private final bb g;
    private final au h;
    private final as i;
    private final av j;
    private final an k;
    private final aq l;
    private final ad m;
    private final ac n;
    private final ah o;
    private final aj p;

    public y(t tVar) {
        this.f942a = new ao(tVar);
        this.b = new ae(tVar);
        this.c = new am(tVar);
        this.d = new ar(tVar);
        this.e = new at(tVar);
        this.f = new aw(tVar);
        this.g = new bb(tVar);
        this.h = new au(tVar);
        this.i = new as(tVar);
        this.j = new av(tVar);
        this.k = new an(tVar);
        this.l = new aq(tVar);
        this.m = new ad(tVar);
        this.n = new ac(tVar);
        this.o = new ah(tVar);
        this.p = new aj(tVar);
    }

    /* access modifiers changed from: package-private */
    public x<af> a(int i2) {
        LinkedList linkedList = new LinkedList();
        p.a a2 = p.a.a(i2);
        if (p.b(a2)) {
            linkedList.add(this.j);
        }
        if (p.a(a2)) {
            linkedList.add(this.e);
        }
        switch (AnonymousClass1.f943a[a2.ordinal()]) {
            case 1:
                linkedList.add(this.o);
                linkedList.add(this.f);
                break;
            case 2:
                linkedList.add(this.f942a);
                break;
            case 3:
                linkedList.add(this.f942a);
                linkedList.add(this.d);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                linkedList.add(this.d);
                break;
            case 9:
                linkedList.add(this.f);
                linkedList.add(this.f942a);
                break;
            case 10:
                linkedList.add(this.c);
                break;
            case 11:
                linkedList.add(this.p);
                break;
            case 12:
            case 13:
                linkedList.add(this.c);
                linkedList.add(this.d);
                linkedList.add(this.b);
                linkedList.add(this.g);
                break;
            case 14:
                linkedList.add(this.f);
                linkedList.add(this.f942a);
                break;
            case 15:
                linkedList.add(this.h);
                break;
            case 16:
                linkedList.add(this.i);
                break;
            case 17:
                linkedList.add(this.l);
                break;
            case 18:
                linkedList.add(this.m);
                break;
            case 19:
                linkedList.add(this.n);
                break;
        }
        if (p.c(a2)) {
            linkedList.add(this.k);
        }
        return new w(linkedList);
    }

    /* renamed from: com.yandex.metrica.impl.ob.y$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f943a;

        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.yandex.metrica.impl.p$a[] r0 = com.yandex.metrica.impl.p.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f943a = r0
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_ACTIVATION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_START     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_REGULAR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_USER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_REFERRER_DEPRECATED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_STATBOX     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_CUSTOM_EVENT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_APP_OPEN     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x006c }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_PURGE_BUFFER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_NATIVE_CRASH     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x009c }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_IDENTITY     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_SET_USER_INFO     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_REPORT_USER_INFO     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_REFERRER_RECEIVED     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = f943a     // Catch:{ NoSuchFieldError -> 0x00e4 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.y.AnonymousClass1.<clinit>():void");
        }
    }
}
