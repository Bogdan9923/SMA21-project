package com.app.shoop.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.app.shoop.ProductPage;
import com.app.shoop.R;
import com.app.shoop.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Product product;
    private Button searchButton;
    private EditText searchField;
    private ListView listView;


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

         listView = (ListView) root.findViewById(R.id.main_list_view);


        databaseListPopulation();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(getActivity(), ProductPage.class);

                Product prod = (Product) listView.getItemAtPosition(position);
                intent.putExtra("ProductName",prod.getName());

                startActivity(intent);
            }
        });

        return root;
    }

    public void databaseListPopulation()
    {
        ArrayList<Product> productArrayList = new ArrayList<>();
        product = new Product();

        databaseReference = database.getReference().child("products");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    product = ds.getValue(Product.class);
                    //i think here needs to be implemented condition for search button. maybe.
                    productArrayList.add(product);
                }
                ListAdapter listAdapter = new ListAdapter(getActivity() ,R.layout.list_item_product,productArrayList);
                listView.setAdapter(listAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}