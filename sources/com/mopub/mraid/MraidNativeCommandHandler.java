package com.mopub.mraid;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import com.appnext.base.b.d;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Streams;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MraidNativeCommandHandler {
    public static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
    private static final String[] DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm:ssZZZZZ", "yyyy-MM-dd'T'HH:mmZZZZZ"};
    private static final int MAX_NUMBER_DAYS_IN_MONTH = 31;

    interface MraidCommandFailureListener {
        void onFailure(MraidCommandException mraidCommandException);
    }

    static boolean isCalendarAvailable(Context context) {
        return false;
    }

    public static boolean isStorePictureSupported(Context context) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isSmsAvailable(Context context) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isTelAvailable(Context context) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isInlineVideoAvailable(Activity activity, View view) {
        if (activity.getWindow() == null || (activity.getWindow().getAttributes().flags & 16777216) == 0) {
            return false;
        }
        return true;
    }

    private Map<String, Object> translateJSParamsToAndroidCalendarEventMapping(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (!map.containsKey(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) || !map.containsKey("start")) {
            throw new IllegalArgumentException("Missing start and description fields");
        }
        hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, map.get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
        if (!map.containsKey("start") || map.get("start") == null) {
            throw new IllegalArgumentException("Invalid calendar event: start is null.");
        }
        Date parseDate = parseDate(map.get("start"));
        if (parseDate != null) {
            hashMap.put("beginTime", Long.valueOf(parseDate.getTime()));
            if (map.containsKey("end") && map.get("end") != null) {
                Date parseDate2 = parseDate(map.get("end"));
                if (parseDate2 != null) {
                    hashMap.put("endTime", Long.valueOf(parseDate2.getTime()));
                } else {
                    throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
                }
            }
            if (map.containsKey("location")) {
                hashMap.put("eventLocation", map.get("location"));
            }
            if (map.containsKey("summary")) {
                hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, map.get("summary"));
            }
            if (map.containsKey("transparency")) {
                hashMap.put("availability", Integer.valueOf(map.get("transparency").equals("transparent") ? 1 : 0));
            }
            hashMap.put("rrule", parseRecurrenceRule(map));
            return hashMap;
        }
        throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
    }

    private Date parseDate(String str) {
        String[] strArr = DATE_FORMATS;
        int length = strArr.length;
        Date date = null;
        int i = 0;
        while (i < length) {
            try {
                date = new SimpleDateFormat(strArr[i], Locale.US).parse(str);
                if (date != null) {
                    break;
                }
                i++;
            } catch (ParseException unused) {
            }
        }
        return date;
    }

    private String parseRecurrenceRule(Map<String, String> map) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey("frequency")) {
            String str = map.get("frequency");
            int parseInt = map.containsKey(d.fn) ? Integer.parseInt(map.get(d.fn)) : -1;
            if ("daily".equals(str)) {
                sb.append("FREQ=DAILY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
            } else if ("weekly".equals(str)) {
                sb.append("FREQ=WEEKLY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInWeek")) {
                    String translateWeekIntegersToDays = translateWeekIntegersToDays(map.get("daysInWeek"));
                    if (translateWeekIntegersToDays != null) {
                        sb.append("BYDAY=" + translateWeekIntegersToDays + ";");
                    } else {
                        throw new IllegalArgumentException("invalid ");
                    }
                }
            } else if ("monthly".equals(str)) {
                sb.append("FREQ=MONTHLY;");
                if (parseInt != -1) {
                    sb.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInMonth")) {
                    String translateMonthIntegersToDays = translateMonthIntegersToDays(map.get("daysInMonth"));
                    if (translateMonthIntegersToDays != null) {
                        sb.append("BYMONTHDAY=" + translateMonthIntegersToDays + ";");
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
            }
        }
        return sb.toString();
    }

    private String translateWeekIntegersToDays(String str) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[7];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            if (parseInt2 == 7) {
                parseInt2 = 0;
            }
            if (!zArr[parseInt2]) {
                sb.append(dayNumberToDayOfWeekString(parseInt2) + ",");
                zArr[parseInt2] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
    }

    private String translateMonthIntegersToDays(String str) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[63];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            int i = parseInt2 + 31;
            if (!zArr[i]) {
                sb.append(dayNumberToDayOfMonthString(parseInt2) + ",");
                zArr[i] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
    }

    private String dayNumberToDayOfWeekString(int i) throws IllegalArgumentException {
        switch (i) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                throw new IllegalArgumentException("invalid day of week " + i);
        }
    }

    private String dayNumberToDayOfMonthString(int i) throws IllegalArgumentException {
        if (i == 0 || i < -31 || i > 31) {
            throw new IllegalArgumentException("invalid day of month " + i);
        }
        return "" + i;
    }

    static class DownloadImageAsyncTask extends AsyncTask<String, Void, Boolean> {
        private final Context mContext;
        private final DownloadImageAsyncTaskListener mListener;

        interface DownloadImageAsyncTaskListener {
            void onFailure();

            void onSuccess();
        }

        public DownloadImageAsyncTask(Context context, DownloadImageAsyncTaskListener downloadImageAsyncTaskListener) {
            this.mContext = context.getApplicationContext();
            this.mListener = downloadImageAsyncTaskListener;
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(String[] strArr) {
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2;
            File file;
            if (strArr == null || strArr.length == 0 || strArr[0] == null) {
                return false;
            }
            File pictureStoragePath = getPictureStoragePath();
            pictureStoragePath.mkdirs();
            String str = strArr[0];
            URI create = URI.create(str);
            BufferedInputStream bufferedInputStream = null;
            try {
                HttpURLConnection httpUrlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(str);
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpUrlConnection.getInputStream());
                try {
                    String headerField = httpUrlConnection.getHeaderField(ResponseHeader.LOCATION.getKey());
                    if (!TextUtils.isEmpty(headerField)) {
                        create = URI.create(headerField);
                    }
                    file = new File(pictureStoragePath, getFileNameForUriAndHeaders(create, httpUrlConnection.getHeaderFields()));
                    fileOutputStream2 = new FileOutputStream(file);
                } catch (Exception unused) {
                    fileOutputStream2 = null;
                    bufferedInputStream = bufferedInputStream2;
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream2);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                    bufferedInputStream = bufferedInputStream2;
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream);
                    throw th;
                }
                try {
                    Streams.copyContent(bufferedInputStream2, fileOutputStream2);
                    loadPictureIntoGalleryApp(file.toString());
                    Streams.closeStream(bufferedInputStream2);
                    Streams.closeStream(fileOutputStream2);
                    return true;
                } catch (Exception unused2) {
                    bufferedInputStream = bufferedInputStream2;
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream2);
                    return false;
                } catch (Throwable th2) {
                    bufferedInputStream = bufferedInputStream2;
                    Throwable th3 = th2;
                    fileOutputStream = fileOutputStream2;
                    th = th3;
                    Streams.closeStream(bufferedInputStream);
                    Streams.closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Exception unused3) {
                fileOutputStream2 = null;
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(fileOutputStream2);
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(fileOutputStream);
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            if (bool == null || !bool.booleanValue()) {
                this.mListener.onFailure();
            } else {
                this.mListener.onSuccess();
            }
        }

        private String getFileNameForUriAndHeaders(URI uri, Map<String, List<String>> map) {
            Preconditions.checkNotNull(uri);
            String path = uri.getPath();
            if (path == null || map == null) {
                return null;
            }
            String name = new File(path).getName();
            List list = map.get(ResponseHeader.CONTENT_TYPE.getKey());
            if (list == null || list.isEmpty()) {
                return name;
            }
            if (list.get(0) == null) {
                return name;
            }
            for (String str : ((String) list.get(0)).split(";")) {
                if (str.contains("image/")) {
                    String str2 = "." + str.split("/")[1];
                    if (name.endsWith(str2)) {
                        return name;
                    }
                    return name + str2;
                }
            }
            return name;
        }

        private File getPictureStoragePath() {
            return new File(Environment.getExternalStorageDirectory(), "Pictures");
        }

        private void loadPictureIntoGalleryApp(String str) {
            MoPubMediaScannerConnectionClient moPubMediaScannerConnectionClient = new MoPubMediaScannerConnectionClient(str, (String) null);
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, moPubMediaScannerConnectionClient);
            moPubMediaScannerConnectionClient.setMediaScannerConnection(mediaScannerConnection);
            mediaScannerConnection.connect();
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public DownloadImageAsyncTaskListener getListener() {
            return this.mListener;
        }
    }

    private static class MoPubMediaScannerConnectionClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private final String mFilename;
        private MediaScannerConnection mMediaScannerConnection;
        private final String mMimeType;

        private MoPubMediaScannerConnectionClient(String str, String str2) {
            this.mFilename = str;
            this.mMimeType = str2;
        }

        /* access modifiers changed from: private */
        public void setMediaScannerConnection(MediaScannerConnection mediaScannerConnection) {
            this.mMediaScannerConnection = mediaScannerConnection;
        }

        public void onMediaScannerConnected() {
            MediaScannerConnection mediaScannerConnection = this.mMediaScannerConnection;
            if (mediaScannerConnection != null) {
                mediaScannerConnection.scanFile(this.mFilename, this.mMimeType);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            MediaScannerConnection mediaScannerConnection = this.mMediaScannerConnection;
            if (mediaScannerConnection != null) {
                mediaScannerConnection.disconnect();
            }
        }
    }
}
