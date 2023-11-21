package com.prm392team.shopf.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.prm392team.shopf.Entity.Product;

import java.util.ArrayList;
import java.util.Arrays;


public class TinyDB {

    private SharedPreferences preferences;


    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }
    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }
    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }

    public ArrayList<Product> getListObject(String key){
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<Product> playerList =  new ArrayList<Product>();

        for(String jObjString : objStrings){
            Product player  = gson.fromJson(jObjString,  Product.class);
            playerList.add(player);
        }
        return playerList;
    }

    public void putListObject(String key, ArrayList<Product> playerList){
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(Product player: playerList){
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }
}
