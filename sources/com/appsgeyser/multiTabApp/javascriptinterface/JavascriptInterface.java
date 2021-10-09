package com.appsgeyser.multiTabApp.javascriptinterface;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.inline.StringEscapeUtils;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.server.BaseServerClient;
import com.appsgeyser.multiTabApp.storage.BrowsingHistoryItem;
import com.appsgeyser.multiTabApp.ui.navigationwidget.NavigationWidgetCustomIcon;
import com.appsgeyser.multiTabApp.ui.views.PdfActivity;
import com.appsgeyser.multiTabApp.utils.FileManager;
import com.appsgeyser.multiTabApp.utils.ImageReader;
import com.appsgeyser.multiTabApp.utils.UrlConverter;
import com.appsgeyser.multiTabApp.utils.WebViewScreenShooter;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.google.android.gms.plus.PlusShare;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.wGhostlySkills_14510784.R;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavascriptInterface {
    /* access modifiers changed from: private */
    public ProgressDialog _currentProgressDialog;
    /* access modifiers changed from: private */
    public MainNavigationActivity _mainActivity;
    /* access modifiers changed from: private */
    public WebContentController _webController;
    /* access modifiers changed from: private */
    public int mFinalHeight;
    /* access modifiers changed from: private */
    public int mFinalWidth;

    @android.webkit.JavascriptInterface
    public void clearUpdateCheckers() {
    }

    @android.webkit.JavascriptInterface
    public void registerUpdateChecker(String str, int i) {
    }

    @android.webkit.JavascriptInterface
    public void removeUpdateChecker(String str) {
    }

    @android.webkit.JavascriptInterface
    public void showPopup(String str) {
    }

    public JavascriptInterface(WebContentController webContentController) {
        if (webContentController != null) {
            this._mainActivity = webContentController.getMainNavigationActivity();
            this._webController = webContentController;
        }
    }

    @android.webkit.JavascriptInterface
    public void showRewardedVideo(String str) {
        this._mainActivity.runOnUiThread(new Runnable(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                JavascriptInterface.this.lambda$showRewardedVideo$0$JavascriptInterface(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$showRewardedVideo$0$JavascriptInterface(final String str) {
        AppsgeyserSDK.getFastTrackAdsController().showRewardedVideo(new FastTrackBaseAdapter.RewardedVideoListener() {
            boolean isVideoFinished;

            public void onVideoClicked() {
            }

            public void onVideoOpened() {
            }

            public void onVideoClosed() {
                String str = "javascript:window." + str + "(" + String.valueOf(this.isVideoFinished) + ");";
                Log.d("Rewarded video", "Closed. callback is: " + str);
                JavascriptInterface.this._webController.getWebView().loadUrl(str);
            }

            public void onVideoError(String str) {
                String str2 = "javascript:window." + str + "(false);";
                Log.d("Rewarded video", "Error. callback is: " + str2);
                JavascriptInterface.this._webController.getWebView().loadUrl(str2);
            }

            public void onVideoFinished() {
                this.isVideoFinished = true;
            }

            public void onVideoDeactivated() {
                Log.d("Rewarded video", "Rewarded video placement deactivated");
                JavascriptInterface.this._webController.getWebView().loadUrl("javascript:window." + str + "(false);");
            }
        }, this._mainActivity.getResources().getString(R.string.ads_placement_tag_rw_js));
    }

    @android.webkit.JavascriptInterface
    public void sendXMLHTTPRequest(final String str, final String str2) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                final String absolute = new UrlConverter(JavascriptInterface.this._webController.getWebView()).toAbsolute(str);
                if (absolute.startsWith("file:///")) {
                    new Thread() {
                        public void run() {
                            String str = "";
                            try {
                                str = FileManager.getStringFromAssetsFileWithFileName(absolute.replace("file:///android_asset/", str).replaceFirst("#.*", str), JavascriptInterface.this._mainActivity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JavascriptInterface.this.XmlHttpRequestDone(str, str2);
                        }
                    }.start();
                } else {
                    new BaseServerClient(JavascriptInterface.this._mainActivity).sendRequestAsync(absolute, 0, new BaseServerClient.OnRequestDoneListener() {
                        public void onRequestDone(String str, int i, String str2) {
                            String str3;
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str2.getBytes())));
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    sb.append(readLine);
                                    sb.append(10);
                                }
                                sb.deleteCharAt(sb.length() - 1);
                                str3 = sb.toString();
                            } catch (IOException e) {
                                e.printStackTrace();
                                str3 = "";
                            }
                            JavascriptInterface.this.XmlHttpRequestDone(str3, str2);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void XmlHttpRequestDone(String str, String str2) {
        final String str3 = "javascript:window." + str2 + "('" + StringEscapeUtils.escapeJavaScript(str) + "');";
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                JavascriptInterface.this._webController.getWebView().loadUrl(str3);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public String getAppId() {
        return Integer.toString(this._mainActivity.getConfig().getApplicationId());
    }

    @android.webkit.JavascriptInterface
    public void updateIconBadge(final String str, final String str2) {
        final HashMap<String, NavigationWidgetCustomIcon> customIcons = Factory.getInstance().getNavigationWidget().getCustomIcons();
        if (customIcons.containsKey(str)) {
            this._mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    ((NavigationWidgetCustomIcon) customIcons.get(str)).updateBadge(str2);
                }
            });
        }
    }

    @android.webkit.JavascriptInterface
    public void updateIcon(final String str, final String str2) {
        final HashMap<String, NavigationWidgetCustomIcon> customIcons = Factory.getInstance().getNavigationWidget().getCustomIcons();
        if (customIcons.containsKey(str)) {
            this._mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    ((NavigationWidgetCustomIcon) customIcons.get(str)).updateIcon(str2);
                }
            });
        }
    }

    @android.webkit.JavascriptInterface
    public void showInfo(String str) {
        MainNavigationActivity mainNavigationActivity = this._mainActivity;
        if (mainNavigationActivity != null) {
            Toast.makeText(mainNavigationActivity, str, 0).show();
        }
    }

    @android.webkit.JavascriptInterface
    public void setScaleForPageWithSize(int i, int i2) {
        this.mFinalHeight = i;
        this.mFinalWidth = i2;
        MainNavigationActivity mainNavigationActivity = this._mainActivity;
        if (mainNavigationActivity != null) {
            mainNavigationActivity.runOnUiThread(new Runnable() {
                public void run() {
                    JavascriptInterface.this._webController.setScaleForPageWithSize(JavascriptInterface.this.mFinalHeight, JavascriptInterface.this.mFinalWidth);
                }
            });
        }
    }

    @android.webkit.JavascriptInterface
    public void downloadFile(String str) {
        FileManager.downloadFile(new UrlConverter(this._webController.getWebView()).toAbsolute(str), "", this._mainActivity);
    }

    @android.webkit.JavascriptInterface
    public String saveImageFromBase64(String str, String str2) {
        File saveBitmapToGallery;
        if (str2 == null) {
            str2 = "IMG";
        }
        Bitmap createBitmapFromBase64 = ImageReader.createBitmapFromBase64(str);
        if (createBitmapFromBase64 == null || (saveBitmapToGallery = FileManager.saveBitmapToGallery(str2, createBitmapFromBase64, this._mainActivity)) == null) {
            return null;
        }
        showInfo("Image saved to gallery...");
        return saveBitmapToGallery.toString();
    }

    @android.webkit.JavascriptInterface
    public void setWallpaper(final String str) {
        this._webController.getWebView().post(new Runnable() {
            public void run() {
                InputStream stream = new UrlConverter(JavascriptInterface.this._webController.getWebView()).toStream(str);
                if (stream != null) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(stream);
                    try {
                        JavascriptInterface.this._scaleBitmapAndSetWallpaper(decodeStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    decodeStream.recycle();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @android.webkit.JavascriptInterface
    public void _scaleBitmapAndSetWallpaper(Bitmap bitmap) throws IOException {
        WallpaperManager instance = WallpaperManager.getInstance(this._mainActivity);
        new BitmapFactory.Options().inScaled = false;
        instance.setBitmap(bitmap);
    }

    @android.webkit.JavascriptInterface
    public void zoomIn() {
        MainNavigationActivity mainNavigationActivity = this._mainActivity;
        if (mainNavigationActivity != null) {
            mainNavigationActivity.runOnUiThread(new Runnable() {
                public void run() {
                    JavascriptInterface.this._webController.zoomIn();
                }
            });
        }
    }

    @android.webkit.JavascriptInterface
    public String getFileContents(String str) {
        return FileManager.getStringFromAssetsFileWithFileName(str, this._mainActivity);
    }

    @android.webkit.JavascriptInterface
    public void showLoadingDialog(final String str) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                JavascriptInterface javascriptInterface = JavascriptInterface.this;
                ProgressDialog unused = javascriptInterface._currentProgressDialog = ProgressDialog.show(javascriptInterface._mainActivity, "", str, true);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void hideLoadingDialog() {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (JavascriptInterface.this._currentProgressDialog != null) {
                    JavascriptInterface.this._currentProgressDialog.hide();
                    ProgressDialog unused = JavascriptInterface.this._currentProgressDialog = null;
                }
            }
        });
    }

    @android.webkit.JavascriptInterface
    public String getTabContentType(String str) {
        WidgetEntity widgetByTabId = Factory.getInstance().getWidgetsController().getWidgetByTabId(str);
        return widgetByTabId != null ? widgetByTabId.getTabType() : "";
    }

    @android.webkit.JavascriptInterface
    public String getTabUrl(String str) {
        WidgetEntity widgetByTabId = Factory.getInstance().getWidgetsController().getWidgetByTabId(str);
        return widgetByTabId != null ? widgetByTabId.getLink() : "";
    }

    @android.webkit.JavascriptInterface
    public void showPdfView(final String str, final String str2) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(JavascriptInterface.this._mainActivity, PdfActivity.class);
                intent.putExtra("path_file", str);
                intent.putExtra("widget_id", str2);
                JavascriptInterface.this._mainActivity.startActivity(intent);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void showTabs() {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                JavascriptInterface.this._mainActivity.findViewById(R.id.tabtags_panel).setVisibility(0);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void setMenuItemVisible(String str, boolean z) {
        if (str != null && !str.equals("undefined") && !str.isEmpty()) {
            this._mainActivity.setMenuItemVisible(str.hashCode(), z);
        }
    }

    @android.webkit.JavascriptInterface
    public String getItem(String str) {
        SharedPreferences sharedPreferences = this._mainActivity.getSharedPreferences("JS-PREFERENCE", 0);
        return sharedPreferences.getString("JS-Preference-" + str, (String) null);
    }

    @android.webkit.JavascriptInterface
    public void setItem(String str, String str2) {
        SharedPreferences.Editor edit = this._mainActivity.getSharedPreferences("JS-PREFERENCE", 0).edit();
        edit.putString("JS-Preference-" + str, str2).commit();
    }

    @android.webkit.JavascriptInterface
    public void addToHomePage(String str, String str2) {
        Factory.getInstance().getHomePageManager().addBookmark(str, str2);
    }

    @android.webkit.JavascriptInterface
    public void addBookmark(String str, String str2, String str3) {
        Factory.getInstance().getBookmarkManager(str3).addBookmark(str, str2);
    }

    @android.webkit.JavascriptInterface
    public void removeFromHomepage(String str) {
        Factory.getInstance().getHomePageManager().removeBookmark(Integer.valueOf(Integer.parseInt(str)));
    }

    @android.webkit.JavascriptInterface
    public void removeBookmark(String str, String str2) {
        Factory.getInstance().getBookmarkManager(str2).removeBookmark(Integer.valueOf(Integer.parseInt(str)));
    }

    private String getBookmarksFromCursor(Cursor cursor) {
        JSONArray jSONArray = new JSONArray();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    try {
                        Long valueOf = Long.valueOf(cursor.getLong(i));
                        String string = cursor.getString(i);
                        if (string != null) {
                            if (string.length() != 0) {
                                jSONObject.put(cursor.getColumnName(i), string);
                            }
                        }
                        jSONObject.put(cursor.getColumnName(i), valueOf);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                jSONArray.put(jSONObject);
                cursor.moveToNext();
            }
        }
        return jSONArray.toString();
    }

    @android.webkit.JavascriptInterface
    public String getHomePageItems() {
        return getBookmarksFromCursor(Factory.getInstance().getHomePageManager().getBookmarks());
    }

    @android.webkit.JavascriptInterface
    public String getBookmarks(String str) {
        return getBookmarksFromCursor(Factory.getInstance().getBookmarkManager(str).getBookmarks());
    }

    @android.webkit.JavascriptInterface
    public String getHomePageItems(String str, String str2) {
        return getBookmarksFromCursor(Factory.getInstance().getHomePageManager().getBookmarks(Integer.valueOf(Integer.parseInt(str)), Integer.valueOf(Integer.parseInt(str2))));
    }

    @android.webkit.JavascriptInterface
    public String getBookmarks(String str, String str2, String str3) {
        return getBookmarksFromCursor(Factory.getInstance().getBookmarkManager(str3).getBookmarks(Integer.valueOf(Integer.parseInt(str)), Integer.valueOf(Integer.parseInt(str2))));
    }

    @android.webkit.JavascriptInterface
    public String getAppName() {
        try {
            return Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName();
        } catch (Exception unused) {
            return null;
        }
    }

    @android.webkit.JavascriptInterface
    public String getAppPackageName() {
        try {
            return this._mainActivity.getApplicationContext().getPackageName();
        } catch (Exception unused) {
            return null;
        }
    }

    @android.webkit.JavascriptInterface
    public void redirect(final String str) {
        try {
            if (this._mainActivity != null) {
                this._mainActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        Factory.getInstance().getTabsController().getSelectedTab().getWebView().loadUrl(str);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @android.webkit.JavascriptInterface
    public void shareText(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str2);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        Factory.getInstance().getMainNavigationActivity().startActivity(Intent.createChooser(intent, "Share using"));
    }

    @android.webkit.JavascriptInterface
    public String takeScreenShot() {
        return WebViewScreenShooter.takeScreenShotInBase64(Factory.getInstance().getTabsController().getSelectedTab().getWebView());
    }

    @android.webkit.JavascriptInterface
    public void getBase64FromImageUrl(String str, final String str2) {
        new BaseServerClient(this._mainActivity).sendRequestAsync(str, 0, new BaseServerClient.OnRequestDoneListener() {
            public void onRequestDone(String str, int i, String str2) {
                String str3;
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str2.getBytes());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = byteArrayInputStream.read();
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(read);
                    }
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                    str3 = "data:image/png;base64," + Base64.encodeToString(byteArray, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                    str3 = "";
                }
                final String replace = str3.replace("\n", "").replace("\\", "\\\\").replace("'", "\\'");
                JavascriptInterface.this._mainActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        WebView webView = JavascriptInterface.this._webController.getWebView();
                        webView.loadUrl("javascript:" + str2 + "('" + replace + "');");
                    }
                });
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void sharePicture(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/png");
        File file = new File(str);
        if (file.exists()) {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
            this._mainActivity.startActivity(Intent.createChooser(intent, str2));
        } else if (Build.VERSION.SDK_INT < 23) {
            showInfo("Error! Can't find " + str + "!");
        }
    }

    @android.webkit.JavascriptInterface
    public void setUrlBarVisibility(boolean z) {
        this._mainActivity.setUrlBarVisibility(z ? 0 : 8);
    }

    @android.webkit.JavascriptInterface
    public boolean playYouTubeVideo(String str, String str2, int i, boolean z, boolean z2) {
        try {
            this._mainActivity.startActivity(YouTubeStandalonePlayer.createVideoIntent(this._mainActivity, str2, str, i, z, z2));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @android.webkit.JavascriptInterface
    public void overrideRefreshAction(String str) {
        this._webController.setPageRefreshJsCode(str);
    }

    @android.webkit.JavascriptInterface
    public void getWeeklyHistory(final String str) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                ArrayList<BrowsingHistoryItem> weeklyHistory = JavascriptInterface.this._mainActivity.getWeeklyHistory();
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                try {
                    Iterator<BrowsingHistoryItem> it = weeklyHistory.iterator();
                    while (it.hasNext()) {
                        BrowsingHistoryItem next = it.next();
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("_id", next.getId());
                        jSONObject2.put("date", next.getDate());
                        jSONObject2.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, next.getTitle());
                        jSONObject2.put("url", next.getUrl());
                        jSONArray.put(jSONObject2);
                    }
                } catch (Exception unused) {
                    jSONArray = new JSONArray();
                }
                try {
                    jSONObject.put("history", jSONArray);
                } catch (JSONException unused2) {
                }
                WebView webView = JavascriptInterface.this._webController.getWebView();
                webView.loadUrl("javascript:" + str + "('" + jSONObject.toString() + "')");
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void removeHistoryItem(final long j) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                JavascriptInterface.this._mainActivity.removeHistoryItem(j);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void removeHistoryAllItem() {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                JavascriptInterface.this._mainActivity.removeHistoryAllItem();
            }
        });
    }
}
