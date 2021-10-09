package com.appsgeyser.multiTabApp.ui.navigationdrawerwidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;

class NavigationDrawerWidgetAdapter extends ArrayAdapter<DrawerWidgetModel> {
    private final ArrayList<DrawerWidgetModel> objects;

    public int getCount() {
        return this.objects.size();
    }

    public DrawerWidgetModel getItem(int i) {
        return this.objects.get(i);
    }

    NavigationDrawerWidgetAdapter(Context context, ArrayList<DrawerWidgetModel> arrayList) {
        super(context, 0, arrayList);
        this.objects = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DrawerWidgetModel drawerWidgetModel = this.objects.get(i);
        if (view == null) {
            LayoutInflater layoutInflater = ((MainNavigationActivity) getContext()).getLayoutInflater();
            if (!drawerWidgetModel.isDivider() && drawerWidgetModel.getImageDrawable() != null) {
                view = layoutInflater.inflate(R.layout.navigation_drawer_widget_list_item_with_icon, viewGroup, false);
            } else if (drawerWidgetModel.isDivider() || drawerWidgetModel.getImageDrawable() != null) {
                view = layoutInflater.inflate(R.layout.list_view_divider, viewGroup, false);
                view.setOnClickListener((View.OnClickListener) null);
                view.setOnLongClickListener((View.OnLongClickListener) null);
                view.setLongClickable(false);
            } else {
                view = layoutInflater.inflate(R.layout.navigation_drawer_widget_list_item, viewGroup, false);
            }
        }
        DrawerWidgetModel item = getItem(i);
        if (item != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.navigation_drawer_widget_list_item_with_icon_icon);
            TextView textView = (TextView) view.findViewById(R.id.drawer_element_textView_description);
            if (imageView != null) {
                imageView.setImageDrawable(item.getImageDrawable());
            }
            if (textView != null) {
                textView.setText(item.getDescription());
            }
        }
        return view;
    }
}
