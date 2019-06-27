package com.example.user.keepsolid.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.user.keepsolid.SharedPrefHelper;
import com.example.user.keepsolid.client.Endpoint;
import com.example.user.keepsolid.client.impl.RestClient;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.entity.callbacks.GenericCallback;
import com.example.user.keepsolid.entity.callbacks.SimpleGenericCallback;
import com.example.user.keepsolid.manager.UserManager;
import com.example.user.keepsolid.presentation.view.ProfileView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.net.URI;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class ProfilePresenter extends BasePresenter<ProfileView> {

//    public void getInfo(Context context){
//        RestClient.getWithParametr(Endpoint.GET_USER_INFO, new RestClient.RestClientCallback() {
//            @Override
//            public void onResult(String success, String dataResponse) {
//                getView().getInfo(new Gson().fromJson(dataResponse, UserModel.class));
//            }
//        }, SharedPrefHelper.getUserAuthCode(context));
//    }

    public void getInfo() {
        UserManager.newInstance().getUserInfo(new GenericCallback<UserModel, String>() {
            @Override
            public void onError(String error) {

            }

            @Override
            public void onResult(UserModel value) {
                getView().getInfo(value);
            }
        });

        UserManager.newInstance().getUserPhoto(FirebaseAuth.getInstance().getUid(), new SimpleGenericCallback<Uri>() {
            @Override
            public void onResult(Uri value) {
                getView().getPhotoUri(value);
            }
        });
    }

    public void uploadPhoto(Bitmap bitmap){
        UserManager.newInstance().uploadPhoto(bitmap, new SimpleGenericCallback<String>() {
            @Override
            public void onResult(String value) {
                UserManager.newInstance().setPhotoUrl(value);
            }
        });
    }
}
