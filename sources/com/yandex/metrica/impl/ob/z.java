package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p;
import java.util.LinkedList;

public class z extends aa<af> {

    /* renamed from: a  reason: collision with root package name */
    private final bb f944a;
    private final ba b;
    private final az c;
    private final ap d;
    private final ax e;
    private final ak f;

    public z(t tVar) {
        this.f944a = new bb(tVar);
        this.b = new ba(tVar);
        this.c = new az(tVar);
        this.d = new ap(tVar);
        this.e = new ax(tVar);
        this.f = new ak(tVar);
    }

    /* access modifiers changed from: package-private */
    public x<af> a(int i) {
        LinkedList linkedList = new LinkedList();
        switch (AnonymousClass1.f945a[p.a.a(i).ordinal()]) {
            case 1:
                linkedList.add(this.e);
                break;
            case 2:
                linkedList.add(this.e);
                linkedList.add(this.d);
                break;
            case 3:
                linkedList.add(this.f944a);
                linkedList.add(this.b);
                linkedList.add(this.c);
                break;
            case 4:
            case 5:
                linkedList.add(this.d);
                break;
            case 6:
                linkedList.add(this.f);
                break;
        }
        return new w(linkedList);
    }

    /* renamed from: com.yandex.metrica.impl.ob.z$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f945a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.yandex.metrica.impl.p$a[] r0 = com.yandex.metrica.impl.p.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f945a = r0
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f945a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_START     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f945a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_SESSION_START_MANUALLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f945a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_INIT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f945a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_INIT_BACKGROUND     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f945a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.p.a.EVENT_TYPE_ACTIVITY_END     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.z.AnonymousClass1.<clinit>():void");
        }
    }
}
