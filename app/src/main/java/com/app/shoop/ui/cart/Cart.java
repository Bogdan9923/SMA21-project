package com.app.shoop.ui.cart;

import android.content.Context;
import android.content.SharedPreferences;


import com.app.shoop.R;

import java.util.HashMap;
import java.util.Map;

public class Cart extends CartFragment{

    private SharedPreferences cartPref;
    private SharedPreferences.Editor editor;
    private Context context = getActivity();

    public void addToCart(String name)
    {
        cartPref = context.getSharedPreferences(getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
    int current = cartPref.getInt(name,0);
        editor.putInt(name,current + 1);
        editor.apply();

    }

    public void addToCart(String name, int quant)
    {
        cartPref = context.getSharedPreferences(getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        editor.putInt(name,quant);
        editor.apply();

    }

    public void removeItem(String name)
    {
        cartPref = context.getSharedPreferences(getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        editor.remove(name);
        editor.apply();
    }

    public void modifyQuant(String name, int newQuant)
    {
        cartPref = context.getSharedPreferences(getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        editor.putInt(name,newQuant);
        editor.apply();
    }

    public Map<String,Integer> getCartItems()
    {
        Map<String,Integer> map = new HashMap<>();
        Map<String, ?> allEntries = cartPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            map.put(entry.getKey(),Integer.parseInt(entry.getValue().toString()));
        }
        return map;
    }


}
