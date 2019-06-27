package com.example.user.keepsolid.client.impl;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.example.user.keepsolid.client.Endpoint;
import com.example.user.keepsolid.client.IRestClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient extends AsyncTask<Void, Void, RestClient.AsyncTaskResponseParams> {

    public static final String ACTION_POST = "action.post";
    public static final String ACTION_GET_PARAMETR = "action.get.parametr";
    public static final String ACTION_GET_PARAMETR_TWO = "action.get.parametr_two";
    public static final String ACTION_GET = "action.get";
    private static final String TAG = "RestClient";
    String actions;
    Endpoint endpoint;
    String data;
    RestClientCallback callback;
    String parametr;
    String param2;

    //login url 10.11.1.134:8090/login?code=randomCode

    public static void getWithParametr(Endpoint endpoint, RestClientCallback callback, String parametr) {
        RestClient.start(RestClient.ACTION_GET_PARAMETR, endpoint, null, callback, parametr);
    }

    public static void getWithTwoParametr(Endpoint endpoint, RestClientCallback callback, String parametr1, String param2) {
        RestClient.start(RestClient.ACTION_GET_PARAMETR_TWO, endpoint, null, callback, parametr1, param2);
    }

    public static void get(Endpoint endpoint, RestClientCallback callback) {
        RestClient.start(RestClient.ACTION_GET, endpoint, null, callback);
    }

    public static void post(Endpoint endpoint, String data,  RestClientCallback callback) {
        RestClient.start(RestClient.ACTION_POST, endpoint, data, callback);
    }

    private static void start(String actions, Endpoint endpoint, String data, RestClientCallback callback, String parametr, String param2) {
        new RestClient(actions, endpoint, data, callback, parametr, param2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static void start(String actions, Endpoint endpoint, String data, RestClientCallback callback, String parametr) {
        new RestClient(actions, endpoint, data, callback, parametr).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static void start(String actions, Endpoint endpoint, String data, RestClientCallback callback) {
        new RestClient(actions, endpoint, data, callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public RestClient(String actions, Endpoint endpoint, String data, RestClientCallback callback, String parametr, String param2) {
        this.actions = actions;
        this.endpoint = endpoint;
        this.data = data;
        this.callback = callback;
        this.parametr = parametr;
        this.param2 = param2;
    }

    public RestClient(String actions, Endpoint endpoint, String data, RestClientCallback callback, String parametr) {
        this.actions = actions;
        this.endpoint = endpoint;
        this.data = data;
        this.callback = callback;
        this.parametr = parametr;
    }

    public RestClient(String actions, Endpoint endpoint, String data, RestClientCallback callback) {
        this.actions = actions;
        this.endpoint = endpoint;
        this.data = data;
        this.callback = callback;
    }

    @Override
    protected AsyncTaskResponseParams doInBackground(Void... voids) {
        Response<JsonObject> responce = null;
        JsonObject jsonData = TextUtils.isEmpty(data) ? null :
                new JsonParser().parse(data).getAsJsonObject();
        Call<JsonObject> callMethod = createCallMethod(actions, Endpoint.CENTRAL_NODE.getPath(), endpoint.getPath(), jsonData,  parametr, param2);
        String resData = null;
        Log.d(TAG, "Request type: " + actions);
        Log.d(TAG, "Request url: " + Endpoint.CENTRAL_NODE.getPath() + endpoint.getPath());
        try {
            responce = callMethod.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Responce data", ""+responce);
        if (responce != null && responce.body() != null) {
            resData = responce.body().toString();
        }
        return new AsyncTaskResponseParams(resData, callback);
    }


    @Override
    protected void onPostExecute(AsyncTaskResponseParams asyncTaskResponseParams) {
        super.onPostExecute(asyncTaskResponseParams);

        sentResponse(asyncTaskResponseParams.getResData(), asyncTaskResponseParams.getCallback());
    }

    public static void sentResponse(String response, RestClientCallback callback) {
        String data = null;
        String status = null;
        JSONObject jsonObj;
        try {
            if (!TextUtils.isEmpty(response)) {
                jsonObj = new JSONObject(response);
                status = jsonObj.getString("status");

                data = jsonObj.getString("data");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.onResult(status, data);
    }

    private static Call<JsonObject> createCallMethod(String action, String baseUrl, String endpoint, JsonObject data, String paremetr, String param2) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRestClient client = retrofit.create(IRestClient.class);
        Call<JsonObject> requestMethod = null;
        switch (action) {
            case ACTION_GET_PARAMETR:
                requestMethod = client.get(endpoint, paremetr);
                Log.d(TAG, "" + requestMethod.request().method());
                Log.d(TAG, "" + requestMethod.request().url());
                Log.d(TAG, "" + requestMethod.request().body());
                Log.d(TAG, "" + requestMethod.request().toString());
                break;
            case ACTION_GET_PARAMETR_TWO:
                requestMethod = client.getTwo(endpoint, paremetr, param2);
                Log.d(TAG, "" + requestMethod.request().method());
                Log.d(TAG, "" + requestMethod.request().url());
                Log.d(TAG, "" + requestMethod.request().body());
                Log.d(TAG, "" + requestMethod.request().toString());
                break;
            case ACTION_GET:
                requestMethod = client.get(endpoint);
                Log.d(TAG, "" + requestMethod.request().method());
                Log.d(TAG, "" + requestMethod.request().url());
                Log.d(TAG, "" + requestMethod.request().body());
                Log.d(TAG, "" + requestMethod.request().toString());
                break;
            case ACTION_POST:
                requestMethod = client.post(endpoint, data);
                Log.d(TAG, "" + requestMethod.request().method());
                Log.d(TAG, "" + requestMethod.request().url());
                Log.d(TAG, "" + requestMethod.request().body());
                Log.d(TAG, "" + requestMethod.request().toString());
                break;
        }
        return requestMethod;
    }

    public static class AsyncTaskResponseParams {
        String resData;
        RestClientCallback callback;

        public AsyncTaskResponseParams(String resData, RestClientCallback callback) {
            this.resData = resData;
            this.callback = callback;
        }

        public String getResData() {
            return resData;
        }

        public RestClientCallback getCallback() {
            return callback;
        }
    }

    public interface RestClientCallback {
        void onResult(String success, String dataResponse);
    }
}
