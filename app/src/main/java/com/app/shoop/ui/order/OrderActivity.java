package com.app.shoop.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shoop.R;
import com.app.shoop.ui.cart.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    TextView itemListText;
    TextView totalPriceText;
    EditText name;
    EditText address;
    EditText phone;
    Button sendOrderButton;
    Button cancelOrderButton;

    private String items;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null)
        {
            finish();
        }

        itemListText = (TextView) findViewById(R.id.order_item_list);
        totalPriceText = (TextView) findViewById(R.id.order_total);
        name = (EditText) findViewById(R.id.order_name_edit);
        address = (EditText) findViewById(R.id.order_address_edit);
        phone = (EditText) findViewById(R.id.order_phone_edit);
        sendOrderButton = (Button) findViewById(R.id.place_order_button);
        cancelOrderButton = (Button) findViewById(R.id.order_cancel_button);


        Bundle extras =getIntent().getExtras();

        if(extras != null)
        {
            items = extras.getString("Items");
            String total = extras.getString("TotalPrice");

            if(items != null)
            {
                itemListText.setText("Your order:\n\n"+items);
            }

            if(total != null)
            {
                totalPriceText.setText(total);
                price = Integer.parseInt(total.substring(7,total.length()-4));
            }
        }

        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrder();
            }
        });


    }

    private void sendOrder()
    {

        if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(address.getText().toString())||TextUtils.isEmpty(phone.getText().toString())) {

            Toast.makeText(this,"All fields must be filled",Toast.LENGTH_SHORT).show();

        }
        else {
            Order order = new Order(name.getText().toString(),address.getText().toString(),phone.getText().toString(),items,price,auth.getCurrentUser().getEmail());
            databaseReference = database.getReference().child("orders");
            databaseReference.push().setValue(order);

            Cart cart = new Cart(getApplicationContext());
            cart.clearCart();

            Toast.makeText(this,"Order placed successfully! Thank you!",Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}