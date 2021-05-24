package com.example.pj_grocerystore.shared_preference;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.ProductTest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public static void setListProductTEST(String key, List<ProductTest> mList){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(mList).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().sharedPreference.putString(key, strJsonArray);
    }

    public static ArrayList<ProductTest> getListProductTEST(String key) {
        String strJsonArray = DataLocalManager.getInstance().sharedPreference.getStringValue(key);
        ArrayList<ProductTest> mList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            ProductTest productTest;
            Gson gson = new Gson();
            for (int i = 0 ; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                productTest  = gson.fromJson(jsonObject.toString(), ProductTest.class);
                mList.add(productTest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }

    public static void setListBitmap(String key, List<Bitmap> mList){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(mList).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().sharedPreference.putString(key, strJsonArray);
    }

    public static ArrayList<Bitmap> getListBitmap(String key) {
        String strJsonArray = DataLocalManager.getInstance().sharedPreference.getStringValue(key);
        ArrayList<Bitmap> mList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            Bitmap bitmap;
            Gson gson = new Gson();
            for (int i = 0 ; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                bitmap  = gson.fromJson(jsonObject.toString(), Bitmap.class);
                mList.add(bitmap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }



    }
