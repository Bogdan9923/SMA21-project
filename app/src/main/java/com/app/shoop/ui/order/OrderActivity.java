package com.app.shoop.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.shoop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null)
        {
            finish();
        }

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        Bundle extras =getIntent().getExtras();

        if(extras != null)
        {
            String items = extras.getString("Items");
            String total = extras.getString("TotalPrice");

            if(items != null)
            {
                textView.setText(items);
            }

            if(total != null)
            {
                textView2.setText(total);
            }
        }


    }
}