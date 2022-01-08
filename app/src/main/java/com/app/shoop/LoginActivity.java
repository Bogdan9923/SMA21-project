package com.app.shoop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    EditText email;
    EditText password;

    Button loginButton;
    Button cancelButton;
    Button gotoRegisterButton;

    enum retCodes{
        SUCCESS,
        BLANK_FIELDS,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.loginEmailField);
        password = (EditText) findViewById(R.id.loginPassword);

        loginButton = (Button) findViewById(R.id.loginButton);
        cancelButton = (Button) findViewById(R.id.loginCancelButton);
        gotoRegisterButton = (Button) findViewById(R.id.loginSignupHere);

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelLogin(view);
            }
        });

        gotoRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignin(view);
            }
        });

    }

    public void login(View view) {

        retCodes returnCode = retCodes.SUCCESS;

        String userEmail = email.getText().toString();
        String userPwd = password.getText().toString();

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd) ){
            Toast.makeText(this,"All fields must be filled", Toast.LENGTH_SHORT).show();
            returnCode = retCodes.BLANK_FIELDS;
        }

        if(returnCode == retCodes.SUCCESS) {

            auth.signInWithEmailAndPassword(userEmail,userPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,"Login successful", Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

    public void cancelLogin(View view)
    {
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    public void gotoSignin(View view)
    {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }


}