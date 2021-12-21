package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zac();
    private final int zaa;
    private final HashMap<String, Integer> zab;
    private final SparseArray<String> zac;
    private final ArrayList<zaa> zad;

    StringToIntConverter(int i, ArrayList<zaa> arrayList) {
        this.zaa = i;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            zaa zaa2 = (zaa) obj;
            add(zaa2.zaa, zaa2.zab);
        }
    }

    public final int zaa() {
        return 7;
    }

    public final int zab() {
        return 0;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zad();
        final String zaa;
        final int zab;
        private final int zac;

        zaa(int i, String str, int i2) {
            this.zac = i;
            this.zaa = str;
            this.zab = i2;
        }

        zaa(String str, int i) {
            this.zac = 1;
            this.zaa = str;
            this.zab = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zac);
            SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zab);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public StringToIntConverter() {
        this.zaa = 1;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
    }

    public final StringToIntConverter add(String str, int i) {
        this.zab.put(str, Integer.valueOf(i));
        this.zac.put(i, str);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String next : this.zab.keySet()) {
            arrayList.add(new zaa(next, this.zab.get(next).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ Object zaa(Object obj) {
        String str = this.zac.get(((Integer) obj).intValue());
        return (str != null || !this.zab.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public final /* synthetic */ Object zab(Object obj) {
        Integer num = this.zab.get((String) obj);
        return num == null ? this.zab.get("gms_unknown") : num;
    }
}
