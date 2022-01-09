package com.app.shoop.ui.cart;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.shoop.ListAdapter;
import com.app.shoop.Product;
import com.app.shoop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {
    private CartViewModel cartViewModel;
    private ListView listView;
    private CartItem item;
    CartListAdapter cartListAdapter;
    ArrayList<CartItem> cartItemArrayList = new ArrayList<>();
    Product product;
    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        randomCode();
        listView = (ListView) root.findViewById(R.id.shopping_cart_listview);

        populateCart(root);

        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        populateCart(root);
                    }
                };


        return root;
    }



    private void randomCode()
    {
        cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        cartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }

    private void getDatabasePrice(CartItem c)
    {

        String name = c.getName();
        product = new Product();
        databaseReference = database.getReference().child("products");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    product = ds.getValue(Product.class);
                    if(product.getName().equals(name))
                    {

                        cartItemArrayList.remove(c);
                        cartItemArrayList.add(new CartItem(product.getName(),product.getPrice(),c.getCount()));

                       // Log.v("price getter:", "index=" + globalPrice);
                    }
                }
                cartListAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void populateCart(View root)
    {
        TextView empty_cart_text = (TextView) root.findViewById(R.id.empty_cart_text);

        item = new CartItem();

        Cart cart = new Cart(getContext());
        Map<String,Integer> cartItems;
        cartItems = cart.getCartItems();
        if(cartItems != null) {

            if(cartItems.size() > 0){
                empty_cart_text.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                    String name = entry.getKey();
                    int count = entry.getValue();
                    CartItem c = new CartItem(name, 0, count);
                    getDatabasePrice(c);
                    cartItemArrayList.add(c);

                }

                 cartListAdapter = new CartListAdapter(getContext(), R.layout.cart_item, cartItemArrayList);
                listView.setAdapter(cartListAdapter);
            }
            else{
                empty_cart_text.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        }

    }

}
