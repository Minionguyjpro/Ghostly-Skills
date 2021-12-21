package com.appsgeyser.multiTabApp.ui.menu;

import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.Iterator;

public class MenuStructure {
    static ArrayList<Item> menuItems;

    static {
        ArrayList<Item> arrayList = new ArrayList<>();
        menuItems = arrayList;
        arrayList.add(new Item(R.id.webapp_refresh, R.drawable.outline_refresh_black_24));
        menuItems.add(new Item(R.id.webapp_share, R.drawable.outline_share_black_24));
        menuItems.add(new Item(R.id.webapp_about, R.drawable.outline_info_black_24));
        menuItems.add(new Item(R.id.webapp_exit, R.drawable.outline_cancel_black_24));
        menuItems.add(new Item(R.id.webapp_theming, R.drawable.outline_palette_black_24));
        menuItems.add(new Item(R.id.webapp_back, R.drawable.outline_arrow_back_black_24));
        menuItems.add(new Item(R.id.webapp_forward, R.drawable.outline_arrow_forward_black_24));
        menuItems.add(new Item(R.id.webapp_request_desktop, R.drawable.outline_desktop_windows_black_24));
        menuItems.add(new Item(R.id.webapp_add_to_home, R.drawable.outline_offline_pin_black_24));
        menuItems.add(new Item(R.id.webapp_home, R.drawable.outline_home_black_24));
        menuItems.add(new Item(R.id.webapp_downloads_list, R.drawable.outline_archive_black_24));
        menuItems.add(new Item(R.id.webapp_settings, R.drawable.outline_settings_black_24));
    }

    public static class Item {
        private int _iconResourceId;
        private int _itemId;

        public Item(int i, int i2) {
            setItemId(i);
            setIconResourceId(i2);
        }

        public int getItemId() {
            return this._itemId;
        }

        public void setItemId(int i) {
            this._itemId = i;
        }

        public int getIconResourceId() {
            return this._iconResourceId;
        }

        public void setIconResourceId(int i) {
            this._iconResourceId = i;
        }
    }

    public static int getIconResourceIdByItemId(int i) {
        Iterator<Item> it = menuItems.iterator();
        while (it.hasNext()) {
            Item next = it.next();
            if (next.getItemId() == i) {
                return next.getIconResourceId();
            }
        }
        return -1000500;
    }
}
