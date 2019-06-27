package com.example.user.keepsolid.presentation;

import android.content.Context;
import android.util.Log;

import com.example.user.keepsolid.SharedPrefHelper;
import com.example.user.keepsolid.client.Endpoint;
import com.example.user.keepsolid.client.impl.RestClient;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.entity.callbacks.GenericCallback;
import com.example.user.keepsolid.manager.UserManager;
import com.example.user.keepsolid.presentation.view.RaitingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class RaitingEmployeePresenter extends BasePresenter<RaitingView> {

    public void getUsers() {
        UserManager.newInstance().getAllUserInfo(new GenericCallback<ArrayList<UserModel>, String>() {
            @Override
            public void onError(String error) {

            }

            @Override
            public void onResult(ArrayList<UserModel> value) {
                Log.d("MyTag", new Gson().toJson(value.get(0)));
                getView().getAllUser(value);
            }
        });
    }

}
