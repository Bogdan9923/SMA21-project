package com.app.shoop.ui.cart;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.shoop.ListAdapter;
import com.app.shoop.LoginActivity;
import com.app.shoop.Product;
import com.app.shoop.R;
import com.app.shoop.ui.order.OrderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment{

    private CartViewModel cartViewModel;
    private ListView listView;
    CartListAdapter cartListAdapter;
    ArrayList<CartItem> cartItemArrayList = new ArrayList<>();
    Product product;

    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    private Button clearCartButton;
    private Button proceedToCheckoutButton;
    private TextView totalText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        randomCode();
        listView = (ListView) root.findViewById(R.id.shopping_cart_listview);
        clearCartButton = (Button) root.findViewById(R.id.cart_removeall_button);
        proceedToCheckoutButton = (Button) root.findViewById(R.id.cart_proceed_button);
        totalText = (TextView) root.findViewById(R.id.cart_total_cost);

        populateCart(root);


        clearCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });


        proceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToCheckout();
            }
        });



        return root;
    }


    private void clearCart()
    {
        SharedPreferences cartPref;
        cartPref = getContext().getSharedPreferences(getContext().getString(R.string.cart_file_key), Context.MODE_PRIVATE);
        cartPref.edit().clear().apply();

    }

    private void proceedToCheckout()
    {
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("Items",stringifyItems());
            intent.putExtra("TotalPrice",totalText.getText().toString());
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(),"Please log in to continue", Toast.LENGTH_SHORT).show();

        }
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

                    }
                }

                cartListAdapter.notifyDataSetChanged();
                recalculateTotal();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void populateCart(View root)
    {
        TextView empty_cart_text = (TextView) root.findViewById(R.id.empty_cart_text);

        Cart cart = new Cart(getContext());
        Map<String,Integer> cartItems;
        cartItems = cart.getCartItems();
        if(cartItems != null) {

            if(cartItems.size() > 0){
                empty_cart_text.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                proceedToCheckoutButton.setVisibility(View.VISIBLE);
                clearCartButton.setVisibility(View.VISIBLE);
                totalText.setVisibility(View.VISIBLE);

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
                proceedToCheckoutButton.setVisibility(View.GONE);
                clearCartButton.setVisibility(View.GONE);
                totalText.setVisibility(View.GONE);
            }
        }

    }

    public void recalculateTotal()
    {
        int totalPrice = 0;
        for(CartItem item: cartItemArrayList)
        {
            totalPrice += item.getPrice()*item.getCount();
        }
        totalText.setText("Total: "+String.valueOf(totalPrice)+" RON");

    }

    public String stringifyItems()
    {
        String res="";

        for(CartItem item: cartItemArrayList)
        {
            res = res + item.getName() + " x " + item.getCount() + "\n";
        }
        Log.v("TAG",res);
        return res;
    }

}
