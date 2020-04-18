package com.sanchit.covid19tracker.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtil {

    private static SharedPreferences preferences =
            PreferenceManager.getDefaultSharedPreferences(CovidApp.getContext());

    private static boolean loggedIn;
    private static String fcmToken;
    private static String totalcases;


    public static void loadPreferences() {

        loggedIn = preferences.getBoolean("loggedIn", false);
        fcmToken = preferences.getString("fcm",fcmToken);
        totalcases=preferences.getString("totalcases",totalcases);

    }


    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        PreferencesUtil.loggedIn = loggedIn;
        preferences.edit().putBoolean("loggedIn", loggedIn).apply();
    }

    public static String getFcmToken() {
        return fcmToken;
    }

    public static void setFcmToken(String fcmToken) {
        PreferencesUtil.fcmToken = fcmToken;
        preferences.edit().putString("fcm",fcmToken).apply();
    }

    public static String getTotalcases() {
        return totalcases;
    }

    public static void setTotalcases(String totalcases) {
        PreferencesUtil.totalcases = totalcases;
        preferences.edit().putString("totalcases",totalcases).apply();
    }

    public static void clearPrefs() {

        preferences.edit().clear().apply();

        preferences.edit().putBoolean("loggedIn", false).apply();
        preferences.edit().putString("user_entity", null).apply();
        preferences.edit().putString("access_token", Constants.NF).apply();

        loadPreferences();
    }


}
