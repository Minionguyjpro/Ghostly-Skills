package com.appnext.core;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ResultReceiver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdsService extends IntentService {
    public static final int ADD_PACK = 8348;
    public static final int START_APP = 8346;
    /* access modifiers changed from: private */
    public static HashMap<String, n> gf = new HashMap<>();
    /* access modifiers changed from: private */
    public Runnable gg = new Runnable() {
        public final void run() {
            try {
                for (Map.Entry entry : new HashMap(AdsService.gf).entrySet()) {
                    n nVar = (n) entry.getValue();
                    if (AdsService.this.Q(nVar.am)) {
                        new Bundle().putAll(nVar.hz);
                        AdsService.this.a(nVar);
                        AdsService.gf.remove(entry.getKey());
                        AdsService.this.startActivity(AdsService.this.getPackageManager().getLaunchIntentForPackage(nVar.am));
                    }
                }
                if (AdsService.this.mHandler == null) {
                    return;
                }
                if (AdsService.gf.entrySet().size() > 0) {
                    AdsService.this.mHandler.postDelayed(AdsService.this.gg, 10000);
                    return;
                }
                AdsService.this.mHandler.removeCallbacksAndMessages((Object) null);
                Handler unused = AdsService.this.mHandler = null;
            } catch (Throwable unused2) {
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler;
    Messenger mMessenger = new Messenger(new a());

    public boolean onUnbind(Intent intent) {
        return false;
    }

    class a extends Handler {
        a() {
        }

        public final void handleMessage(Message message) {
            if (message.what != 8348) {
                super.handleMessage(message);
                return;
            }
            Bundle data = message.getData();
            AdsService.this.addPack(data.getString("package"), data, (ResultReceiver) data.getParcelable("receiver"));
            new StringBuilder("Package added: ").append(data.getString("package"));
        }
    }

    public AdsService() {
        super("AdsService");
    }

    public void onDestroy() {
        super.onDestroy();
        gf.clear();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.mHandler = null;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent.getIntExtra("added_info", 0) == 8348) {
            addPack(intent.getStringExtra("package"), intent.getExtras(), (ResultReceiver) intent.getParcelableExtra("receiver"));
            new StringBuilder("Package added: ").append(intent.getStringExtra("package"));
        }
    }

    public void addPack(String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (gf == null) {
            gf = new HashMap<>();
        }
        if (gf.containsKey(str)) {
            n nVar = gf.get(str);
            if (nVar == null) {
                addPack(str, bundle, resultReceiver);
                return;
            }
            nVar.hz = bundle;
            gf.put(str, nVar);
            return;
        }
        n nVar2 = new n();
        nVar2.am = str;
        nVar2.hz = bundle;
        gf.put(str, nVar2);
        if (this.mHandler == null) {
            Handler handler = new Handler();
            this.mHandler = handler;
            handler.postDelayed(this.gg, 10000);
        }
    }

    private void a(String str, Bundle bundle, ResultReceiver resultReceiver) {
        n nVar = gf.get(str);
        if (nVar == null) {
            addPack(str, bundle, resultReceiver);
            return;
        }
        nVar.hz = bundle;
        gf.put(str, nVar);
    }

    /* access modifiers changed from: private */
    public boolean Q(String str) {
        try {
            getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(final n nVar) {
        new Thread(new Runnable() {
            public final void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("guid", nVar.hz.getString("guid"));
                String str = "";
                hashMap.put("zone", nVar.hz.getString("zone") == null ? str : nVar.hz.getString("zone"));
                hashMap.put("adsID", f.b((Context) AdsService.this, true));
                hashMap.put("isApk", "0");
                hashMap.put("bannerid", nVar.hz.getString("bannerid"));
                hashMap.put("placementid", nVar.hz.getString("placementid"));
                hashMap.put("vid", nVar.hz.getString("vid"));
                hashMap.put("tid", nVar.hz.getString("tid", str));
                hashMap.put("osid", "100");
                hashMap.put("auid", nVar.hz.getString("auid", str));
                String installerPackageName = AdsService.this.getPackageManager().getInstallerPackageName(nVar.am);
                if (installerPackageName != null) {
                    str = installerPackageName;
                }
                hashMap.put("installer", str);
                try {
                    f.a("https://admin.appnext.com/AdminService.asmx/SetOpenV1", (HashMap<String, String>) hashMap);
                } catch (IOException unused) {
                }
            }
        }).start();
    }

    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }
}
