package com.appnext.ads.fullscreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.appnext.R;
import com.appnext.ads.a;
import com.appnext.ads.b;
import com.appnext.ads.c;
import com.appnext.base.b.d;
import com.appnext.core.Ad;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.j;
import com.appnext.core.p;
import com.appnext.core.q;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public class FullscreenActivity extends AppnextActivity implements h, i, j, e.a {
    private p aB;
    private boolean aC = true;
    /* access modifiers changed from: private */
    public AppnextAd aD;
    /* access modifiers changed from: private */
    public AppnextAd aE;
    b aF;
    private HashMap<String, Integer> aG;
    private Video aH;
    Runnable aI = new Runnable() {
        public final void run() {
            if (FullscreenActivity.this.userAction != null) {
                FullscreenActivity.this.userAction.e(FullscreenActivity.this.aD);
            }
            FullscreenActivity.this.report(a.G, "S2");
        }
    };
    Runnable aJ = new Runnable() {
        public final void run() {
            FullscreenActivity fullscreenActivity = FullscreenActivity.this;
            fullscreenActivity.a(fullscreenActivity.aD, (e.a) null);
            FullscreenActivity.this.report(a.H, "S2");
        }
    };
    private ArrayList<AppnextAd> ads;
    private boolean finished = false;
    private Handler mHandler;
    private int state = 0;
    private int type;

    private static void c() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Fragment fragment;
        if (bundle != null) {
            this.aG = (HashMap) bundle.getSerializable("templates");
            this.state = bundle.getInt("state");
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Configuration configuration = getResources().getConfiguration();
            configuration.setLayoutDirection(new Locale("en"));
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
        super.onCreate(bundle);
        if (Video.currentAd == null) {
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        if (Video.currentAd instanceof RewardedVideo) {
            this.aH = new RewardedVideo((Context) this, (RewardedVideo) Video.currentAd);
        } else {
            this.aH = new FullScreenVideo((Context) this, (FullScreenVideo) Video.currentAd);
        }
        String orientation = d().getOrientation();
        char c = 65535;
        switch (orientation.hashCode()) {
            case 729267099:
                if (orientation.equals(Ad.ORIENTATION_PORTRAIT)) {
                    c = 3;
                    break;
                }
                break;
            case 1430647483:
                if (orientation.equals(Ad.ORIENTATION_LANDSCAPE)) {
                    c = 2;
                    break;
                }
                break;
            case 1673671211:
                if (orientation.equals(Ad.ORIENTATION_AUTO)) {
                    c = 1;
                    break;
                }
                break;
            case 2129065206:
                if (orientation.equals(Ad.ORIENTATION_DEFAULT)) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0 || c == 1) {
            if (getResources().getConfiguration().orientation == 2) {
                setRequestedOrientation(6);
            } else {
                setRequestedOrientation(7);
            }
        } else if (c == 2) {
            setRequestedOrientation(6);
        } else if (c == 3) {
            setRequestedOrientation(7);
        }
        this.mHandler = new Handler();
        this.placementID = getIntent().getExtras().getString("id");
        int i = getIntent().getExtras().getInt("type");
        this.type = i;
        if (i == 1) {
            this.aB = c.m();
        } else {
            this.aB = f.q();
        }
        this.aC = Boolean.parseBoolean(this.aB.get("can_close"));
        if (d() instanceof FullScreenVideo) {
            this.aC = ((FullScreenVideo) d()).isBackButtonCanClose();
        }
        String str = "";
        if (bundle == null) {
            ArrayList<AppnextAd> f = b.j().f(d());
            this.ads = f;
            if (f == null) {
                onError(AppnextError.NO_ADS);
                finish();
                return;
            }
            this.aD = a(f, this.placementID, str);
        } else {
            this.ads = (ArrayList) bundle.getSerializable("ads");
            this.aD = (AppnextAd) bundle.getSerializable("currentAd");
        }
        if (this.aD == null) {
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        setContentView(R.layout.apnxt_video_activity);
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            if (d() instanceof RewardedVideo) {
                str = ((RewardedVideo) d()).getMode();
            }
            if (str.equals(RewardedVideo.VIDEO_MODE_DEFAULT)) {
                str = getConfig().get("default_mode");
            }
            if (this.type != 2 || !str.equals("multi")) {
                if (this.type == 2) {
                    report("normal");
                }
                fragment = new g();
                bundle2.putBoolean("showCta", d().isShowCta());
                this.state = 1;
            } else {
                fragment = new e();
                bundle2.putInt(d.fl, ((RewardedVideo) d()).getMultiTimerLength());
                report("multi");
            }
            fragment.setArguments(bundle2);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add(R.id.ll, fragment, "fragment");
            try {
                beginTransaction.commit();
            } catch (Exception unused) {
                finish();
                return;
            }
        } else {
            this.finished = bundle.getBoolean("finished", true);
        }
        this.gl = (RelativeLayout) findViewById(R.id.ll);
        this.userAction = new q(this, new q.a() {
            public final void report(String str) {
                FullscreenActivity.this.report(str);
            }

            public final Ad e() {
                return Video.currentAd;
            }

            public final AppnextAd f() {
                return FullscreenActivity.this.aE;
            }

            public final p g() {
                return FullscreenActivity.this.getConfig();
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void onError(String str) {
        if (d() != null && d().getOnAdErrorCallback() != null) {
            d().getOnAdErrorCallback().adError(str);
        }
    }

    /* access modifiers changed from: protected */
    public final p getConfig() {
        if (this.aB == null) {
            if (this.type == 1) {
                this.aB = c.m();
            } else {
                this.aB = f.q();
            }
        }
        return this.aB;
    }

    /* access modifiers changed from: protected */
    public final void a(AppnextAd appnextAd, e.a aVar) {
        super.a(appnextAd, (e.a) new e.a() {
            public final void error(String str) {
            }

            public final void onMarket(String str) {
            }
        });
    }

    private Uri a() {
        String str;
        String videoUrl = b.getVideoUrl(this.aD, d().getVideoLength());
        String c = b.c(videoUrl);
        if (Video.getCacheVideo()) {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + d().rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            Uri parse = Uri.parse(str + c);
            new StringBuilder("playing video ").append(parse.getPath());
            return parse;
        }
        Uri parse2 = Uri.parse(videoUrl);
        new StringBuilder("playing video from web: ").append(videoUrl);
        new StringBuilder("file not found: ").append(file.getAbsolutePath());
        return parse2;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("finished", this.finished);
        bundle.putInt("type", this.type);
        bundle.putSerializable("templates", this.aG);
        bundle.putSerializable("ads", this.ads);
        bundle.putInt("state", this.state);
        bundle.putSerializable("currentAd", this.aD);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.finished = bundle.getBoolean("finished", true);
        this.type = bundle.getInt("type");
        this.aG = (HashMap) bundle.getSerializable("templates");
    }

    private boolean b() {
        return d().isBackButtonCanClose();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.aI);
        this.mHandler.removeCallbacks(this.aJ);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() | 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|5|6|(1:8)|9|10|11|(1:13)|14|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0047 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b A[Catch:{ all -> 0x0052 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r4 = this;
            super.onDestroy()
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0037 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            r1.<init>()     // Catch:{ all -> 0x0037 }
            java.io.File r2 = r4.getFilesDir()     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x0037 }
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "/data/appnext/videos/"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "tmp/vid"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            com.appnext.ads.fullscreen.Video r2 = r4.d()     // Catch:{ all -> 0x0037 }
            long r2 = r2.rnd     // Catch:{ all -> 0x0037 }
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0037 }
            r0.<init>(r1)     // Catch:{ all -> 0x0037 }
            com.appnext.core.f.a((java.io.File) r0)     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r0 = 0
            android.os.Handler r1 = r4.mHandler     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0041
            android.os.Handler r1 = r4.mHandler     // Catch:{ all -> 0x0047 }
            r1.removeCallbacksAndMessages(r0)     // Catch:{ all -> 0x0047 }
        L_0x0041:
            r4.mHandler = r0     // Catch:{ all -> 0x0047 }
            r4.aE = r0     // Catch:{ all -> 0x0047 }
            r4.aD = r0     // Catch:{ all -> 0x0047 }
        L_0x0047:
            com.appnext.ads.b r1 = r4.aF     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            com.appnext.ads.b r1 = r4.aF     // Catch:{ all -> 0x0052 }
            r1.a((android.content.Context) r4)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            r4.aF = r0     // Catch:{ all -> 0x0052 }
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.fullscreen.FullscreenActivity.onDestroy():void");
    }

    private void a(AppnextAd appnextAd) {
        b(appnextAd, (e.a) this);
    }

    /* access modifiers changed from: protected */
    public final void b(AppnextAd appnextAd, e.a aVar) {
        if (appnextAd != null) {
            this.aE = appnextAd;
            if (d().getOnAdClickedCallback() != null) {
                d().getOnAdClickedCallback().adClicked();
            }
            if (this.finished || !(d() instanceof RewardedVideo)) {
                super.b(appnextAd, aVar);
            }
        }
    }

    public void onMarket(String str) {
        ba();
        this.finished = true;
        Collections.shuffle(this.ads, new Random(System.nanoTime()));
        this.ads.remove(this.aD);
        this.ads.add(0, this.aD);
        d dVar = new d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.ll, dVar, "fragment");
        try {
            beginTransaction.commit();
        } catch (Exception unused) {
            finish();
        }
    }

    public void error(String str) {
        ba();
        report(a.A);
    }

    private void onClose() {
        try {
            b.j().a(this.aD.getBannerID(), (Ad) d());
            if (!(d() == null || d().getOnAdClosedCallback() == null)) {
                d().getOnAdClosedCallback().onAdClosed();
            }
            Video.currentAd = null;
        } catch (Throwable unused) {
        }
    }

    public void videoStarted() {
        this.mHandler.postDelayed(this.aI, Long.parseLong(this.aB.get("postpone_impression_sec")) * 1000);
        if (Boolean.parseBoolean(this.aB.get("pview"))) {
            this.mHandler.postDelayed(this.aJ, Long.parseLong(this.aB.get("postpone_vta_sec")) * 1000);
        }
        if (d() != null && d().getOnAdOpenedCallback() != null) {
            d().getOnAdOpenedCallback().adOpened();
        }
    }

    public void videoEnded() {
        this.state = 2;
        this.finished = true;
        if (!(d() == null || d().getOnVideoEndedCallback() == null)) {
            d().getOnVideoEndedCallback().videoEnded();
        }
        new Thread(new Runnable() {
            public final void run() {
                RewardedServerSidePostback rewardedServerSidePostback;
                if ((FullscreenActivity.this.d() instanceof RewardedVideo) && (rewardedServerSidePostback = ((RewardedVideo) FullscreenActivity.this.d()).getRewardedServerSidePostback()) != null) {
                    HashMap<String, String> p = rewardedServerSidePostback.p();
                    p.put("placementId", FullscreenActivity.this.placementID);
                    try {
                        f.a("https://admin.appnext.com/adminService.asmx/SetRewards", p);
                    } catch (IOException unused) {
                    }
                }
            }
        }).start();
        Collections.shuffle(this.ads, new Random(System.nanoTime()));
        this.ads.remove(this.aD);
        this.ads.add(0, this.aD);
        d dVar = new d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.ll, dVar, "fragment");
        try {
            beginTransaction.commit();
            AppnextAd appnextAd = this.aE;
            if (appnextAd != null) {
                super.b(appnextAd, this);
                report(a.Q);
                return;
            }
            report(a.P);
            if (Integer.parseInt(getConfig().get("clickType_b")) > new Random(System.nanoTime()).nextInt(100)) {
                installClicked(getSelectedAd());
            }
        } catch (Exception unused) {
            finish();
        }
    }

    public void videoSelected(AppnextAd appnextAd) {
        this.aD = appnextAd;
        g gVar = new g();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putBoolean("showCta", d().isShowCta());
        gVar.setArguments(bundle);
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.ll, gVar, "fragment");
        try {
            beginTransaction.commit();
        } catch (Exception unused) {
            finish();
        }
    }

    public ArrayList<AppnextAd> getPreRollAds() {
        if (this.ads == null) {
            this.ads = b.j().f(d());
        }
        ArrayList<AppnextAd> arrayList = new ArrayList<>();
        AppnextAd a2 = a(this.ads, this.placementID, "");
        arrayList.add(a2);
        AppnextAd a3 = a(this.ads, this.placementID, a2.getBannerID());
        if (a3 != null && !a3.getBannerID().equals(arrayList.get(0).getBannerID())) {
            arrayList.add(a3);
        }
        return arrayList;
    }

    public void privacyClicked() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(f.g(this.aD)));
            intent.setFlags(268435456);
            startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public void installClicked(AppnextAd appnextAd) {
        if (this.state == 1 && !isRewarded()) {
            a((ViewGroup) this.gl, getResources().getDrawable(R.drawable.apnxt_loader));
        }
        b(appnextAd, (e.a) this);
    }

    public void closeClicked() {
        if (this.state != 1 || isRewarded() || Integer.parseInt(getConfig().get("clickType_a")) <= new Random(System.nanoTime()).nextInt(100)) {
            onClose();
            finish();
            return;
        }
        installClicked(getSelectedAd());
    }

    public AppnextAd getSelectedAd() {
        return this.aD;
    }

    public boolean showClose() {
        return (d() instanceof FullScreenVideo) && ((FullScreenVideo) d()).isShowClose();
    }

    public p getConfigManager() {
        return getConfig();
    }

    public int getTemplate(String str) {
        if (this.aG == null) {
            this.aG = new HashMap<>();
        }
        if (!this.aG.containsKey(str)) {
            String a2 = c.a(getConfig().get(str));
            Resources resources = getResources();
            int identifier = resources.getIdentifier("apnxt_" + a2, "layout", getPackageName());
            if (identifier == 0) {
                Resources resources2 = getResources();
                identifier = resources2.getIdentifier("apnxt_" + str.toLowerCase() + "t1", "layout", getPackageName());
            }
            this.aG.put(str, Integer.valueOf(identifier));
        }
        return this.aG.get(str).intValue();
    }

    public boolean getMute() {
        return d().getMute();
    }

    public void report(String str, String str2) {
        b(str, getResources().getResourceEntryName(getTemplate(str2)));
    }

    public ArrayList<AppnextAd> getPostRollAds() {
        return this.ads;
    }

    public boolean isRewarded() {
        return d() instanceof RewardedVideo;
    }

    public String getLanguage() {
        return this.aH.getLanguage();
    }

    public String getCtaText() {
        String buttonText = new FullscreenAd(this.aD).getButtonText();
        AppnextAd appnextAd = this.aD;
        String str = com.appnext.core.a.b.hY;
        if (appnextAd == null || !buttonText.equals("")) {
            com.appnext.core.a.b bp = com.appnext.core.a.b.bp();
            String language = this.aH.getLanguage();
            if (!isInstalled()) {
                str = com.appnext.core.a.b.hX;
            }
            return bp.b(language, str, buttonText);
        } else if (isInstalled()) {
            return com.appnext.core.a.b.bp().b(this.aH.getLanguage(), str, "Open");
        } else {
            return com.appnext.core.a.b.bp().b(this.aH.getLanguage(), com.appnext.core.a.b.hX, "Install");
        }
    }

    public boolean isInstalled() {
        try {
            return f.c(this, this.aD.getAdPackage());
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getCaptionTextTime() {
        return d().getRollCaptionTime();
    }

    public long closeDelay() {
        if (d() instanceof FullScreenVideo) {
            return ((FullScreenVideo) d()).getCloseDelay();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(ArrayList<AppnextAd> arrayList, String str, String str2) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (b(next) && !a(next.getBannerID(), str) && !next.getBannerID().equals(str2)) {
                return next;
            }
        }
        j.bj().ab(str);
        Iterator<AppnextAd> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AppnextAd next2 = it2.next();
            if (b(next2) && !a(next2.getBannerID(), str)) {
                return next2;
            }
        }
        return null;
    }

    protected static boolean a(String str, String str2) {
        return j.bj().o(str, str2);
    }

    private static boolean b(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        Resources resources = getResources();
        b(str, resources.getResourceEntryName(getTemplate("S" + (this.state + 1))));
    }

    private void b(String str, String str2) {
        try {
            f.a(d().getTID(), d().getVID(), d().getAUID(), this.placementID, d().getSessionId(), str, str2, this.aD != null ? this.aD.getBannerID() : "", this.aD != null ? this.aD.getCampaignID() : "");
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    public Video d() {
        if (Video.currentAd != null) {
            return Video.currentAd;
        }
        return this.aH;
    }

    public void onBackPressed() {
        if (d().isBackButtonCanClose()) {
            onClose();
            super.onBackPressed();
        }
    }

    public Uri getSelectedVideoUri() {
        String str;
        String videoUrl = b.getVideoUrl(this.aD, d().getVideoLength());
        String c = b.c(videoUrl);
        if (Video.getCacheVideo()) {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + d().rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            Uri parse = Uri.parse(str + c);
            new StringBuilder("playing video ").append(parse.getPath());
            return parse;
        }
        Uri parse2 = Uri.parse(videoUrl);
        new StringBuilder("playing video from web: ").append(videoUrl);
        new StringBuilder("file not found: ").append(file.getAbsolutePath());
        return parse2;
    }
}
