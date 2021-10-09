package com.appsgeyser.multiTabApp.ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.controllers.ITabContentController;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.ui.views.TabContent;

public class TabFragment extends Fragment {
    private ITabContentController _tabContentController;

    public static TabFragment newInstance(String str, String str2, String str3) {
        TabFragment tabFragment = new TabFragment();
        tabFragment.setRetainInstance(false);
        Bundle bundle = new Bundle();
        bundle.putString("TAB_ID", str);
        bundle.putString("INJECTION_JS", str2);
        bundle.putString("TAB_TYPE", str3);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this._tabContentController = Factory.getInstance().getTabContentController(Factory.getInstance().getWidgetsController().getWidgetByTabId(getArguments().getString("TAB_ID")));
        ((WebContentController) this._tabContentController).setBannerInjectionJs(getArguments().getString("INJECTION_JS"));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TabContent.TabType tabType;
        try {
            String string = getArguments().getString("TAB_TYPE");
            if (string.equals(TabContent.TabType.WEB.toString())) {
                tabType = TabContent.TabType.WEB;
            } else {
                tabType = string.equals(TabContent.TabType.PDF.toString()) ? TabContent.TabType.PDF : null;
            }
            return this._tabContentController.createTabContent(layoutInflater, viewGroup, tabType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ITabContentController getContentController() {
        return this._tabContentController;
    }
}
