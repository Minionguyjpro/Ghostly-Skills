package com.appsgeyser.multiTabApp.browser;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.appsgeyser.multiTabApp.DownloadsListActivity;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.browser.DownloadsItem;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.storage.DatabaseOpenHelper;
import com.appsgeyser.multiTabApp.utils.FileManager;
import com.google.android.gms.plus.PlusShare;
import com.wGhostlySkills_14510784.R;

public class BrowserDownloadListener implements DownloadListener {
    private WebContentController _webController;

    public BrowserDownloadListener(WebContentController webContentController) {
        this._webController = webContentController;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x003e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0059 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDownloadStart(final java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, long r11) {
        /*
            r6 = this;
            com.appsgeyser.multiTabApp.Factory r8 = com.appsgeyser.multiTabApp.Factory.getInstance()     // Catch:{ ActivityNotFoundException -> 0x005d }
            com.appsgeyser.multiTabApp.MainNavigationActivity r8 = r8.getMainNavigationActivity()     // Catch:{ ActivityNotFoundException -> 0x005d }
            java.lang.String r11 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r7)     // Catch:{ ActivityNotFoundException -> 0x005d }
            r12 = 0
            if (r11 == 0) goto L_0x0018
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()     // Catch:{ ActivityNotFoundException -> 0x005d }
            java.lang.String r11 = r0.getMimeTypeFromExtension(r11)     // Catch:{ ActivityNotFoundException -> 0x005d }
            goto L_0x0019
        L_0x0018:
            r11 = r12
        L_0x0019:
            if (r11 != 0) goto L_0x001d
            r4 = r10
            goto L_0x001e
        L_0x001d:
            r4 = r11
        L_0x001e:
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration r11 = r8.getConfig()     // Catch:{ ActivityNotFoundException -> 0x005d }
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration$DownloadActions r11 = r11.getDownloadAction()     // Catch:{ ActivityNotFoundException -> 0x005d }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ ActivityNotFoundException -> 0x005d }
            r1 = 9
            if (r0 >= r1) goto L_0x002e
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration$DownloadActions r11 = com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration.DownloadActions.OPEN     // Catch:{ ActivityNotFoundException -> 0x005d }
        L_0x002e:
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration$DownloadActions r0 = com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration.DownloadActions.OPEN     // Catch:{ ActivityNotFoundException -> 0x005d }
            if (r11 != r0) goto L_0x0036
            com.appsgeyser.multiTabApp.utils.FileManager.fireOpenFileIntent(r7, r4, r8)     // Catch:{ ActivityNotFoundException -> 0x005d }
            goto L_0x0078
        L_0x0036:
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration$DownloadActions r0 = com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration.DownloadActions.SAVE     // Catch:{ ActivityNotFoundException -> 0x005d }
            if (r11 != r0) goto L_0x0042
            r6.download(r7, r12, r4, r8)     // Catch:{ Exception -> 0x003e }
            goto L_0x0078
        L_0x003e:
            com.appsgeyser.multiTabApp.utils.FileManager.fireOpenFileIntent(r7, r4, r8)     // Catch:{ ActivityNotFoundException -> 0x005d }
            goto L_0x0078
        L_0x0042:
            com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration$DownloadActions r12 = com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration.DownloadActions.DIALOG     // Catch:{ ActivityNotFoundException -> 0x005d }
            if (r11 != r12) goto L_0x0078
            com.appsgeyser.multiTabApp.browser.BrowserDownloadListener$1 r11 = new com.appsgeyser.multiTabApp.browser.BrowserDownloadListener$1     // Catch:{ Exception -> 0x0059 }
            r11.<init>(r7, r4, r8)     // Catch:{ Exception -> 0x0059 }
            com.appsgeyser.multiTabApp.browser.BrowserDownloadListener$2 r12 = new com.appsgeyser.multiTabApp.browser.BrowserDownloadListener$2     // Catch:{ Exception -> 0x0059 }
            r0 = r12
            r1 = r6
            r2 = r7
            r3 = r9
            r5 = r8
            r0.<init>(r2, r3, r4, r5)     // Catch:{ Exception -> 0x0059 }
            com.appsgeyser.multiTabApp.ui.dialog.SimpleDialogs.showOpenOrSaveDialog(r8, r11, r12)     // Catch:{ Exception -> 0x0059 }
            goto L_0x0078
        L_0x0059:
            com.appsgeyser.multiTabApp.utils.FileManager.fireOpenFileIntent(r7, r10, r8)     // Catch:{ ActivityNotFoundException -> 0x005d }
            goto L_0x0078
        L_0x005d:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "onDownloadStart :"
            r8.append(r9)
            java.lang.String r7 = r7.getMessage()
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            java.lang.String r8 = "ANFE"
            android.util.Log.e(r8, r7)
        L_0x0078:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.multiTabApp.browser.BrowserDownloadListener.onDownloadStart(java.lang.String, java.lang.String, java.lang.String, java.lang.String, long):void");
    }

    /* access modifiers changed from: private */
    public void download(String str, String str2, String str3, MainNavigationActivity mainNavigationActivity) {
        if (ContextCompat.checkSelfPermission(mainNavigationActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            long downloadFile = FileManager.downloadFile(str, str3, str2, mainNavigationActivity);
            String guessFileName = URLUtil.guessFileName(str, str2, str3);
            MainNavigationActivity mainNavigationActivity2 = mainNavigationActivity;
            addIntoDataBase(mainNavigationActivity2, str, downloadFile, guessFileName);
            sendMessageToActivity(mainNavigationActivity2, downloadFile, guessFileName, str);
            return;
        }
        ActivityCompat.requestPermissions(mainNavigationActivity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 35);
        final String str4 = str;
        final String str5 = str3;
        final String str6 = str2;
        final MainNavigationActivity mainNavigationActivity3 = mainNavigationActivity;
        mainNavigationActivity.setPermissionCallback(new ValueCallback<Integer>() {
            public void onReceiveValue(Integer num) {
                if (num.intValue() == 0) {
                    long downloadFile = FileManager.downloadFile(str4, str5, str6, mainNavigationActivity3);
                    String guessFileName = URLUtil.guessFileName(str4, str6, str5);
                    BrowserDownloadListener.this.addIntoDataBase(mainNavigationActivity3, str4, downloadFile, guessFileName);
                    BrowserDownloadListener.this.sendMessageToActivity(mainNavigationActivity3, downloadFile, guessFileName, str4);
                    return;
                }
                Toast.makeText(mainNavigationActivity3, R.string.download_permission_denied, 1).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendMessageToActivity(MainNavigationActivity mainNavigationActivity, long j, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("type", 100);
        intent.putExtra("item", new DownloadsItem(j, str, str2));
        intent.setAction(DownloadsListActivity.DOWNLOAD_LIST_MSG);
        mainNavigationActivity.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void addIntoDataBase(MainNavigationActivity mainNavigationActivity, String str, long j, String str2) {
        SQLiteDatabase writableDatabase = new DatabaseOpenHelper(mainNavigationActivity).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("id_d", Long.valueOf(j));
            contentValues.put("name", str2);
            contentValues.put("status", DownloadsItem.Status.InProgress.toString());
            contentValues.put("link_d", str);
            contentValues.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str);
            writableDatabase.insert("downloadsList", (String) null, contentValues);
        } catch (Exception e) {
            Log.e("Error", "  Error while add string into table downloadsList " + e);
        } catch (Throwable th) {
            writableDatabase.close();
            throw th;
        }
        writableDatabase.close();
    }
}
