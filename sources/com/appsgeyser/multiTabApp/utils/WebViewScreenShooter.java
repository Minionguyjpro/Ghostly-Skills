package com.appsgeyser.multiTabApp.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.Base64;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;

public class WebViewScreenShooter {
    public static String takeScreenShotInBase64(WebView webView) {
        Picture capturePicture = webView.capturePicture();
        Bitmap createBitmap = Bitmap.createBitmap(capturePicture.getWidth(), capturePicture.getHeight(), Bitmap.Config.ARGB_8888);
        capturePicture.draw(new Canvas(createBitmap));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
