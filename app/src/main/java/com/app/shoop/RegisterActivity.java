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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    EditText email;
    EditText password1;
    EditText password2;
    Button registerButton;
    Button cancelButton;
    Button gotoLoginButton;



    enum retCodes{
        SUCCESS,
        BLANK_FIELDS,
        PWD_MISSMATCH
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.registerEmailField);
        password1 = (EditText) findViewById(R.id.registerPassword1);
        password2 = (EditText) findViewById(R.id.registerPassword2);

        registerButton = (Button) findViewById(R.id.registerButton);
        cancelButton = (Button) findViewById(R.id.registerCancelButton);
        gotoLoginButton = (Button) findViewById(R.id.registerLoginHere);

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRegister(view);
            }
        });

        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin(view);
            }
        });


    }

    public void register(View view) {

        retCodes returnCode = retCodes.SUCCESS;

        String userEmail = email.getText().toString();
        String userPwd = password1.getText().toString();
        String confirmPwd = password2.getText().toString();

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd) || TextUtils.isEmpty(confirmPwd)){
            Toast.makeText(this,"All fields must be filled", Toast.LENGTH_SHORT).show();
            returnCode = retCodes.BLANK_FIELDS;
        }

        if(returnCode == retCodes.SUCCESS && !userPwd.equals(confirmPwd) )
        {
            Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show();
            returnCode = retCodes.PWD_MISSMATCH;
        }

        if(returnCode == retCodes.SUCCESS){

            auth.createUserWithEmailAndPassword(userEmail,userPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this,"Account successfully created!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Registration failed!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void cancelRegister(View view)
    {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    public void gotoLogin(View view)
    {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}