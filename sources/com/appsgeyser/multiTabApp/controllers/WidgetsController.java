package com.appsgeyser.multiTabApp.controllers;

import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class WidgetsController implements Serializable {
    private Vector<WidgetEntity> _widgetCollection = new Vector<>();

    public void addWidget(WidgetEntity widgetEntity) {
        this._widgetCollection.add(widgetEntity);
        Collections.sort(this._widgetCollection, new WidgetEntityComparator());
    }

    public void removeAll() {
        this._widgetCollection.removeAllElements();
    }

    public Enumeration<WidgetEntity> getEnumeration() {
        return this._widgetCollection.elements();
    }

    public int widgetsCount() {
        return this._widgetCollection.size();
    }

    public WidgetEntity getWidgetByTabId(String str) {
        Enumeration<WidgetEntity> elements = this._widgetCollection.elements();
        while (elements.hasMoreElements()) {
            WidgetEntity nextElement = elements.nextElement();
            if (nextElement.getTabId().equalsIgnoreCase(str)) {
                return nextElement;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getWidgetPositionByTabId(String str) {
        for (int i = 0; i < this._widgetCollection.size(); i++) {
            if (this._widgetCollection.get(i).getTabId().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    class WidgetEntityComparator implements Comparator<WidgetEntity> {
        WidgetEntityComparator() {
        }

        public int compare(WidgetEntity widgetEntity, WidgetEntity widgetEntity2) {
            String id = widgetEntity.getId();
            String id2 = widgetEntity2.getId();
            if (id.indexOf("tab") == 0 && id2.indexOf("tab") == 0) {
                return Integer.valueOf(Integer.parseInt(id.substring(3))).compareTo(Integer.valueOf(Integer.parseInt(id2.substring(3))));
            }
            return widgetEntity.getId().compareTo(widgetEntity2.getId());
        }
    }

    public int tabsCount() {
        if (Factory.getInstance().getMainNavigationActivity().getConfig().getTabsPosition() == WebWidgetConfiguration.TabsPositions.BOTTOM_MENU) {
            return this._widgetCollection.size();
        }
        int i = 0;
        Iterator<WidgetEntity> it = this._widgetCollection.iterator();
        while (it.hasNext()) {
            if (it.next().isShowAsTab()) {
                i++;
            }
        }
        return i;
    }

    public WidgetEntity getTabByPosition(int i) {
        Iterator<WidgetEntity> it = this._widgetCollection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            WidgetEntity next = it.next();
            if (Factory.getInstance().getMainNavigationActivity().getConfig().getTabsPosition() == WebWidgetConfiguration.TabsPositions.BOTTOM_MENU) {
                if (i2 == i) {
                    return next;
                }
            } else if (!next.isShowAsTab()) {
                continue;
            } else if (i2 == i) {
                return next;
            }
            i2++;
        }
        return null;
    }

    public Vector<WidgetEntity> getWidgetCollection() {
        return this._widgetCollection;
    }

    public void sortCollectionByShowingTabs() {
        Vector<WidgetEntity> vector = new Vector<>();
        Vector vector2 = new Vector();
        Iterator<WidgetEntity> it = this._widgetCollection.iterator();
        while (it.hasNext()) {
            WidgetEntity next = it.next();
            if (next.isShowAsTab()) {
                vector.add(next);
            } else {
                vector2.add(next);
            }
        }
        vector.addAll(vector2);
        this._widgetCollection = vector;
    }
}
