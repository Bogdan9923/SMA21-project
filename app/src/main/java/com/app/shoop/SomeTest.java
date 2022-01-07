package com.app.shoop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SomeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_test);


        ImageView imageView = findViewById(R.id.imageViewTest);

       // new DownloadImageFromInternet(findViewById(R.id.imageViewTest)).execute();
        Picasso.get().load("https://images.app.goo.gl/S5nHzhf3M8YsqE3y9").placeholder(R.drawable.image_not_found).into(imageView);

    }
}