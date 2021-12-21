package com.onesignal;

import java.util.Set;

class OSSharedPreferencesWrapper implements OSSharedPreferences {
    public String getOutcomesV2KeyName() {
        return "PREFS_OS_OUTCOMES_V2";
    }

    OSSharedPreferencesWrapper() {
    }

    public String getPreferencesName() {
        return OneSignalPrefs.PREFS_ONESIGNAL;
    }

    public String getString(String str, String str2, String str3) {
        return OneSignalPrefs.getString(str, str2, str3);
    }

    public void saveString(String str, String str2, String str3) {
        OneSignalPrefs.saveString(str, str2, str3);
    }

    public boolean getBool(String str, String str2, boolean z) {
        return OneSignalPrefs.getBool(str, str2, z);
    }

    public void saveBool(String str, String str2, boolean z) {
        OneSignalPrefs.saveBool(str, str2, z);
    }

    public int getInt(String str, String str2, int i) {
        return OneSignalPrefs.getInt(str, str2, i);
    }

    public void saveInt(String str, String str2, int i) {
        OneSignalPrefs.saveInt(str, str2, i);
    }

    public Set<String> getStringSet(String str, String str2, Set<String> set) {
        return OneSignalPrefs.getStringSet(str, str2, set);
    }

    public void saveStringSet(String str, String str2, Set<String> set) {
        OneSignalPrefs.saveStringSet(str, str2, set);
    }
}
