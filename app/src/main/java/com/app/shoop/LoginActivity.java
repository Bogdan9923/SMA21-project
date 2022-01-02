package com.app.shoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.loginEmailField);
        password = (EditText) findViewById(R.id.loginPassword);


    }

    public void login(View view) {

        String userEmail = email.getText().toString();
        String userPwd = password.getText().toString();

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd) ){
            Toast.makeText(this,"All fields must be filled", Toast.LENGTH_SHORT).show();
            return;
        }


        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void cancelLogin(View view)
    {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void gotoSignin(View view)
    {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }


}