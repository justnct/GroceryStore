package com.example.pj_grocerystore.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String SHARED_REFEFERENCE = "SHARED_REFEFERENCE";
    private Context context;

    public SharedPreference(Context context) {
        this.context = context;
    }

    public void putBoolean(String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putString(String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public void putIntValue(String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntValue(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public boolean contains(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    public void remove(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REFEFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
