package com.app.shoop.ui.order;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<Order> {



    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
    }


}
