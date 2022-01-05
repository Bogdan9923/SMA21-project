package com.app.shoop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    ActivityMainBinding binding;

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

        String[] nameList = {"Telefon" , "Tableta","Tastatura"};
        String[] imageList = {"https://images.app.goo.gl/3kg5RW8fExt7inBg7","https://images.app.goo.gl/S5nHzhf3M8YsqE3y9","https://images.app.goo.gl/UnxzBM19KxRf5dXv6"};
        int[] priceList = {1200,100,350};

        ArrayList<Product> productArrayList = new ArrayList<>();

        for(int i=0;i<nameList.length ; i++)
        {
            Product product = new Product(nameList[i],imageList[i],priceList[i],"no desc");
            productArrayList.add(product);

        }

        ListAdapter listAdapter = new ListAdapter(getContext(), productArrayList);
       // homeViewModel.setAdapter(listAdapter);

        return root;
    }
}