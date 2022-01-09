package com.app.shoop.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.shoop.R;

import org.w3c.dom.Text;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<Order> {

    private Context mContext;
    private int resourceLayout;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        String status = getItem(position).getStatus();
        int price = getItem(position).getPrice();
        String items = getItem(position).getItems();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        listItemView = inflater.inflate(resourceLayout,parent,false);

        TextView statusText = (TextView) listItemView.findViewById(R.id.order_status);
        TextView priceText = (TextView) listItemView.findViewById(R.id.orders_price);
        TextView itemsText = (TextView) listItemView.findViewById(R.id.orders_item_list);

        statusText.setText("Status: "+status);
        priceText.setText("Price: "+price+" RON");
        itemsText.setText("Items:\n"+items);

        return  listItemView;
    }
}
