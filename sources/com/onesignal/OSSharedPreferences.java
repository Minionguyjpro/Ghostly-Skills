package com.onesignal;

import java.util.Set;

public interface OSSharedPreferences {
    boolean getBool(String str, String str2, boolean z);

    int getInt(String str, String str2, int i);

    String getOutcomesV2KeyName();

    String getPreferencesName();

    String getString(String str, String str2, String str3);

    Set<String> getStringSet(String str, String str2, Set<String> set);

    void saveBool(String str, String str2, boolean z);

    void saveInt(String str, String str2, int i);

    void saveString(String str, String str2, String str3);

    void saveStringSet(String str, String str2, Set<String> set);
}
