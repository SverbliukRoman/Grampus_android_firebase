package com.example.user.keepsolid.presentation;

import com.google.gson.Gson;

/**
 * Created by Mexanik on 29.09.2018.
 */

public abstract class BasePresenter<V> {
    protected final String TAG = this.getClass().getSimpleName();
    protected Gson converter = new Gson();
    private V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public V getView() {
        return view;
    }

    public boolean isViewAttached() {
        return view != null;
    }
}
