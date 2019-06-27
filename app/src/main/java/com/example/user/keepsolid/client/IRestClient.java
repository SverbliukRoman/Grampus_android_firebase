package com.example.user.keepsolid.client;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IRestClient {

    @GET
    Call<JsonObject> get(
            @Url String endpoint,
            @Query("_token") String parametrs
    );

    @GET
    Call<JsonObject> getTwo(
            @Url String endpoint,
            @Query("_token") String parametrs,
            @Query("type") String param
    );

    @GET
    Call<JsonObject> get(
            @Url String endpoint
    );

    @POST
    Call<JsonObject> post(
            @Url String endpoint,
            @Body JsonObject json
    );
}
