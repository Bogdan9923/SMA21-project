package com.app.shoop.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shoop.ListAdapter;
import com.app.shoop.Product;
import com.app.shoop.R;
import com.app.shoop.ui.cart.CartListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOrdersActivity extends AppCompatActivity {

    Button cancelButton;
    ListView listView;
    TextView emptyText;

    private com.google.firebase.database.DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth;

    ArrayList<Order> orderArrayList = new ArrayList<>();
    OrderAdapter adapter;
    Order order;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);

        cancelButton = (Button) findViewById(R.id.orders_cancel_button);
        listView = (ListView) findViewById(R.id.ordersListview);
        emptyText = (TextView) findViewById(R.id.orders_empty_text);

        prepareEmail();

        populateList();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void prepareEmail()
    {
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null)
        {
            email = auth.getCurrentUser().getEmail();
        }
    }

    public void populateList()
    {

        databaseReference = database.getReference().child("orders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    order = ds.getValue(Order.class);
                     if(order.getEmail().equals(email)){
                        orderArrayList.add(order);
                     }
                }

                adapter = new OrderAdapter(getApplicationContext(), R.layout.cart_item, orderArrayList);
                listView.setAdapter(adapter);

                if(orderArrayList.size()==0)
                {
                    listView.setVisibility(View.GONE);
                    emptyText.setVisibility(View.VISIBLE);
                }
                else
                {
                    emptyText.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}