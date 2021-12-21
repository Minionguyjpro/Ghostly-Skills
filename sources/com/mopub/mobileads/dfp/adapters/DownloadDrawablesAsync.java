package com.mopub.mobileads.dfp.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DownloadDrawablesAsync extends AsyncTask<Object, Void, HashMap<String, Drawable>> {
    private static final long DRAWABLE_FUTURE_TIMEOUT_SECONDS = 10;
    public static final String KEY_ICON = "icon_key";
    public static final String KEY_IMAGE = "image_key";
    private DrawableDownloadListener mListener;

    public DownloadDrawablesAsync(DrawableDownloadListener drawableDownloadListener) {
        this.mListener = drawableDownloadListener;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Drawable> doInBackground(Object... objArr) {
        HashMap hashMap = objArr[0];
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Future<Drawable> drawableFuture = getDrawableFuture((URL) hashMap.get(KEY_IMAGE), newCachedThreadPool);
        Future<Drawable> drawableFuture2 = getDrawableFuture((URL) hashMap.get(KEY_ICON), newCachedThreadPool);
        try {
            HashMap<String, Drawable> hashMap2 = new HashMap<>();
            hashMap2.put(KEY_IMAGE, drawableFuture.get(DRAWABLE_FUTURE_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            hashMap2.put(KEY_ICON, drawableFuture2.get(DRAWABLE_FUTURE_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            return hashMap2;
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            Log.d(MoPubAdapter.TAG, "Native ad images failed to download");
            return null;
        }
    }

    private Future<Drawable> getDrawableFuture(final URL url, ExecutorService executorService) {
        return executorService.submit(new Callable<Drawable>() {
            public Drawable call() throws Exception {
                Bitmap decodeStream = BitmapFactory.decodeStream(url.openStream());
                decodeStream.setDensity(160);
                return new BitmapDrawable(Resources.getSystem(), decodeStream);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HashMap<String, Drawable> hashMap) {
        super.onPostExecute(hashMap);
        if (hashMap != null) {
            this.mListener.onDownloadSuccess(hashMap);
        } else {
            this.mListener.onDownloadFailure();
        }
    }
}
