package com.example.user.keepsolid.presentation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.user.keepsolid.App;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.presentation.view.RegistrationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Mexanik on 30.09.2018.
 */

public class RegistrationPresenter extends BasePresenter<RegistrationView> {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    public void signUp(String name, String email, String password){
//        PostRegister postRegister = new PostRegister(name, email, password);
//        RestClient.post(
//                Endpoint.REGISTER, new Gson().toJson(postRegister), new RestClient.RestClientCallback() {
//                    @Override
//                    public void onResult(String success, String dataResponse) {
//                        UserModel loginData = new Gson().fromJson(dataResponse, UserModel.class);
//                        getView().ok(loginData);
//                    }
//                }
//        );
//    }

    public void signUp(final String name, final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getView(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            mAuth.signOut();
                            //Toast.makeText((Activity)getView(), "Success",
                                    //Toast.LENGTH_SHORT).show();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("user");
                            String key = user.getUid();

                            UserModel userModel = new UserModel();
                            userModel.setId(user.getUid());
                            userModel.setName(name);
                            userModel.setEmail(email);

                            myRef.child(key).setValue(userModel);
                            getView().ok(userModel);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(App.getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
