package com.appnext.core;

public final class b {
    private String cat = "";
    private int cnt;
    private String fM = "";
    private int fN;
    private int fO;
    private String pbk = "";

    public b(Ad ad) {
        this.fM = ad.getPlacementID();
        this.cat = ad.getCategories();
        this.pbk = ad.getPostback();
        this.fN = ad.getMinVideoLength();
        this.fO = ad.getMaxVideoLength();
        this.cnt = ad.getCount();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isInstance(obj) && !obj.getClass().isInstance(this)) {
            return false;
        }
        if (!(obj instanceof b)) {
            return super.equals(obj);
        }
        b bVar = (b) obj;
        return bVar.fM.equals(this.fM) && bVar.cat.equals(this.cat) && bVar.pbk.equals(this.pbk) && bVar.fN == this.fN && bVar.fO == this.fO && bVar.cnt == this.cnt;
    }

    public final int hashCode() {
        return (((((((((this.fM.hashCode() * 31) + this.cat.hashCode()) * 31) + this.pbk.hashCode()) * 31) + this.fN) * 31) + this.fO) * 31) + this.cnt;
    }
}
