package com.example.user.keepsolid.presentation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.user.keepsolid.presentation.view.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    public void signIn(String name, String password){
//        final PostLogin login = new PostLogin(name, password);
//        RestClient.post(
//                Endpoint.LOGIN, new Gson().toJson(login), new RestClient.RestClientCallback() {
//                    @Override
//                    public void onResult(String success, String dataResponse) {
//                        try {
//                            if(success.equals("success")) {
//                                GetLogin loginData = new Gson().fromJson(dataResponse, GetLogin.class);
//                                getView().getToken(loginData.getToken());
//                            } else {
//
//                            }
//                        } catch (NullPointerException e){
//                            getView().error();
//                        }
//
//                    }
//                }
//        );
//    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getView(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");


                            getView().loginSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText((Activity) getView(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
