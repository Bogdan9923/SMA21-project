package com.app.shoop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shoop.ui.cart.Cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductPage extends AppCompatActivity {

    private String prodName;
    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Bundle bundle = getIntent().getExtras();
        TextView textName = findViewById(R.id.product_page_name);
        TextView textDescription = findViewById(R.id.product_page_description);
        TextView textPrice = findViewById(R.id.product_page_price);
        ImageView imageView = findViewById(R.id.product_page_image);
        Button buyButton = findViewById(R.id.product_page_add_button);
        Button backButton = findViewById(R.id.product_page_back_button);

        prodName = bundle.getString("ProductName");

        if(prodName!= null)
        {
            textName.setText(prodName);
        }

        databaseReference = database.getReference().child("products");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    product = ds.getValue(Product.class);
                    if(product.getName().equals(prodName))
                    {
                        textDescription.setText("Description: "+product.getDescription());
                        textPrice.setText("Price: "+ product.getPrice()+" RON");
                        Picasso.get().load(product.getImage()).placeholder(R.drawable.image_not_found).into(imageView);

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(ProductPage.this,MainActivity.class);
                startActivity(intent);
                */
                finish();
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart(getApplicationContext());
                cart.addToCart(prodName);
                finish();
            }
        });


    }
}