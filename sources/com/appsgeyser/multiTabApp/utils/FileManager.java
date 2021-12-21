package com.appsgeyser.multiTabApp.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.webkit.URLUtil;
import androidx.core.app.ActivityCompat;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.VideoPlayerActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class FileManager {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final ArrayList<String> list = new ArrayList<String>() {
        {
            add("video/mpeg4");
            add("video/mp4");
            add("video/3gp");
            add("video/3gpp");
            add("video/3gpp2");
            add("video/webm");
            add("video/avi");
            add("application/sdp");
            add("application/vnd.apple.mpegurl");
            add("application/x-mpegurl");
        }
    };

    public static String getStringFromAssetsFileWithFileName(String str, Context context) {
        InputStream open;
        BufferedReader bufferedReader;
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            AssetManager assets = context.getAssets();
            if (!(assets == null || (open = assets.open(str)) == null)) {
                bufferedReader = new BufferedReader(new InputStreamReader(open));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                }
                bufferedReader.close();
                open.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
        return sb.toString();
    }

    public static void fireOpenFileIntent(String str, String str2, Context context) {
        Intent intent;
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str), context, VideoPlayerActivity.class);
        if (str2 == null || str2.length() <= 0) {
            intent2.setData(Uri.parse(str));
        } else {
            intent2.setDataAndType(Uri.parse(str), str2);
        }
        if (context.getPackageManager().resolveActivity(intent2, 0) != null) {
            context.startActivity(intent2);
            return;
        }
        if (list.contains(str2)) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(str), context, VideoPlayerActivity.class);
        } else {
            intent = new Intent("android.intent.action.VIEW");
        }
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }

    public static long downloadFile(String str, String str2, String str3, Context context) {
        if (Build.VERSION.SDK_INT < 9) {
            fireOpenFileIntent(str, str2, context);
        }
        if (context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            fireOpenFileIntent(str, str2, context);
        }
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(str, str3, str2));
        request.setMimeType(str2);
        if (Build.VERSION.SDK_INT >= 11) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
        }
        return downloadManager.enqueue(request);
    }

    public static void downloadFile(String str, String str2, Context context) {
        downloadFile(str, (String) null, str2, context);
    }

    public static File saveBitmapToGallery(String str, Bitmap bitmap, Context context) {
        File file;
        try {
            Calendar instance = Calendar.getInstance();
            String str2 = "" + instance.get(5) + instance.get(2) + instance.get(1) + instance.get(11) + instance.get(12) + instance.get(13);
            if (Build.VERSION.RELEASE.compareTo("2.3.3") >= 1) {
                if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                    PermissionController.getInstance().addPermissions(PERMISSIONS_STORAGE, 4);
                }
                file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                file = Environment.getExternalStorageDirectory();
            }
            File file2 = new File(file, str + str2 + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            scanPhoto(file2);
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void scanPhoto(File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        Factory.getInstance().getMainNavigationActivity().sendBroadcast(intent);
    }
}
