package com.example.user.keepsolid.client;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class Endpoint {
    public static final Endpoint CENTRAL_NODE = new Endpoint("http://c1d965fa.ngrok.io/");
    public static final Endpoint LOGIN = new Endpoint("api/user/login");
    public static final Endpoint REGISTER = new Endpoint("api/user/register");
    public static final Endpoint GET_USER = new Endpoint("api/user/all");
    public static final Endpoint GET_USER_INFO = new Endpoint("api/user/info");
    public static final Endpoint DO_LIKE = new Endpoint("api/like/do");
    public static final Endpoint GET_CATEGORY = new Endpoint("api/category/all");


    final private String path;
    private Endpoint(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
