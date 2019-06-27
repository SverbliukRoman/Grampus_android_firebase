package com.example.user.keepsolid.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.databinding.ActivityLoginBinding;
import com.example.user.keepsolid.presentation.LoginPresenter;
import com.example.user.keepsolid.presentation.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginView {
    ActivityLoginBinding binding;
    LoginPresenter presenter;
    FirebaseUser auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth!=null) {
            loginSuccess();
        }
        presenter = new LoginPresenter();
        presenter.attachView(this);
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        binding.singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn() {
        if (!TextUtils.isEmpty(binding.etLogin.getText().toString())) {
            if (!TextUtils.isEmpty(binding.etPass.getText().toString())) {
                presenter.signIn(binding.etLogin.getText().toString(), binding.etPass.getText().toString());
            } else {
                Toast.makeText(getBaseContext(), "Please pass your password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please pass your login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginSuccess() {
        //SharedPrefHelper.setUserAuthCode(getBaseContext(), token);
        Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainActivity);
    }

    @Override
    public void error() {
        Toast.makeText(getBaseContext(), "Login faild", Toast.LENGTH_LONG).show();
    }
}
