package com.mopub.nativeads;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.ImageLoader;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NativeImageHelper {

    public interface ImageListener {
        void onImagesCached();

        void onImagesFailedToCache(NativeErrorCode nativeErrorCode);
    }

    public static void preCacheImages(Context context, List<String> list, final ImageListener imageListener) {
        ImageLoader imageLoader = Networking.getImageLoader(context);
        final AtomicInteger atomicInteger = new AtomicInteger(list.size());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AnonymousClass1 r2 = new ImageLoader.ImageListener() {
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean z) {
                if (imageContainer.getBitmap() != null && atomicInteger.decrementAndGet() == 0 && !atomicBoolean.get()) {
                    imageListener.onImagesCached();
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to download a native ads image:", volleyError);
                boolean andSet = atomicBoolean.getAndSet(true);
                atomicInteger.decrementAndGet();
                if (!andSet) {
                    imageListener.onImagesFailedToCache(NativeErrorCode.IMAGE_DOWNLOAD_FAILURE);
                }
            }
        };
        for (String next : list) {
            if (TextUtils.isEmpty(next)) {
                atomicBoolean.set(true);
                imageListener.onImagesFailedToCache(NativeErrorCode.IMAGE_DOWNLOAD_FAILURE);
                return;
            }
            imageLoader.get(next, r2);
        }
    }

    public static void loadImageView(String str, final ImageView imageView) {
        if (Preconditions.NoThrow.checkNotNull(imageView, "Cannot load image into null ImageView")) {
            if (!Preconditions.NoThrow.checkNotNull(str, "Cannot load image with null url")) {
                imageView.setImageDrawable((Drawable) null);
            } else {
                Networking.getImageLoader(imageView.getContext()).get(str, new ImageLoader.ImageListener() {
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean z) {
                        if (!z) {
                            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Image was not loaded immediately into your ad view. You should call preCacheImages as part of your custom event loading process.");
                        }
                        imageView.setImageBitmap(imageContainer.getBitmap());
                    }

                    public void onErrorResponse(VolleyError volleyError) {
                        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Failed to load image.", volleyError);
                        imageView.setImageDrawable((Drawable) null);
                    }
                });
            }
        }
    }
}
