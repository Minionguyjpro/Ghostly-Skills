package com.appsgeyser.multiTabApp.utils;

import com.wGhostlySkills_14510784.R;
import java.util.HashMap;
import java.util.Map;

public enum ThemePreset {
    DEFAULT(R.style.AppTheme, R.style.AppThemeNoActionBar, "AppThemeDefault", "AppThemeDefaultNoActionBar", R.string.theming_default, R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark),
    BLUE(R.style.AppThemeBlue, R.style.AppThemeBlueNoActionBar, "AppThemeBlue", "AppThemeBlueNoActionBar", R.string.theming_blue, R.color.blueAccent, R.color.bluePrimary, R.color.bluePrimaryDark),
    RED(R.style.AppThemeRed, R.style.AppThemeRedNoActionBar, "AppThemeRed", "AppThemeRedNoActionBar", R.string.theming_red, R.color.redAccent, R.color.redPrimary, R.color.redPrimaryDark),
    PINK(R.style.AppThemePink, R.style.AppThemePinkNoActionBar, "AppThemePink", "AppThemePinkNoActionBar", R.string.theming_pink, R.color.pinkAccent, R.color.pinkPrimary, R.color.pinkPrimaryDark),
    PURPLE(R.style.AppThemePurple, R.style.AppThemePurpleNoActionBar, "AppThemePurple", "AppThemePurpleNoActionBar", R.string.theming_purple, R.color.purpleAccent, R.color.purplePrimary, R.color.purplePrimaryDark),
    INDIGO(R.style.AppThemeIndigo, R.style.AppThemeIndigoNoActionBar, "AppThemeIndigo", "AppThemeIndigoNoActionBar", R.string.theming_indigo, R.color.indigoAccent, R.color.indigoPrimary, R.color.indigoPrimaryDark),
    TEAL(R.style.AppThemeTeal, R.style.AppThemeTealNoActionBar, "AppThemeTeal", "AppThemeTealNoActionBar", R.string.theming_teal, R.color.tealAccent, R.color.tealPrimary, R.color.tealPrimaryDark),
    GREEN(R.style.AppThemeGreen, R.style.AppThemeGreenNoActionBar, "AppThemeGreen", "AppThemeGreenNoActionBar", R.string.theming_green, R.color.greenAccent, R.color.greenPrimary, R.color.greenPrimaryDark),
    YELLOW(R.style.AppThemeYellow, R.style.AppThemeYellowNoActionBar, "AppThemeYellow", "AppThemeYellowNoActionBar", R.string.theming_yellow, R.color.yellowAccent, R.color.yellowPrimary, R.color.yellowPrimaryDark),
    ORANGE(R.style.AppThemeOrange, R.style.AppThemeOrangeNoActionBar, "AppThemeOrange", "AppThemeOrangeNoActionBar", R.string.theming_orange, R.color.orangeAccent, R.color.orangePrimary, R.color.orangePrimaryDark),
    BROWN(R.style.AppThemeBrown, R.style.AppThemeBrownNoActionBar, "AppThemeBrown", "AppThemeBrownNoActionBar", R.string.theming_brown, R.color.brownAccent, R.color.brownPrimary, R.color.brownPrimaryDark),
    GREY(R.style.AppThemeGrey, R.style.AppThemeGreyNoActionBar, "AppThemeGrey", "AppThemeGreyNoActionBar", R.string.theming_grey, R.color.greyAccent, R.color.greyPrimary, R.color.greyPrimaryDark),
    BLACK(R.style.AppThemeBlack, R.style.AppThemeBlackNoActionBar, "AppThemeBlack", "AppThemeBlackNoActionBar", R.string.theming_black, R.color.blackAccent, R.color.blackPrimary, R.color.blackPrimaryDark);
    
    private static final Map<String, ThemePreset> themeMap = null;
    private static final Map<String, ThemePreset> themeMapNoActionBar = null;
    public final int themeColorAccentId;
    public final int themeColorPrimaryDarkId;
    public final int themeColorPrimaryId;
    public final int themeId;
    public final String themeName;
    public final int themeNoActionBarId;
    public final String themeNoActionBarName;
    public final int themeTitleId;

    static {
        int i;
        themeMap = new HashMap();
        for (ThemePreset themePreset : values()) {
            themeMap.put(themePreset.themeName, themePreset);
        }
        themeMapNoActionBar = new HashMap();
        for (ThemePreset themePreset2 : values()) {
            themeMapNoActionBar.put(themePreset2.themeNoActionBarName, themePreset2);
        }
    }

    private ThemePreset(int i, int i2, String str, String str2, int i3, int i4, int i5, int i6) {
        this.themeId = i;
        this.themeNoActionBarId = i2;
        this.themeName = str;
        this.themeNoActionBarName = str2;
        this.themeTitleId = i3;
        this.themeColorAccentId = i4;
        this.themeColorPrimaryId = i5;
        this.themeColorPrimaryDarkId = i6;
    }

    public static ThemePreset findByName(String str) {
        return themeMap.get(str);
    }

    public static ThemePreset findByNoActionBarName(String str) {
        return themeMapNoActionBar.get(str);
    }
}
