package com.example.user.keepsolid.presentation.view;

import android.net.Uri;

import com.example.user.keepsolid.entity.UserModel;

/**
 * Created by Mexanik on 29.09.2018.
 */

public interface ProfileView {
    void getInfo(UserModel userModel);
    void getPhotoUri(Uri uri);
}
