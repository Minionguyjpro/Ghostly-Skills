package com.appsgeyser.sdk.deviceidparser;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class DeviceIdParser {
    private static DeviceIdParser instance;
    /* access modifiers changed from: private */
    public final DeviceIdParameters deviceIdParameters = new DeviceIdParameters();

    public DeviceIdParameters getDeviceIdParameters() {
        return this.deviceIdParameters;
    }

    public static DeviceIdParser getInstance() {
        if (instance == null) {
            instance = new DeviceIdParser();
        }
        return instance;
    }

    private DeviceIdParser() {
    }

    public void rescan(Context context, IDeviceIdParserListener iDeviceIdParserListener) {
        new ParserThread(context, iDeviceIdParserListener).execute(new Context[]{context});
    }

    /* access modifiers changed from: private */
    public AdvertisingIdClient.Info getAdvertisingIdInfo(Context context) {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (GooglePlayServicesNotAvailableException e2) {
            e2.printStackTrace();
            return null;
        } catch (GooglePlayServicesRepairableException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class ParserThread extends AsyncTask<Context, Void, DeviceIdParameters> {
        private final Context context;
        private final IDeviceIdParserListener listener;

        ParserThread(Context context2, IDeviceIdParserListener iDeviceIdParserListener) {
            this.listener = iDeviceIdParserListener;
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public DeviceIdParameters doInBackground(Context... contextArr) {
            DeviceIdParser.this.deviceIdParameters.clear();
            AdvertisingIdClient.Info access$100 = DeviceIdParser.this.getAdvertisingIdInfo(contextArr[0]);
            if (access$100 != null) {
                DeviceIdParser.this.deviceIdParameters.setLimitAdTrackingEnabled(access$100.isLimitAdTrackingEnabled() ? LimitAdTrackingEnabledStates.TRUE : LimitAdTrackingEnabledStates.FALSE);
                DeviceIdParser.this.deviceIdParameters.setAdvId(access$100.getId());
            } else {
                DeviceIdParser.this.deviceIdParameters.setLimitAdTrackingEnabled(LimitAdTrackingEnabledStates.UNKNOWN);
                DeviceIdParser.this.deviceIdParameters.setAdvId((String) null);
                DeviceIdParser.this.deviceIdParameters.setaId(DeviceIdParser.this.getAndroidId(contextArr[0]));
            }
            return DeviceIdParser.this.generateParametersCopy();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(DeviceIdParameters deviceIdParameters) {
            IDeviceIdParserListener iDeviceIdParserListener = this.listener;
            if (iDeviceIdParserListener != null) {
                iDeviceIdParserListener.onDeviceIdParametersObtained(this.context, deviceIdParameters);
            }
        }
    }

    /* access modifiers changed from: private */
    public DeviceIdParameters generateParametersCopy() {
        try {
            return (DeviceIdParameters) this.deviceIdParameters.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
