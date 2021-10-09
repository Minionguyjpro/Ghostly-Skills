package com.integralads.avid.library.mopub.activity;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import com.integralads.avid.library.mopub.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvidActivityStack {
    private static AvidActivityStack avidActivityStackInstance = new AvidActivityStack();
    private final ArrayList<AvidActivity> activities = new ArrayList<>();

    public static AvidActivityStack getInstance() {
        return avidActivityStackInstance;
    }

    public void addActivity(Activity activity) {
        if (find(activity) == null) {
            this.activities.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        ArrayList arrayList = new ArrayList();
        Iterator<AvidActivity> it = this.activities.iterator();
        View view = null;
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (isFinished(next)) {
                it.remove();
            } else {
                View rootView = getRootView(next);
                if (rootView != null) {
                    view = rootView;
                }
            }
        }
        if (view != null) {
            arrayList.add(view);
        }
        return arrayList;
    }

    public void cleanup() {
        this.activities.clear();
    }

    /* access modifiers changed from: package-private */
    public AvidActivity find(Activity activity) {
        Iterator<AvidActivity> it = this.activities.iterator();
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (next.contains(activity)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isFinished(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
        if (activity == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    private View getRootView(AvidActivity avidActivity) {
        Window window;
        View decorView;
        Activity activity = (Activity) avidActivity.get();
        if (activity == null || (window = activity.getWindow()) == null || !activity.hasWindowFocus() || (decorView = window.getDecorView()) == null || !decorView.isShown()) {
            return null;
        }
        return decorView;
    }
}
