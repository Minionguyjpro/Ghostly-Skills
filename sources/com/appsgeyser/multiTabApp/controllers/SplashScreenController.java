package com.appsgeyser.multiTabApp.controllers;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.utils.ImageReader;
import com.wGhostlySkills_14510784.R;
import java.io.IOException;

public class SplashScreenController {
    /* access modifiers changed from: private */
    public MainNavigationActivity _activity;
    private Handler _handler = new Handler();
    private ViewGroup _splashScreenView;
    /* access modifiers changed from: private */
    public Runnable hideSplashScreenViewRunnable = new Runnable() {
        public void run() {
            SplashScreenController.this._activity.showContentView();
        }
    };
    private Runnable showSplashScreenViewRunnable = new Runnable() {
        public void run() {
            SplashScreenController.this._activity.showSplashScreen();
        }
    };

    public SplashScreenController(ViewGroup viewGroup, MainNavigationActivity mainNavigationActivity) {
        this._splashScreenView = viewGroup;
        this._activity = mainNavigationActivity;
    }

    public void showSplashScreen(String str) {
        try {
            ((ImageView) this._splashScreenView.findViewById(R.id.splashScreenImage)).setImageBitmap(ImageReader.decodeFile(this._activity.getAssets().open(str), this._activity.getWindowManager().getDefaultDisplay().getWidth()));
            this._activity.runOnUiThread(this.showSplashScreenViewRunnable);
            this._handler.postDelayed(new Runnable() {
                public void run() {
                    SplashScreenController.this._activity.runOnUiThread(SplashScreenController.this.hideSplashScreenViewRunnable);
                }
            }, 2000);
        } catch (IOException e) {
            e.printStackTrace();
            this._activity.showContentView();
        }
    }
}
