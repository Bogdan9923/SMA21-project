package com.app.shoop.ui.cart;

import android.content.Context;
import android.content.SharedPreferences;


import com.app.shoop.MainActivity;
import com.app.shoop.R;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private SharedPreferences cartPref;
    private SharedPreferences.Editor editor;
    private Context context ;

    public Cart(Context context)
    {
        this.context = context;

    }

    public void addToCart(String name)
    {
        cartPref = context.getSharedPreferences(context.getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
    int current = cartPref.getInt(name,0);
        editor.putInt(name,current + 1);
        editor.apply();

    }

    public void addToCart(String name, int quant)
    {
        cartPref = context.getSharedPreferences(context.getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        editor.putInt(name,quant);
        editor.apply();

    }

    public void removeItem(String name)
    {
        cartPref = context.getSharedPreferences(context.getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        editor.remove(name);
        editor.apply();
    }

    public void modifyQuant(String name, int newQuant)
    {
        cartPref = context.getSharedPreferences(context.getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        editor = cartPref.edit();
        if(newQuant < 1) {
            removeItem(name);
        }
        else {
            editor.putInt(name, newQuant);
        }
        editor.apply();
    }

    public Map<String,Integer> getCartItems()
    {
        cartPref = context.getSharedPreferences(context.getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        if(cartPref == null)
        {
            return null;
        }
        Map<String,Integer> map = new HashMap<>();
        Map<String, ?> allEntries = cartPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            map.put(entry.getKey(),Integer.parseInt(entry.getValue().toString()));
        }
        return map;
    }


}
