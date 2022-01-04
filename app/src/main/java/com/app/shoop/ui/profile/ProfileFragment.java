package com.app.shoop.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.shoop.LoginActivity;
import com.app.shoop.MainActivity;
import com.app.shoop.R;
import com.app.shoop.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseAuth auth;

    Button loginButton;
    Button signinButton;
    Button logoutButton;
    TextView welcomeText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();


        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView textView = root.findViewById(R.id.text_profile);

        loginButton = (Button) root.findViewById(R.id.profileLoginButton);
        signinButton = (Button) root.findViewById(R.id.profileRegisterButton);
        logoutButton = (Button) root.findViewById(R.id.profileLogoutButton);
        welcomeText = (TextView) root.findViewById(R.id.profileDescText);

        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

    if(auth.getCurrentUser() != null)
    {
        loginButton.setVisibility(View.GONE);
        signinButton.setVisibility(View.GONE);
        logoutButton.setVisibility(View.VISIBLE);
        welcomeText.setText(getString(R.string.profileWelcomingText)+" "+auth.getCurrentUser().getEmail());

    }
    else
    {
        loginButton.setVisibility(View.VISIBLE);
        signinButton.setVisibility(View.VISIBLE);
        logoutButton.setVisibility(View.GONE);
        welcomeText.setText(getString(R.string.profileAskingForLoginText));
    }


    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    });

    signinButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), RegisterActivity.class));
        }
    });

    logoutButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            auth.signOut();
            Toast.makeText(getActivity(),"Goodbye", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    });


        return root;
    }
}
