package com.app.shoop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Product> {
    private static final String TAG = "AdapterPage";


    private Context mContext;
    private int resourceLayout;

    public ListAdapter(Context context,int resource, ArrayList<Product> productArrayList){
        super(context,resource,productArrayList);
        this.mContext = context;
        this.resourceLayout = resource;
        Log.v(TAG, "called constructor");

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.v(TAG, "called getView");
        View listItemView = convertView;

        String name = getItem(position).getName();
        String image = getItem(position).getImage();
        int price = getItem(position).getPrice();


        Product product = new Product(name,image,price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        Log.v(TAG, "set inflater");
        listItemView = inflater.inflate(resourceLayout,parent,false);

        ImageView imageView = listItemView.findViewById(R.id.list_item_image);
        TextView itemName = listItemView.findViewById(R.id.list_item_name);
        TextView itemPrice = listItemView.findViewById(R.id.list_item_price);

        Log.v(TAG, "set new values");


        Picasso.get().load(product.getImage()).placeholder(R.drawable.image_not_found).into(imageView);
        itemName.setText(name);
        itemPrice.setText(String.valueOf(price) + " RON");

        Log.v(TAG, "before return");
        return listItemView;

    }

    /*
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View v = convertView;


        if(v == null)
        {
            v = LayoutInflater.from(mContext).inflate(resourceLayout,parent, true);
        }

        Product product = getItem(position);

        if(product != null) {
            ImageView imageView = convertView.findViewById(R.id.list_item_image);
            TextView itemName = convertView.findViewById(R.id.list_item_name);
            TextView itemPrice = convertView.findViewById(R.id.list_item_price);

            itemName.setText(product.getName());
            itemPrice.setText(product.getPrice());
            //to add image from web
            //   new DownloadImageFromInternet(imageView).execute(product.getImage());
            imageView.setImageAlpha(R.drawable.aaaa);
        }

        return v;

    }*/
}
