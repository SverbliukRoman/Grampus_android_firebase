package com.example.user.keepsolid.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.SharedPrefHelper;
import com.example.user.keepsolid.databinding.ActivityRegistrationBinding;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.presentation.RegistrationPresenter;
import com.example.user.keepsolid.presentation.view.RegistrationView;

public class RegistrationActivity extends Activity implements RegistrationView {
    ActivityRegistrationBinding binding;
    RegistrationPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        presenter = new RegistrationPresenter();
        presenter.attachView(this);

        SharedPrefHelper.setFirstStart(getBaseContext(), true);

        binding.singUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        if (!TextUtils.isEmpty(binding.etLoginRegister.getText().toString())) {
            if(!TextUtils.isEmpty(binding.etPassRegister.getText().toString())){
                presenter.signUp(binding.etLoginRegister.getText().toString(), binding.etEmailRegister.getText().toString(), binding.etPassRegister.getText().toString());
            }else{
                Toast.makeText(getBaseContext(), "Please pass your password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please pass your login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ok(UserModel user) {
        Toast.makeText(getBaseContext(), "Register success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
}
