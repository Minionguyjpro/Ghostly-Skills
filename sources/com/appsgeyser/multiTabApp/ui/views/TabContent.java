package com.appsgeyser.multiTabApp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.appsgeyser.multiTabApp.controllers.ITabContentController;

public class TabContent extends RelativeLayout {

    public enum TabType {
        WEB,
        PDF
    }

    /* access modifiers changed from: protected */
    public void init(ITabContentController iTabContentController) {
    }

    protected TabContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected TabContent(Context context) {
        super(context);
    }
}
