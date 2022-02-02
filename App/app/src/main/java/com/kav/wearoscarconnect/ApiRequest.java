package com.kav.wearoscarconnect;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private Map headers;
    private Context ctx;

    public ApiRequest(Context context, String accessToken){
        ctx = context;
        headers = getHeaders(accessToken);
    }

    public void request(String url, int method){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (method, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response123",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response123",error.toString());
                    }
                }) {
            @Override
            public Map getHeaders() throws AuthFailureError{
                return headers;
            }
        };
        NetworkRequests.getInstance(ctx).addToRequestQueue(jsonObjectRequest);
    }

    private Map getHeaders(String accessToken)
    {
        HashMap headers = new HashMap();
        headers.put("auth-token", accessToken);
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "en-US");
        headers.put("User-Agent", "FordPass/5 CFNetwork/1327.0.4 Darwin/21.2.0");
        headers.put("Content-Type", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Application-Id", "1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
        return headers;
    }
}
