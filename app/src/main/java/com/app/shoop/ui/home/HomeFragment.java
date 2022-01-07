package com.app.shoop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.shoop.ListAdapter;
import com.app.shoop.MainActivity;
import com.app.shoop.Product;
import com.app.shoop.R;
import com.app.shoop.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.main_list_view);

        String[] nameList = {"Telefon" , "Monitor","Tastatura","Telefon" , "Monitor","Tastatura"};
        String[] imageList = {"https://s13emagst.akamaized.net/products/33587/33586602/images/res_5633c2906632179ba63d5ff2c0692442.jpg","https://s13emagst.akamaized.net/products/32921/32920244/images/res_f347beb92a3312023c2857f2dc539540.jpg","https://s13emagst.akamaized.net/products/24208/24207180/images/res_67aefbe6a180afde3eb0d21ff3062468.jpg","https://s13emagst.akamaized.net/products/33587/33586602/images/res_5633c2906632179ba63d5ff2c0692442.jpg","https://s13emagst.akamaized.net/products/32921/32920244/images/res_f347beb92a3312023c2857f2dc539540.jpg","https://s13emagst.akamaized.net/products/24208/24207180/images/res_67aefbe6a180afde3eb0d21ff3062468.jpg"};
        int[] priceList = {1200,100,350,1200,100,350};

        ArrayList<Product> productArrayList = new ArrayList<>();

        for(int i=0;i<nameList.length ; i++)
        {
            Product product = new Product(nameList[i],imageList[i],priceList[i]);
            productArrayList.add(product);
        }

        ListAdapter listAdapter = new ListAdapter(getActivity() ,R.layout.list_item_product,productArrayList);


        listView.setAdapter(listAdapter);

        return root;
    }
}