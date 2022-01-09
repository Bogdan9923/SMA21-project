package com.app.shoop.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.shoop.R;

import java.util.List;

public class CartListAdapter extends ArrayAdapter<CartItem> {
    private Context mContext;
    private int resourceLayout;

    public CartListAdapter(@NonNull Context context, int resource, @NonNull List<CartItem> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        String name = getItem(position).getName();
        int count = getItem(position).getCount();
        int price = getItem(position).getPrice();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        listItemView = inflater.inflate(resourceLayout,parent,false);

        TextView itemName = listItemView.findViewById(R.id.cart_item_name);
        TextView itemPrice = listItemView.findViewById(R.id.cart_item_price);
        EditText itemCount = listItemView.findViewById(R.id.cart_item_count);
        Button removeButton = listItemView.findViewById(R.id.cart_item_remove_button);

        itemName.setText(name);
        itemCount.setText(String.valueOf(count));
        itemPrice.setText("Price: " + String.valueOf(price * count)+ " RON");

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart(mContext);
                cart.removeItem(name);
            }
        });


        return listItemView;
    }
}
