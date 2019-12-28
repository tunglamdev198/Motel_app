package com.lamnt.motel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lamnt.motel.common.Key;

public class SharedPreferencesUtils {

    public SharedPreferencesUtils() {
    }

    public static void savePhoneNumber(String phoneNumber,Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Key.KEY_PHONE_NUMBER,phoneNumber);
        editor.apply();
    }

    public static void savePassword(String password,Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Key.KEY_PASSWORD,password);
        editor.apply();
    }

    public static String getPhoneNumber(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(Key.KEY_PHONE_NUMBER,null);
    }

    public static String getPassword(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(Key.KEY_PASSWORD,null);
    }
}
