package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.mopub.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzr extends FastSafeParcelableJsonResponse implements Person {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
    private final Set<Integer> zzap;
    private String zzaq;
    private zza zzar;
    private String zzas;
    private String zzat;
    private int zzau;
    private zzb zzav;
    private String zzaw;
    private String zzax;
    private int zzay;
    private String zzaz;
    private zzc zzba;
    private boolean zzbb;
    private String zzbc;
    private zzd zzbd;
    private String zzbe;
    private int zzbf;
    private List<zze> zzbg;
    private List<zzf> zzbh;
    private int zzbi;
    private int zzbj;
    private String zzbk;
    private List<zzg> zzbl;
    private boolean zzbm;
    private String zzk;
    private final int zzw;

    public static final class zza extends FastSafeParcelableJsonResponse implements Person.AgeRange {
        public static final Parcelable.Creator<zza> CREATOR = new zzt();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private final Set<Integer> zzap;
        private int zzbn;
        private int zzbo;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("max", FastJsonResponse.Field.forInteger("max", 2));
            zzao.put("min", FastJsonResponse.Field.forInteger("min", 3));
        }

        public zza() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zza(Set<Integer> set, int i, int i2, int i3) {
            this.zzap = set;
            this.zzw = i;
            this.zzbn = i2;
            this.zzbo = i3;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zza zza = (zza) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zza.isFieldSet(next) || !getFieldValue(next).equals(zza.getFieldValue(next))) {
                        return false;
                    }
                } else if (zza.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            int i;
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                i = this.zzbn;
            } else if (safeParcelableFieldId == 3) {
                i = this.zzbo;
            } else {
                int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unknown safe parcelable id=");
                sb.append(safeParcelableFieldId2);
                throw new IllegalStateException(sb.toString());
            }
            return Integer.valueOf(i);
        }

        public final int getMax() {
            return this.zzbn;
        }

        public final int getMin() {
            return this.zzbo;
        }

        public final boolean hasMax() {
            return this.zzap.contains(2);
        }

        public final boolean hasMin() {
            return this.zzap.contains(3);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        /* access modifiers changed from: protected */
        public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzbn = i;
            } else if (safeParcelableFieldId == 3) {
                this.zzbo = i;
            } else {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be an int.");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeInt(parcel, 2, this.zzbn);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeInt(parcel, 3, this.zzbo);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zzb extends FastSafeParcelableJsonResponse implements Person.Cover {
        public static final Parcelable.Creator<zzb> CREATOR = new zzu();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private final Set<Integer> zzap;
        private zza zzbp;
        private C0046zzb zzbq;
        private int zzbr;
        private final int zzw;

        public static final class zza extends FastSafeParcelableJsonResponse implements Person.Cover.CoverInfo {
            public static final Parcelable.Creator<zza> CREATOR = new zzv();
            private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
            private final Set<Integer> zzap;
            private int zzbs;
            private int zzbt;
            private final int zzw;

            static {
                HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
                zzao = hashMap;
                hashMap.put("leftImageOffset", FastJsonResponse.Field.forInteger("leftImageOffset", 2));
                zzao.put("topImageOffset", FastJsonResponse.Field.forInteger("topImageOffset", 3));
            }

            public zza() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            zza(Set<Integer> set, int i, int i2, int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbs = i2;
                this.zzbt = i3;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof zza)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                zza zza = (zza) obj;
                for (FastJsonResponse.Field next : zzao.values()) {
                    if (isFieldSet(next)) {
                        if (!zza.isFieldSet(next) || !getFieldValue(next).equals(zza.getFieldValue(next))) {
                            return false;
                        }
                    } else if (zza.isFieldSet(next)) {
                        return false;
                    }
                }
                return true;
            }

            public final /* bridge */ /* synthetic */ Object freeze() {
                return this;
            }

            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            /* access modifiers changed from: protected */
            public final Object getFieldValue(FastJsonResponse.Field field) {
                int i;
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    i = this.zzbs;
                } else if (safeParcelableFieldId == 3) {
                    i = this.zzbt;
                } else {
                    int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId2);
                    throw new IllegalStateException(sb.toString());
                }
                return Integer.valueOf(i);
            }

            public final int getLeftImageOffset() {
                return this.zzbs;
            }

            public final int getTopImageOffset() {
                return this.zzbt;
            }

            public final boolean hasLeftImageOffset() {
                return this.zzap.contains(2);
            }

            public final boolean hasTopImageOffset() {
                return this.zzap.contains(3);
            }

            public final int hashCode() {
                int i = 0;
                for (FastJsonResponse.Field next : zzao.values()) {
                    if (isFieldSet(next)) {
                        i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                    }
                }
                return i;
            }

            public final boolean isDataValid() {
                return true;
            }

            /* access modifiers changed from: protected */
            public final boolean isFieldSet(FastJsonResponse.Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            /* access modifiers changed from: protected */
            public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    this.zzbs = i;
                } else if (safeParcelableFieldId == 3) {
                    this.zzbt = i;
                } else {
                    StringBuilder sb = new StringBuilder(52);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be an int.");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            public final void writeToParcel(Parcel parcel, int i) {
                int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set<Integer> set = this.zzap;
                if (set.contains(1)) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(2)) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbs);
                }
                if (set.contains(3)) {
                    SafeParcelWriter.writeInt(parcel, 3, this.zzbt);
                }
                SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            }
        }

        /* renamed from: com.google.android.gms.internal.plus.zzr$zzb$zzb  reason: collision with other inner class name */
        public static final class C0046zzb extends FastSafeParcelableJsonResponse implements Person.Cover.CoverPhoto {
            public static final Parcelable.Creator<C0046zzb> CREATOR = new zzw();
            private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
            private final Set<Integer> zzap;
            private int zzbu;
            private int zzbv;
            private String zzk;
            private final int zzw;

            static {
                HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
                zzao = hashMap;
                hashMap.put("height", FastJsonResponse.Field.forInteger("height", 2));
                zzao.put("url", FastJsonResponse.Field.forString("url", 3));
                zzao.put("width", FastJsonResponse.Field.forInteger("width", 4));
            }

            public C0046zzb() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            C0046zzb(Set<Integer> set, int i, int i2, String str, int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbu = i2;
                this.zzk = str;
                this.zzbv = i3;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof C0046zzb)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0046zzb zzb = (C0046zzb) obj;
                for (FastJsonResponse.Field next : zzao.values()) {
                    if (isFieldSet(next)) {
                        if (!zzb.isFieldSet(next) || !getFieldValue(next).equals(zzb.getFieldValue(next))) {
                            return false;
                        }
                    } else if (zzb.isFieldSet(next)) {
                        return false;
                    }
                }
                return true;
            }

            public final /* bridge */ /* synthetic */ Object freeze() {
                return this;
            }

            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            /* access modifiers changed from: protected */
            public final Object getFieldValue(FastJsonResponse.Field field) {
                int i;
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    i = this.zzbu;
                } else if (safeParcelableFieldId == 3) {
                    return this.zzk;
                } else {
                    if (safeParcelableFieldId == 4) {
                        i = this.zzbv;
                    } else {
                        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                        StringBuilder sb = new StringBuilder(38);
                        sb.append("Unknown safe parcelable id=");
                        sb.append(safeParcelableFieldId2);
                        throw new IllegalStateException(sb.toString());
                    }
                }
                return Integer.valueOf(i);
            }

            public final int getHeight() {
                return this.zzbu;
            }

            public final String getUrl() {
                return this.zzk;
            }

            public final int getWidth() {
                return this.zzbv;
            }

            public final boolean hasHeight() {
                return this.zzap.contains(2);
            }

            public final boolean hasUrl() {
                return this.zzap.contains(3);
            }

            public final boolean hasWidth() {
                return this.zzap.contains(4);
            }

            public final int hashCode() {
                int i = 0;
                for (FastJsonResponse.Field next : zzao.values()) {
                    if (isFieldSet(next)) {
                        i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                    }
                }
                return i;
            }

            public final boolean isDataValid() {
                return true;
            }

            /* access modifiers changed from: protected */
            public final boolean isFieldSet(FastJsonResponse.Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            /* access modifiers changed from: protected */
            public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    this.zzbu = i;
                } else if (safeParcelableFieldId == 4) {
                    this.zzbv = i;
                } else {
                    StringBuilder sb = new StringBuilder(52);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be an int.");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            /* access modifiers changed from: protected */
            public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 3) {
                    this.zzk = str2;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                }
                StringBuilder sb = new StringBuilder(54);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a String.");
                throw new IllegalArgumentException(sb.toString());
            }

            public final void writeToParcel(Parcel parcel, int i) {
                int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set<Integer> set = this.zzap;
                if (set.contains(1)) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(2)) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
                }
                if (set.contains(3)) {
                    SafeParcelWriter.writeString(parcel, 3, this.zzk, true);
                }
                if (set.contains(4)) {
                    SafeParcelWriter.writeInt(parcel, 4, this.zzbv);
                }
                SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            }
        }

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("coverInfo", FastJsonResponse.Field.forConcreteType("coverInfo", 2, zza.class));
            zzao.put("coverPhoto", FastJsonResponse.Field.forConcreteType("coverPhoto", 3, C0046zzb.class));
            zzao.put("layout", FastJsonResponse.Field.withConverter("layout", 4, new StringToIntConverter().add("banner", 0), false));
        }

        public zzb() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zzb(Set<Integer> set, int i, zza zza2, C0046zzb zzb, int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzbp = zza2;
            this.zzbq = zzb;
            this.zzbr = i2;
        }

        public final <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzbp = (zza) t;
            } else if (safeParcelableFieldId == 3) {
                this.zzbq = (C0046zzb) t;
            } else {
                String canonicalName = t.getClass().getCanonicalName();
                StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 62);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not a known custom type.  Found ");
                sb.append(canonicalName);
                sb.append(".");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzb zzb = (zzb) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zzb.isFieldSet(next) || !getFieldValue(next).equals(zzb.getFieldValue(next))) {
                        return false;
                    }
                } else if (zzb.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final Person.Cover.CoverInfo getCoverInfo() {
            return this.zzbp;
        }

        public final Person.Cover.CoverPhoto getCoverPhoto() {
            return this.zzbq;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                return this.zzbp;
            }
            if (safeParcelableFieldId == 3) {
                return this.zzbq;
            }
            if (safeParcelableFieldId == 4) {
                return Integer.valueOf(this.zzbr);
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        public final int getLayout() {
            return this.zzbr;
        }

        public final boolean hasCoverInfo() {
            return this.zzap.contains(2);
        }

        public final boolean hasCoverPhoto() {
            return this.zzap.contains(3);
        }

        public final boolean hasLayout() {
            return this.zzap.contains(4);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        /* access modifiers changed from: protected */
        public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                this.zzbr = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeParcelable(parcel, 2, this.zzbp, i, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeParcelable(parcel, 3, this.zzbq, i, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeInt(parcel, 4, this.zzbr);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zzc extends FastSafeParcelableJsonResponse implements Person.Image {
        public static final Parcelable.Creator<zzc> CREATOR = new zzx();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private final Set<Integer> zzap;
        private String zzk;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("url", FastJsonResponse.Field.forString("url", 2));
        }

        public zzc() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        public zzc(String str) {
            HashSet hashSet = new HashSet();
            this.zzap = hashSet;
            this.zzw = 1;
            this.zzk = str;
            hashSet.add(2);
        }

        zzc(Set<Integer> set, int i, String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzk = str;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zzc.isFieldSet(next) || !getFieldValue(next).equals(zzc.getFieldValue(next))) {
                        return false;
                    }
                } else if (zzc.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            if (field.getSafeParcelableFieldId() == 2) {
                return this.zzk;
            }
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId);
            throw new IllegalStateException(sb.toString());
        }

        public final String getUrl() {
            return this.zzk;
        }

        public final boolean hasUrl() {
            return this.zzap.contains(2);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        /* access modifiers changed from: protected */
        public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzk = str2;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(54);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a String.");
            throw new IllegalArgumentException(sb.toString());
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzk, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zzd extends FastSafeParcelableJsonResponse implements Person.Name {
        public static final Parcelable.Creator<zzd> CREATOR = new zzy();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private final Set<Integer> zzap;
        private String zzbw;
        private String zzbx;
        private String zzby;
        private String zzbz;
        private String zzca;
        private String zzcb;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("familyName", FastJsonResponse.Field.forString("familyName", 2));
            zzao.put("formatted", FastJsonResponse.Field.forString("formatted", 3));
            zzao.put("givenName", FastJsonResponse.Field.forString("givenName", 4));
            zzao.put("honorificPrefix", FastJsonResponse.Field.forString("honorificPrefix", 5));
            zzao.put("honorificSuffix", FastJsonResponse.Field.forString("honorificSuffix", 6));
            zzao.put("middleName", FastJsonResponse.Field.forString("middleName", 7));
        }

        public zzd() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zzd(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.zzap = set;
            this.zzw = i;
            this.zzbw = str;
            this.zzbx = str2;
            this.zzby = str3;
            this.zzbz = str4;
            this.zzca = str5;
            this.zzcb = str6;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzd zzd = (zzd) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zzd.isFieldSet(next) || !getFieldValue(next).equals(zzd.getFieldValue(next))) {
                        return false;
                    }
                } else if (zzd.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final String getFamilyName() {
            return this.zzbw;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzbw;
                case 3:
                    return this.zzbx;
                case 4:
                    return this.zzby;
                case 5:
                    return this.zzbz;
                case 6:
                    return this.zzca;
                case 7:
                    return this.zzcb;
                default:
                    int safeParcelableFieldId = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId);
                    throw new IllegalStateException(sb.toString());
            }
        }

        public final String getFormatted() {
            return this.zzbx;
        }

        public final String getGivenName() {
            return this.zzby;
        }

        public final String getHonorificPrefix() {
            return this.zzbz;
        }

        public final String getHonorificSuffix() {
            return this.zzca;
        }

        public final String getMiddleName() {
            return this.zzcb;
        }

        public final boolean hasFamilyName() {
            return this.zzap.contains(2);
        }

        public final boolean hasFormatted() {
            return this.zzap.contains(3);
        }

        public final boolean hasGivenName() {
            return this.zzap.contains(4);
        }

        public final boolean hasHonorificPrefix() {
            return this.zzap.contains(5);
        }

        public final boolean hasHonorificSuffix() {
            return this.zzap.contains(6);
        }

        public final boolean hasMiddleName() {
            return this.zzap.contains(7);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        /* access modifiers changed from: protected */
        public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzbw = str2;
                    break;
                case 3:
                    this.zzbx = str2;
                    break;
                case 4:
                    this.zzby = str2;
                    break;
                case 5:
                    this.zzbz = str2;
                    break;
                case 6:
                    this.zzca = str2;
                    break;
                case 7:
                    this.zzcb = str2;
                    break;
                default:
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzbw, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.zzbx, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.zzby, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzbz, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeString(parcel, 6, this.zzca, true);
            }
            if (set.contains(7)) {
                SafeParcelWriter.writeString(parcel, 7, this.zzcb, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zze extends FastSafeParcelableJsonResponse implements Person.Organizations {
        public static final Parcelable.Creator<zze> CREATOR = new zzz();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private String mName;
        private final Set<Integer> zzap;
        private String zzcc;
        private String zzcd;
        private String zzce;
        private String zzcf;
        private boolean zzcg;
        private String zzch;
        private String zzci;
        private int zzcj;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("department", FastJsonResponse.Field.forString("department", 2));
            zzao.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FastJsonResponse.Field.forString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, 3));
            zzao.put("endDate", FastJsonResponse.Field.forString("endDate", 4));
            zzao.put("location", FastJsonResponse.Field.forString("location", 5));
            zzao.put("name", FastJsonResponse.Field.forString("name", 6));
            zzao.put("primary", FastJsonResponse.Field.forBoolean("primary", 7));
            zzao.put("startDate", FastJsonResponse.Field.forString("startDate", 8));
            zzao.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, FastJsonResponse.Field.forString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, 9));
            zzao.put("type", FastJsonResponse.Field.withConverter("type", 10, new StringToIntConverter().add("work", 0).add("school", 1), false));
        }

        public zze() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zze(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzcc = str;
            this.zzcd = str2;
            this.zzce = str3;
            this.zzcf = str4;
            this.mName = str5;
            this.zzcg = z;
            this.zzch = str6;
            this.zzci = str7;
            this.zzcj = i2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zze)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zze zze = (zze) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zze.isFieldSet(next) || !getFieldValue(next).equals(zze.getFieldValue(next))) {
                        return false;
                    }
                } else if (zze.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final String getDepartment() {
            return this.zzcc;
        }

        public final String getDescription() {
            return this.zzcd;
        }

        public final String getEndDate() {
            return this.zzce;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzcc;
                case 3:
                    return this.zzcd;
                case 4:
                    return this.zzce;
                case 5:
                    return this.zzcf;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.zzcg);
                case 8:
                    return this.zzch;
                case 9:
                    return this.zzci;
                case 10:
                    return Integer.valueOf(this.zzcj);
                default:
                    int safeParcelableFieldId = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId);
                    throw new IllegalStateException(sb.toString());
            }
        }

        public final String getLocation() {
            return this.zzcf;
        }

        public final String getName() {
            return this.mName;
        }

        public final String getStartDate() {
            return this.zzch;
        }

        public final String getTitle() {
            return this.zzci;
        }

        public final int getType() {
            return this.zzcj;
        }

        public final boolean hasDepartment() {
            return this.zzap.contains(2);
        }

        public final boolean hasDescription() {
            return this.zzap.contains(3);
        }

        public final boolean hasEndDate() {
            return this.zzap.contains(4);
        }

        public final boolean hasLocation() {
            return this.zzap.contains(5);
        }

        public final boolean hasName() {
            return this.zzap.contains(6);
        }

        public final boolean hasPrimary() {
            return this.zzap.contains(7);
        }

        public final boolean hasStartDate() {
            return this.zzap.contains(8);
        }

        public final boolean hasTitle() {
            return this.zzap.contains(9);
        }

        public final boolean hasType() {
            return this.zzap.contains(10);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public final boolean isPrimary() {
            return this.zzcg;
        }

        /* access modifiers changed from: protected */
        public final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 7) {
                this.zzcg = z;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(55);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a boolean.");
            throw new IllegalArgumentException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 10) {
                this.zzcj = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzcc = str2;
                    break;
                case 3:
                    this.zzcd = str2;
                    break;
                case 4:
                    this.zzce = str2;
                    break;
                case 5:
                    this.zzcf = str2;
                    break;
                case 6:
                    this.mName = str2;
                    break;
                case 8:
                    this.zzch = str2;
                    break;
                case 9:
                    this.zzci = str2;
                    break;
                default:
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzcc, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.zzcd, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.zzce, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzcf, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeString(parcel, 6, this.mName, true);
            }
            if (set.contains(7)) {
                SafeParcelWriter.writeBoolean(parcel, 7, this.zzcg);
            }
            if (set.contains(8)) {
                SafeParcelWriter.writeString(parcel, 8, this.zzch, true);
            }
            if (set.contains(9)) {
                SafeParcelWriter.writeString(parcel, 9, this.zzci, true);
            }
            if (set.contains(10)) {
                SafeParcelWriter.writeInt(parcel, 10, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zzf extends FastSafeParcelableJsonResponse implements Person.PlacesLived {
        public static final Parcelable.Creator<zzf> CREATOR = new zzaa();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private String mValue;
        private final Set<Integer> zzap;
        private boolean zzcg;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put("primary", FastJsonResponse.Field.forBoolean("primary", 2));
            zzao.put("value", FastJsonResponse.Field.forString("value", 3));
        }

        public zzf() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zzf(Set<Integer> set, int i, boolean z, String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzcg = z;
            this.mValue = str;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzf)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzf zzf = (zzf) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zzf.isFieldSet(next) || !getFieldValue(next).equals(zzf.getFieldValue(next))) {
                        return false;
                    }
                } else if (zzf.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                return Boolean.valueOf(this.zzcg);
            }
            if (safeParcelableFieldId == 3) {
                return this.mValue;
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        public final String getValue() {
            return this.mValue;
        }

        public final boolean hasPrimary() {
            return this.zzap.contains(2);
        }

        public final boolean hasValue() {
            return this.zzap.contains(3);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public final boolean isPrimary() {
            return this.zzcg;
        }

        /* access modifiers changed from: protected */
        public final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzcg = z;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(55);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a boolean.");
            throw new IllegalArgumentException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 3) {
                this.mValue = str2;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(54);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a String.");
            throw new IllegalArgumentException(sb.toString());
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeBoolean(parcel, 2, this.zzcg);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.mValue, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static final class zzg extends FastSafeParcelableJsonResponse implements Person.Urls {
        public static final Parcelable.Creator<zzg> CREATOR = new zzab();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;
        private String mValue;
        private final Set<Integer> zzap;
        private int zzcj;
        private String zzck;
        private final int zzcl;
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            zzao = hashMap;
            hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, FastJsonResponse.Field.forString(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
            zzao.put("type", FastJsonResponse.Field.withConverter("type", 6, new StringToIntConverter().add("home", 0).add("work", 1).add("blog", 2).add(Scopes.PROFILE, 3).add("other", 4).add("otherProfile", 5).add("contributor", 6).add("website", 7), false));
            zzao.put("value", FastJsonResponse.Field.forString("value", 4));
        }

        public zzg() {
            this.zzcl = 4;
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        zzg(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
            this.zzcl = 4;
            this.zzap = set;
            this.zzw = i;
            this.zzck = str;
            this.zzcj = i2;
            this.mValue = str2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzg)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzg zzg = (zzg) obj;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    if (!zzg.isFieldSet(next) || !getFieldValue(next).equals(zzg.getFieldValue(next))) {
                        return false;
                    }
                } else if (zzg.isFieldSet(next)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            return this;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        /* access modifiers changed from: protected */
        public final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                return this.mValue;
            }
            if (safeParcelableFieldId == 5) {
                return this.zzck;
            }
            if (safeParcelableFieldId == 6) {
                return Integer.valueOf(this.zzcj);
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        public final String getLabel() {
            return this.zzck;
        }

        public final int getType() {
            return this.zzcj;
        }

        public final String getValue() {
            return this.mValue;
        }

        public final boolean hasLabel() {
            return this.zzap.contains(5);
        }

        public final boolean hasType() {
            return this.zzap.contains(6);
        }

        public final boolean hasValue() {
            return this.zzap.contains(4);
        }

        public final int hashCode() {
            int i = 0;
            for (FastJsonResponse.Field next : zzao.values()) {
                if (isFieldSet(next)) {
                    i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
                }
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: protected */
        public final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        /* access modifiers changed from: protected */
        public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 6) {
                this.zzcj = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                return;
            }
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                this.mValue = str2;
            } else if (safeParcelableFieldId == 5) {
                this.zzck = str2;
            } else {
                StringBuilder sb = new StringBuilder(54);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a String.");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeInt(parcel, 3, 4);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.mValue, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzck, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeInt(parcel, 6, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
        zzao = hashMap;
        hashMap.put("aboutMe", FastJsonResponse.Field.forString("aboutMe", 2));
        zzao.put("ageRange", FastJsonResponse.Field.forConcreteType("ageRange", 3, zza.class));
        zzao.put("birthday", FastJsonResponse.Field.forString("birthday", 4));
        zzao.put("braggingRights", FastJsonResponse.Field.forString("braggingRights", 5));
        zzao.put("circledByCount", FastJsonResponse.Field.forInteger("circledByCount", 6));
        zzao.put("cover", FastJsonResponse.Field.forConcreteType("cover", 7, zzb.class));
        zzao.put("currentLocation", FastJsonResponse.Field.forString("currentLocation", 8));
        zzao.put("displayName", FastJsonResponse.Field.forString("displayName", 9));
        zzao.put("gender", FastJsonResponse.Field.withConverter("gender", 12, new StringToIntConverter().add("male", 0).add("female", 1).add("other", 2), false));
        zzao.put("id", FastJsonResponse.Field.forString("id", 14));
        zzao.put("image", FastJsonResponse.Field.forConcreteType("image", 15, zzc.class));
        zzao.put("isPlusUser", FastJsonResponse.Field.forBoolean("isPlusUser", 16));
        zzao.put("language", FastJsonResponse.Field.forString("language", 18));
        zzao.put("name", FastJsonResponse.Field.forConcreteType("name", 19, zzd.class));
        zzao.put("nickname", FastJsonResponse.Field.forString("nickname", 20));
        zzao.put("objectType", FastJsonResponse.Field.withConverter("objectType", 21, new StringToIntConverter().add("person", 0).add("page", 1), false));
        zzao.put("organizations", FastJsonResponse.Field.forConcreteTypeArray("organizations", 22, zze.class));
        zzao.put("placesLived", FastJsonResponse.Field.forConcreteTypeArray("placesLived", 23, zzf.class));
        zzao.put("plusOneCount", FastJsonResponse.Field.forInteger("plusOneCount", 24));
        zzao.put("relationshipStatus", FastJsonResponse.Field.withConverter("relationshipStatus", 25, new StringToIntConverter().add("single", 0).add("in_a_relationship", 1).add("engaged", 2).add("married", 3).add("its_complicated", 4).add("open_relationship", 5).add("widowed", 6).add("in_domestic_partnership", 7).add("in_civil_union", 8), false));
        zzao.put("tagline", FastJsonResponse.Field.forString("tagline", 26));
        zzao.put("url", FastJsonResponse.Field.forString("url", 27));
        zzao.put(Constants.VIDEO_TRACKING_URLS_KEY, FastJsonResponse.Field.forConcreteTypeArray(Constants.VIDEO_TRACKING_URLS_KEY, 28, zzg.class));
        zzao.put("verified", FastJsonResponse.Field.forBoolean("verified", 29));
    }

    public zzr() {
        this.zzw = 1;
        this.zzap = new HashSet();
    }

    public zzr(String str, String str2, zzc zzc2, int i, String str3) {
        this.zzw = 1;
        HashSet hashSet = new HashSet();
        this.zzap = hashSet;
        this.zzax = str;
        hashSet.add(9);
        this.zzaz = str2;
        this.zzap.add(14);
        this.zzba = zzc2;
        this.zzap.add(15);
        this.zzbf = i;
        this.zzap.add(21);
        this.zzk = str3;
        this.zzap.add(27);
    }

    zzr(Set<Integer> set, int i, String str, zza zza2, String str2, String str3, int i2, zzb zzb2, String str4, String str5, int i3, String str6, zzc zzc2, boolean z, String str7, zzd zzd2, String str8, int i4, List<zze> list, List<zzf> list2, int i5, int i6, String str9, String str10, List<zzg> list3, boolean z2) {
        this.zzap = set;
        this.zzw = i;
        this.zzaq = str;
        this.zzar = zza2;
        this.zzas = str2;
        this.zzat = str3;
        this.zzau = i2;
        this.zzav = zzb2;
        this.zzaw = str4;
        this.zzax = str5;
        this.zzay = i3;
        this.zzaz = str6;
        this.zzba = zzc2;
        this.zzbb = z;
        this.zzbc = str7;
        this.zzbd = zzd2;
        this.zzbe = str8;
        this.zzbf = i4;
        this.zzbg = list;
        this.zzbh = list2;
        this.zzbi = i5;
        this.zzbj = i6;
        this.zzbk = str9;
        this.zzk = str10;
        this.zzbl = list3;
        this.zzbm = z2;
    }

    public static zzr zza(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzr createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<T> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 22) {
            this.zzbg = arrayList;
        } else if (safeParcelableFieldId == 23) {
            this.zzbh = arrayList;
        } else if (safeParcelableFieldId == 28) {
            this.zzbl = arrayList;
        } else {
            String canonicalName = arrayList.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 71);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not a known array of custom type.  Found ");
            sb.append(canonicalName);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zzar = (zza) t;
        } else if (safeParcelableFieldId == 7) {
            this.zzav = (zzb) t;
        } else if (safeParcelableFieldId == 15) {
            this.zzba = (zzc) t;
        } else if (safeParcelableFieldId == 19) {
            this.zzbd = (zzd) t;
        } else {
            String canonicalName = t.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 62);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not a known custom type.  Found ");
            sb.append(canonicalName);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzr)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzr zzr = (zzr) obj;
        for (FastJsonResponse.Field next : zzao.values()) {
            if (isFieldSet(next)) {
                if (!zzr.isFieldSet(next) || !getFieldValue(next).equals(zzr.getFieldValue(next))) {
                    return false;
                }
            } else if (zzr.isFieldSet(next)) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }

    public final String getAboutMe() {
        return this.zzaq;
    }

    public final Person.AgeRange getAgeRange() {
        return this.zzar;
    }

    public final String getBirthday() {
        return this.zzas;
    }

    public final String getBraggingRights() {
        return this.zzat;
    }

    public final int getCircledByCount() {
        return this.zzau;
    }

    public final Person.Cover getCover() {
        return this.zzav;
    }

    public final String getCurrentLocation() {
        return this.zzaw;
    }

    public final String getDisplayName() {
        return this.zzax;
    }

    public final /* synthetic */ Map getFieldMappings() {
        return zzao;
    }

    /* access modifiers changed from: protected */
    public final Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 2:
                return this.zzaq;
            case 3:
                return this.zzar;
            case 4:
                return this.zzas;
            case 5:
                return this.zzat;
            case 6:
                return Integer.valueOf(this.zzau);
            case 7:
                return this.zzav;
            case 8:
                return this.zzaw;
            case 9:
                return this.zzax;
            case 12:
                return Integer.valueOf(this.zzay);
            case 14:
                return this.zzaz;
            case 15:
                return this.zzba;
            case 16:
                return Boolean.valueOf(this.zzbb);
            case 18:
                return this.zzbc;
            case 19:
                return this.zzbd;
            case 20:
                return this.zzbe;
            case 21:
                return Integer.valueOf(this.zzbf);
            case 22:
                return this.zzbg;
            case 23:
                return this.zzbh;
            case 24:
                return Integer.valueOf(this.zzbi);
            case 25:
                return Integer.valueOf(this.zzbj);
            case 26:
                return this.zzbk;
            case 27:
                return this.zzk;
            case 28:
                return this.zzbl;
            case 29:
                return Boolean.valueOf(this.zzbm);
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unknown safe parcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
        }
    }

    public final int getGender() {
        return this.zzay;
    }

    public final String getId() {
        return this.zzaz;
    }

    public final Person.Image getImage() {
        return this.zzba;
    }

    public final String getLanguage() {
        return this.zzbc;
    }

    public final Person.Name getName() {
        return this.zzbd;
    }

    public final String getNickname() {
        return this.zzbe;
    }

    public final int getObjectType() {
        return this.zzbf;
    }

    public final List<Person.Organizations> getOrganizations() {
        return (ArrayList) this.zzbg;
    }

    public final List<Person.PlacesLived> getPlacesLived() {
        return (ArrayList) this.zzbh;
    }

    public final int getPlusOneCount() {
        return this.zzbi;
    }

    public final int getRelationshipStatus() {
        return this.zzbj;
    }

    public final String getTagline() {
        return this.zzbk;
    }

    public final String getUrl() {
        return this.zzk;
    }

    public final List<Person.Urls> getUrls() {
        return (ArrayList) this.zzbl;
    }

    public final boolean hasAboutMe() {
        return this.zzap.contains(2);
    }

    public final boolean hasAgeRange() {
        return this.zzap.contains(3);
    }

    public final boolean hasBirthday() {
        return this.zzap.contains(4);
    }

    public final boolean hasBraggingRights() {
        return this.zzap.contains(5);
    }

    public final boolean hasCircledByCount() {
        return this.zzap.contains(6);
    }

    public final boolean hasCover() {
        return this.zzap.contains(7);
    }

    public final boolean hasCurrentLocation() {
        return this.zzap.contains(8);
    }

    public final boolean hasDisplayName() {
        return this.zzap.contains(9);
    }

    public final boolean hasGender() {
        return this.zzap.contains(12);
    }

    public final boolean hasId() {
        return this.zzap.contains(14);
    }

    public final boolean hasImage() {
        return this.zzap.contains(15);
    }

    public final boolean hasIsPlusUser() {
        return this.zzap.contains(16);
    }

    public final boolean hasLanguage() {
        return this.zzap.contains(18);
    }

    public final boolean hasName() {
        return this.zzap.contains(19);
    }

    public final boolean hasNickname() {
        return this.zzap.contains(20);
    }

    public final boolean hasObjectType() {
        return this.zzap.contains(21);
    }

    public final boolean hasOrganizations() {
        return this.zzap.contains(22);
    }

    public final boolean hasPlacesLived() {
        return this.zzap.contains(23);
    }

    public final boolean hasPlusOneCount() {
        return this.zzap.contains(24);
    }

    public final boolean hasRelationshipStatus() {
        return this.zzap.contains(25);
    }

    public final boolean hasTagline() {
        return this.zzap.contains(26);
    }

    public final boolean hasUrl() {
        return this.zzap.contains(27);
    }

    public final boolean hasUrls() {
        return this.zzap.contains(28);
    }

    public final boolean hasVerified() {
        return this.zzap.contains(29);
    }

    public final int hashCode() {
        int i = 0;
        for (FastJsonResponse.Field next : zzao.values()) {
            if (isFieldSet(next)) {
                i = i + next.getSafeParcelableFieldId() + getFieldValue(next).hashCode();
            }
        }
        return i;
    }

    public final boolean isDataValid() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    public final boolean isPlusUser() {
        return this.zzbb;
    }

    public final boolean isVerified() {
        return this.zzbm;
    }

    /* access modifiers changed from: protected */
    public final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 16) {
            this.zzbb = z;
        } else if (safeParcelableFieldId == 29) {
            this.zzbm = z;
        } else {
            StringBuilder sb = new StringBuilder(55);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a boolean.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    /* access modifiers changed from: protected */
    public final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 6) {
            this.zzau = i;
        } else if (safeParcelableFieldId == 12) {
            this.zzay = i;
        } else if (safeParcelableFieldId == 21) {
            this.zzbf = i;
        } else if (safeParcelableFieldId == 24) {
            this.zzbi = i;
        } else if (safeParcelableFieldId == 25) {
            this.zzbj = i;
        } else {
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    /* access modifiers changed from: protected */
    public final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.zzaq = str2;
        } else if (safeParcelableFieldId == 14) {
            this.zzaz = str2;
        } else if (safeParcelableFieldId == 18) {
            this.zzbc = str2;
        } else if (safeParcelableFieldId == 20) {
            this.zzbe = str2;
        } else if (safeParcelableFieldId == 4) {
            this.zzas = str2;
        } else if (safeParcelableFieldId == 5) {
            this.zzat = str2;
        } else if (safeParcelableFieldId == 8) {
            this.zzaw = str2;
        } else if (safeParcelableFieldId == 9) {
            this.zzax = str2;
        } else if (safeParcelableFieldId == 26) {
            this.zzbk = str2;
        } else if (safeParcelableFieldId == 27) {
            this.zzk = str2;
        } else {
            StringBuilder sb = new StringBuilder(54);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be a String.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzap;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzw);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.zzaq, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeParcelable(parcel, 3, this.zzar, i, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.zzas, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzat, true);
        }
        if (set.contains(6)) {
            SafeParcelWriter.writeInt(parcel, 6, this.zzau);
        }
        if (set.contains(7)) {
            SafeParcelWriter.writeParcelable(parcel, 7, this.zzav, i, true);
        }
        if (set.contains(8)) {
            SafeParcelWriter.writeString(parcel, 8, this.zzaw, true);
        }
        if (set.contains(9)) {
            SafeParcelWriter.writeString(parcel, 9, this.zzax, true);
        }
        if (set.contains(12)) {
            SafeParcelWriter.writeInt(parcel, 12, this.zzay);
        }
        if (set.contains(14)) {
            SafeParcelWriter.writeString(parcel, 14, this.zzaz, true);
        }
        if (set.contains(15)) {
            SafeParcelWriter.writeParcelable(parcel, 15, this.zzba, i, true);
        }
        if (set.contains(16)) {
            SafeParcelWriter.writeBoolean(parcel, 16, this.zzbb);
        }
        if (set.contains(18)) {
            SafeParcelWriter.writeString(parcel, 18, this.zzbc, true);
        }
        if (set.contains(19)) {
            SafeParcelWriter.writeParcelable(parcel, 19, this.zzbd, i, true);
        }
        if (set.contains(20)) {
            SafeParcelWriter.writeString(parcel, 20, this.zzbe, true);
        }
        if (set.contains(21)) {
            SafeParcelWriter.writeInt(parcel, 21, this.zzbf);
        }
        if (set.contains(22)) {
            SafeParcelWriter.writeTypedList(parcel, 22, this.zzbg, true);
        }
        if (set.contains(23)) {
            SafeParcelWriter.writeTypedList(parcel, 23, this.zzbh, true);
        }
        if (set.contains(24)) {
            SafeParcelWriter.writeInt(parcel, 24, this.zzbi);
        }
        if (set.contains(25)) {
            SafeParcelWriter.writeInt(parcel, 25, this.zzbj);
        }
        if (set.contains(26)) {
            SafeParcelWriter.writeString(parcel, 26, this.zzbk, true);
        }
        if (set.contains(27)) {
            SafeParcelWriter.writeString(parcel, 27, this.zzk, true);
        }
        if (set.contains(28)) {
            SafeParcelWriter.writeTypedList(parcel, 28, this.zzbl, true);
        }
        if (set.contains(29)) {
            SafeParcelWriter.writeBoolean(parcel, 29, this.zzbm);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
