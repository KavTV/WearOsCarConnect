package com.kav.wearoscarconnect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.models.AccessToken;

import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class AuthClient extends Service {
    public int currentState = 0;

    private Context ctx;
    private String clientId = "";
    private String region = "";

    public AuthClient(Context ctx, String clientId, String region) {
        this.ctx = ctx;
        this.clientId = clientId;
        this.region = region;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return START_NOT_STICKY;
    }


    public void getAccessTokenFromCredentials(String username, String password, VolleyCallBack volleyCallBack) {

        //This request does not work when using jsonobjectrequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sso.ci.ford.com/oidc/endpoint/default/token",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Convert the response to a json
                            JSONObject jsonResponse = new JSONObject(response);
                            String token = jsonResponse.getString("access_token");
                            Log.d("response123", token);

                            //If everything was successful, create a new web request to get the token we can use for the car commands.
                            getCarAccessToken(token, volleyCallBack);

                        } catch (Exception e) {
                            Log.d("response123", "Could not convert to json");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyCallBack.onFail(error);
                        try {
                            String body = new String(error.networkResponse.data, "UTF-8");
                            Log.d("response123", body);
                        } catch (Exception e) {
                            // exception
                        }
                        Log.d("response123", "Could not translate body to json");
                    }
                }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = getReqeustHeaders();
                headers.put("Content-Type", "application/x-www-form-urlencoded");

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", clientId);
                params.put("grant_type", "password");
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };

        NetworkRequests.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    private void getCarAccessToken(String token, VolleyCallBack volleyCallBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", token);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, "https://api.mps.ford.com/api/oauth2/v1/token", new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            currentState = 2;

                            //Return the response
                            volleyCallBack.onSuccess(response);

                        } catch (Exception e) {
                            Log.d("response123", "Could not convert to json");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyCallBack.onFail(error);
                        try {
                            String body = new String(error.networkResponse.data, "UTF-8");
                            Log.d("response123", body);
                        } catch (Exception e) {
                            // exception
                            Log.d("response123", "Could not translate body to json");
                        }
                    }
                }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = getReqeustHeaders();
                headers.put("Content-Type", "application/json");
                headers.put("Application-Id", "1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };
        NetworkRequests.getInstance(ctx).addToRequestQueue(jsonObjectRequest);

    }

    public HashMap<String, String> getReqeustHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "en-US");
        headers.put("User-Agent", "FordPass/5 CFNetwork/1327.0.4 Darwin/21.2.0");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        return headers;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
