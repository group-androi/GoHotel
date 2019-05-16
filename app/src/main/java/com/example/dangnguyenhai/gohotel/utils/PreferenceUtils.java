package com.example.dangnguyenhai.gohotel.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static String HOTEL_PREFERENCE = "HOTEL_PREFERENCE";

    public static String getLatLocation(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(HOTEL_PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getString("LAT_LOCATION", "");
    }

    public static String getLongLocation(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(HOTEL_PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getString("LONG_LOCATION", "");
    }

    public static void setLatLocation(Context context, String strLocation) {
        final SharedPreferences prefs = context.getSharedPreferences(HOTEL_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LAT_LOCATION", strLocation);
        editor.apply();
    }

    public static void setLongLocation(Context context, String strLocation) {
        final SharedPreferences prefs = context.getSharedPreferences(HOTEL_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LONG_LOCATION", strLocation);
        editor.apply();
    }
}
