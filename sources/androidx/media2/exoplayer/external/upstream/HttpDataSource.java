package androidx.media2.exoplayer.external.upstream;

import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.util.Predicate;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HttpDataSource extends DataSource {
    public static final Predicate<String> REJECT_PAYWALL_TYPES = HttpDataSource$$Lambda$0.$instance;

    public interface Factory extends DataSource.Factory {
    }

    public static final class RequestProperties {
        private final Map<String, String> requestProperties = new HashMap();
        private Map<String, String> requestPropertiesSnapshot;

        public synchronized Map<String, String> getSnapshot() {
            if (this.requestPropertiesSnapshot == null) {
                this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
            }
            return this.requestPropertiesSnapshot;
        }
    }

    public static abstract class BaseFactory implements Factory {
        private final RequestProperties defaultRequestProperties = new RequestProperties();

        /* access modifiers changed from: protected */
        public abstract HttpDataSource createDataSourceInternal(RequestProperties requestProperties);

        public final HttpDataSource createDataSource() {
            return createDataSourceInternal(this.defaultRequestProperties);
        }
    }

    public static class HttpDataSourceException extends IOException {
        public final DataSpec dataSpec;
        public final int type;

        public HttpDataSourceException(String str, DataSpec dataSpec2, int i) {
            super(str);
            this.dataSpec = dataSpec2;
            this.type = i;
        }

        public HttpDataSourceException(IOException iOException, DataSpec dataSpec2, int i) {
            super(iOException);
            this.dataSpec = dataSpec2;
            this.type = i;
        }

        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec2, int i) {
            super(str, iOException);
            this.dataSpec = dataSpec2;
            this.type = i;
        }
    }

    public static final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InvalidContentTypeException(java.lang.String r4, androidx.media2.exoplayer.external.upstream.DataSpec r5) {
            /*
                r3 = this;
                java.lang.String r0 = java.lang.String.valueOf(r4)
                int r1 = r0.length()
                java.lang.String r2 = "Invalid content type: "
                if (r1 == 0) goto L_0x0011
                java.lang.String r0 = r2.concat(r0)
                goto L_0x0016
            L_0x0011:
                java.lang.String r0 = new java.lang.String
                r0.<init>(r2)
            L_0x0016:
                r1 = 1
                r3.<init>((java.lang.String) r0, (androidx.media2.exoplayer.external.upstream.DataSpec) r5, (int) r1)
                r3.contentType = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.upstream.HttpDataSource.InvalidContentTypeException.<init>(java.lang.String, androidx.media2.exoplayer.external.upstream.DataSpec):void");
        }
    }

    public static final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map<String, List<String>> headerFields;
        public final int responseCode;
        public final String responseMessage;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InvalidResponseCodeException(int r3, java.lang.String r4, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r5, androidx.media2.exoplayer.external.upstream.DataSpec r6) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r1 = 26
                r0.<init>(r1)
                java.lang.String r1 = "Response code: "
                r0.append(r1)
                r0.append(r3)
                java.lang.String r0 = r0.toString()
                r1 = 1
                r2.<init>((java.lang.String) r0, (androidx.media2.exoplayer.external.upstream.DataSpec) r6, (int) r1)
                r2.responseCode = r3
                r2.responseMessage = r4
                r2.headerFields = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.upstream.HttpDataSource.InvalidResponseCodeException.<init>(int, java.lang.String, java.util.Map, androidx.media2.exoplayer.external.upstream.DataSpec):void");
        }
    }
}
