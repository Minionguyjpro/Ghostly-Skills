package com.mopub.common;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesHelper {
    public static final String DEFAULT_PREFERENCE_NAME = "mopubSettings";

    private SharedPreferencesHelper() {
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        Preconditions.checkNotNull(context);
        return context.getSharedPreferences(DEFAULT_PREFERENCE_NAME, 0);
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        return context.getSharedPreferences(str, 0);
    }
}
