package com.startapp.android.publish.common.metaData;

import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: StartAppSDK */
public class MetaDataStyle implements Serializable {
    public static final int DEFAULT_ITEM_BOTTOM = -8750199;
    public static final Integer DEFAULT_ITEM_DESC_TEXT_COLOR = -1;
    public static final Set<String> DEFAULT_ITEM_DESC_TEXT_DECORATION = new HashSet();
    public static final Integer DEFAULT_ITEM_DESC_TEXT_SIZE = 14;
    public static final Integer DEFAULT_ITEM_TITLE_TEXT_COLOR = -1;
    public static final Set<String> DEFAULT_ITEM_TITLE_TEXT_DECORATION = new HashSet(Arrays.asList(new String[]{"BOLD"}));
    public static final Integer DEFAULT_ITEM_TITLE_TEXT_SIZE = 18;
    public static final int DEFAULT_ITEM_TOP = -14014151;
    private static final long serialVersionUID = 1;
    private Integer itemDescriptionTextColor = DEFAULT_ITEM_DESC_TEXT_COLOR;
    @f(b = HashSet.class)
    private Set<String> itemDescriptionTextDecoration = DEFAULT_ITEM_DESC_TEXT_DECORATION;
    private Integer itemDescriptionTextSize = DEFAULT_ITEM_DESC_TEXT_SIZE;
    private Integer itemGradientBottom = Integer.valueOf(DEFAULT_ITEM_BOTTOM);
    private Integer itemGradientTop = Integer.valueOf(DEFAULT_ITEM_TOP);
    private Integer itemTitleTextColor = DEFAULT_ITEM_TITLE_TEXT_COLOR;
    @f(b = HashSet.class)
    private Set<String> itemTitleTextDecoration = DEFAULT_ITEM_TITLE_TEXT_DECORATION;
    private Integer itemTitleTextSize = DEFAULT_ITEM_TITLE_TEXT_SIZE;
    private String name = "";

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getItemGradientTop() {
        return this.itemGradientTop;
    }

    public Integer getItemGradientBottom() {
        return this.itemGradientBottom;
    }

    public Integer getItemTitleTextSize() {
        return this.itemTitleTextSize;
    }

    public Integer getItemTitleTextColor() {
        return this.itemTitleTextColor;
    }

    public Set<String> getItemTitleTextDecoration() {
        return this.itemTitleTextDecoration;
    }

    public Integer getItemDescriptionTextSize() {
        return this.itemDescriptionTextSize;
    }

    public Integer getItemDescriptionTextColor() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> getItemDescriptionTextDecoration() {
        return this.itemDescriptionTextDecoration;
    }

    public void setItemGradientTop(Integer num) {
        this.itemGradientTop = num;
    }

    public void setItemGradientBottom(Integer num) {
        this.itemGradientBottom = num;
    }

    public void setItemTitleTextSize(Integer num) {
        this.itemTitleTextSize = num;
    }

    public void setItemTitleTextColor(Integer num) {
        this.itemTitleTextColor = num;
    }

    public void setItemTitleTextDecoration(Set<String> set) {
        this.itemTitleTextDecoration = set;
    }

    public void setItemDescriptionTextSize(Integer num) {
        this.itemDescriptionTextSize = num;
    }

    public void setItemDescriptionTextColor(Integer num) {
        this.itemDescriptionTextColor = num;
    }

    public void setItemDescriptionTextDecoration(Set<String> set) {
        this.itemDescriptionTextDecoration = set;
    }
}
