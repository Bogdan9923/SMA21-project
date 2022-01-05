package com.app.shoop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Product> {

    public ListAdapter(Context context, ArrayList<Product> productArrayList){
        super(context,R.layout.list_item_product,productArrayList);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product product = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_product,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.list_item_image);
        TextView itemName = convertView.findViewById(R.id.list_item_name);
        TextView itemPrice = convertView.findViewById(R.id.list_item_price);

        itemName.setText(product.getName());
        itemPrice.setText(product.getPrice());
        //to add image from web


        return super.getView(position, convertView, parent);

    }
}
