package com.appsgeyser.multiTabApp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Base64;
import java.io.IOException;
import java.io.InputStream;

public class ImageReader {
    public static Bitmap decodeFile(InputStream inputStream, int i) {
        if (i == 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        while ((options.outWidth / i2) / 2 >= i && (options.outHeight / i2) / 2 >= i) {
            i2 *= 2;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = i2;
        try {
            inputStream.reset();
            return BitmapFactory.decodeStream(inputStream, (Rect) null, options2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createBitmapFromBase64(String str) {
        if (str.equals("")) {
            return null;
        }
        byte[] decode = Base64.decode(str.replace("data:image/png;base64,", ""), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }
}
