package com.example.pj_grocerystore.shared_preference;

import android.content.Context;

import com.example.pj_grocerystore.model.Account;
import com.google.gson.Gson;

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

    public static void setAccount(String key, Account account){
        Gson gson = new Gson();
        String strGsonAccount = gson.toJson(account);
        DataLocalManager.getInstance().sharedPreference.putString(key, strGsonAccount);
    }

    public static Account getAccount(String key) {
        String strJsonAccount = DataLocalManager.getInstance().sharedPreference.getStringValue(key);
        Gson gson = new Gson();
        Account account = gson.fromJson(strJsonAccount, Account.class);
        return account;
    }

    }
