package com.appsgeyser.multiTabApp.controllers;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.ui.views.TabFragment;
import com.wGhostlySkills_14510784.R;
import java.util.HashMap;

public class TabsAdapter extends FragmentPagerAdapter {
    private String _injectionJs;
    private HashMap<Integer, TabFragment> _tabFragments = new HashMap<>();
    private WidgetsController _widgetsController = Factory.getInstance().getWidgetsController();
    private boolean shouldAddOfferWall;

    public TabsAdapter(boolean z) {
        super(Factory.getInstance().getMainNavigationActivity().getSupportFragmentManager());
        this.shouldAddOfferWall = z;
    }

    public Fragment getItem(int i) {
        WidgetEntity tabByPosition = this._widgetsController.getTabByPosition(i);
        TabFragment newInstance = TabFragment.newInstance(tabByPosition.getTabId(), this._injectionJs, tabByPosition.getTabType());
        this._tabFragments.put(Integer.valueOf(i), newInstance);
        return newInstance;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return (Fragment) super.instantiateItem(viewGroup, i);
    }

    public Fragment getFragmentByPosition(int i) {
        if (this._tabFragments.size() > i) {
            return this._tabFragments.get(Integer.valueOf(i));
        }
        return null;
    }

    public int getCount() {
        if (this.shouldAddOfferWall) {
            return this._widgetsController.tabsCount() + 1;
        }
        return this._widgetsController.tabsCount();
    }

    public CharSequence getPageTitle(int i) {
        if (!this.shouldAddOfferWall || i != this._widgetsController.tabsCount()) {
            return this._widgetsController.getTabByPosition(i).getTabName();
        }
        return Factory.getInstance().getMainNavigationActivity().getString(R.string.menuOfferWallTitle);
    }
}
