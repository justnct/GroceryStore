package com.example.pj_grocerystore.shared_preference;

import android.content.Context;

public class DataLocalManager {
    private static  DataLocalManager instance;
    private  SharedPreference sharedPreference;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.sharedPreference = new SharedPreference(context);
    }
    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setStrng(String key, String value){
        DataLocalManager.getInstance().sharedPreference.putString(key, value);
    }

    public static String getString(String key){
        return DataLocalManager.getInstance().sharedPreference.getStringValue(key);
    }

    public static void setBoolean(String key, boolean value){
        DataLocalManager.getInstance().sharedPreference.putBoolean(key, value);
    }

    public static void getBoolean(String key){
        DataLocalManager.getInstance().sharedPreference.getBooleanValue(key);
    }


    public static boolean checkExitst(String key){
        return DataLocalManager.getInstance().sharedPreference.contains(key);
    }

    public static void removeKey(String key){
        DataLocalManager.getInstance().sharedPreference.remove(key);
    }

}
