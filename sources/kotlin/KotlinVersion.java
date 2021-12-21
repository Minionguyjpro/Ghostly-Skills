package kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: KotlinVersion.kt */
public final class KotlinVersion implements Comparable<KotlinVersion> {
    public static final KotlinVersion CURRENT = new KotlinVersion(1, 3, 61);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int major;
    private final int minor;
    private final int patch;
    private final int version;

    public KotlinVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
        this.version = versionOf(i, i2, i3);
    }

    private final int versionOf(int i, int i2, int i3) {
        if (i >= 0 && 255 >= i && i2 >= 0 && 255 >= i2 && i3 >= 0 && 255 >= i3) {
            return (i << 16) + (i2 << 8) + i3;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i + '.' + i2 + '.' + i3).toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append('.');
        sb.append(this.minor);
        sb.append('.');
        sb.append(this.patch);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KotlinVersion)) {
            obj = null;
        }
        KotlinVersion kotlinVersion = (KotlinVersion) obj;
        if (kotlinVersion == null || this.version != kotlinVersion.version) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.version;
    }

    public int compareTo(KotlinVersion kotlinVersion) {
        Intrinsics.checkParameterIsNotNull(kotlinVersion, "other");
        return this.version - kotlinVersion.version;
    }

    /* compiled from: KotlinVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
