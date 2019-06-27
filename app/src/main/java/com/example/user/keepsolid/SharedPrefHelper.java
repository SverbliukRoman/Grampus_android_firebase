package com.example.user.keepsolid;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class SharedPrefHelper {

    private static final String SETTINGS_FILE = "com.blacksmithdreamapps.accountingsystematomiq.settings";
    private static final String FIRST_START = "firstStart";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static boolean getFirstStart(Context context) {
        return getPreferences(context).getBoolean(FIRST_START, false);
    }

    public static void setFirstStart(Context context, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        prefs.edit().putBoolean(FIRST_START, value).apply();
    }
}
