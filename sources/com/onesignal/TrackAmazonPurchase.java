package com.onesignal;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.ProductDataResponse;
import com.onesignal.OneSignal;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class TrackAmazonPurchase {
    private boolean canTrack = false;
    private Context context;
    private Field listenerHandlerField;
    private Object listenerHandlerObject;
    private OSPurchasingListener osPurchasingListener;

    TrackAmazonPurchase(Context context2) {
        this.context = context2;
        try {
            Class<?> cls = Class.forName("com.amazon.device.iap.internal.d");
            this.listenerHandlerObject = cls.getMethod("d", new Class[0]).invoke((Object) null, new Object[0]);
            Field declaredField = cls.getDeclaredField("f");
            this.listenerHandlerField = declaredField;
            declaredField.setAccessible(true);
            OSPurchasingListener oSPurchasingListener = new OSPurchasingListener(this, (AnonymousClass1) null);
            this.osPurchasingListener = oSPurchasingListener;
            oSPurchasingListener.orgPurchasingListener = (PurchasingListener) this.listenerHandlerField.get(this.listenerHandlerObject);
            this.canTrack = true;
            setListener();
        } catch (ClassNotFoundException e) {
            logAmazonIAPListenerError(e);
        } catch (IllegalAccessException e2) {
            logAmazonIAPListenerError(e2);
        } catch (InvocationTargetException e3) {
            logAmazonIAPListenerError(e3);
        } catch (NoSuchMethodException e4) {
            logAmazonIAPListenerError(e4);
        } catch (NoSuchFieldException e5) {
            logAmazonIAPListenerError(e5);
        }
    }

    private static void logAmazonIAPListenerError(Exception exc) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding Amazon IAP listener.", exc);
        exc.printStackTrace();
    }

    private void setListener() {
        PurchasingService.registerListener(this.context, this.osPurchasingListener);
    }

    /* access modifiers changed from: package-private */
    public void checkListener() {
        if (this.canTrack) {
            try {
                OSPurchasingListener oSPurchasingListener = (PurchasingListener) this.listenerHandlerField.get(this.listenerHandlerObject);
                if (oSPurchasingListener != this.osPurchasingListener) {
                    this.osPurchasingListener.orgPurchasingListener = oSPurchasingListener;
                    setListener();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private class OSPurchasingListener implements PurchasingListener {
        PurchasingListener orgPurchasingListener;

        private OSPurchasingListener() {
        }

        /* synthetic */ OSPurchasingListener(TrackAmazonPurchase trackAmazonPurchase, AnonymousClass1 r2) {
            this();
        }
    }

    /* renamed from: com.onesignal.TrackAmazonPurchase$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus;

        static {
            int[] iArr = new int[ProductDataResponse.RequestStatus.values().length];
            $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus = iArr;
            try {
                iArr[ProductDataResponse.RequestStatus.SUCCESSFUL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
