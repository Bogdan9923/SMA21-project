package com.app.shoop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Bundle bundle = getIntent().getExtras();
        TextView textView = findViewById(R.id.textView123);

        if(bundle.getString("ProductName")!= null)
        {
            textView.setText(bundle.getString("ProductName"));
        }

    }
}