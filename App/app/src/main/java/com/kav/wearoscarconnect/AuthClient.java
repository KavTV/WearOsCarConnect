package com.kav.wearoscarconnect;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthClient {
    private Context ctx;
    private String clientId = "";
    private String region = "";

    public AuthClient(Context ctx, String clientId, String region){
        this.ctx = ctx;
        this.clientId = clientId;
        this.region = region;
    }


    public void getAccessTokenFromCredentials(String username, String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sso.ci.ford.com/oidc/endpoint/default/token",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response123",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            String body = new String(error.networkResponse.data,"UTF-8");
                            Log.d("response123",body);
                        } catch (Exception e) {
                            // exception
                        }
                        Log.d("response123","woop");
                    }
                }){
            @Override
            public Map getHeaders() throws AuthFailureError{
                HashMap headers = new HashMap();
                headers.put("Accept", "*/*");
                headers.put("Accept-Language", "en-US");
                headers.put("User-Agent", "FordPass/5 CFNetwork/1327.0.4 Darwin/21.2.0");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Accept-Encoding", "gzip, deflate, br");
                return headers;
            }
            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded";
            }
            @Override
            protected Map<String,String> getParams(){
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

}
