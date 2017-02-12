package com.ahmaddev.xgram.Help;


import android.content.Context;
import android.content.SharedPreferences;

public class Helpers {

    public static String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    public static void SpIntertInt (Context c, String key, int value) {
        SharedPreferences sp = c.getSharedPreferences("XGram" ,Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void SpIntertString (Context c, String key, String value) {
        SharedPreferences sp = c.getSharedPreferences("XGram" ,Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void SpIntertBoolean (Context c, String key, Boolean value) {
        SharedPreferences sp = c.getSharedPreferences("XGram" ,Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


}
