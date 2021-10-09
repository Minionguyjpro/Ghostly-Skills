package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_TransportContext extends TransportContext {
    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    private AutoValue_TransportContext(String str, byte[] bArr, Priority priority2) {
        this.backendName = str;
        this.extras = bArr;
        this.priority = priority2;
    }

    public String getBackendName() {
        return this.backendName;
    }

    public byte[] getExtras() {
        return this.extras;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransportContext)) {
            return false;
        }
        TransportContext transportContext = (TransportContext) obj;
        if (this.backendName.equals(transportContext.getBackendName())) {
            if (!Arrays.equals(this.extras, transportContext instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext) transportContext).extras : transportContext.getExtras()) || !this.priority.equals(transportContext.getPriority())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.backendName.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003) ^ this.priority.hashCode();
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        Builder() {
        }

        public TransportContext.Builder setBackendName(String str) {
            if (str != null) {
                this.backendName = str;
                return this;
            }
            throw new NullPointerException("Null backendName");
        }

        public TransportContext.Builder setExtras(byte[] bArr) {
            this.extras = bArr;
            return this;
        }

        public TransportContext.Builder setPriority(Priority priority2) {
            if (priority2 != null) {
                this.priority = priority2;
                return this;
            }
            throw new NullPointerException("Null priority");
        }

        public TransportContext build() {
            String str = "";
            if (this.backendName == null) {
                str = str + " backendName";
            }
            if (this.priority == null) {
                str = str + " priority";
            }
            if (str.isEmpty()) {
                return new AutoValue_TransportContext(this.backendName, this.extras, this.priority);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
