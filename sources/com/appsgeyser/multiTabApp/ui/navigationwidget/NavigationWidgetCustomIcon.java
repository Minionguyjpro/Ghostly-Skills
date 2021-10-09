package com.appsgeyser.multiTabApp.ui.navigationwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class NavigationWidgetCustomIcon {
    private TextView _badge;
    private ViewGroup _iconView;
    private ImageButton _imageButton;

    public void showBadge() {
        this._badge.setVisibility(0);
    }

    public void hideBadge() {
        this._badge.setVisibility(8);
    }

    public void updateBadge(String str) {
        if (str.length() > 0) {
            showBadge();
            this._badge.setText(str);
            return;
        }
        hideBadge();
    }

    public void updateIcon(String str) {
        Context context = this._iconView.getContext();
        try {
            InputStream open = context.getAssets().open(str);
            int round = Math.round((context.getResources().getDisplayMetrics().xdpi / 160.0f) * 38.0f);
            this._imageButton.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeStream(open), round, round, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
