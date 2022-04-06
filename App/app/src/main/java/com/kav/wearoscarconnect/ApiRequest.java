package com.kav.wearoscarconnect;

import android.content.Context;
import android.os.Handler;
import android.os.VibrationEffect;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.models.AccessToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {

    private Context ctx;
    private AccessToken accessToken;
    private AuthClient authClient;

    public ApiRequest(Context context, AccessToken accessToken) {
        ctx = context;
        this.accessToken = accessToken;
        this.authClient = new AuthClient(ctx, SavedSettings.clientId, SavedSettings.region);
    }

    /**
     * Make a request with volley
     * @param url the url the request should be sent to
     * @param method the method the request should use
     * @param callBack the callback for when the request is done.
     */
    public void request(String url, int method, final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (method, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //If we did not get a bad response then proceed response.
                        Log.d("response123", response.toString());
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //If we were not authorized, then try to get a new access token,
                        //and try to call status again
                        if (error.networkResponse.statusCode == 401) {
                            regainAccess(new VolleyCallBack() {
                                @Override
                                public void onSuccess(JSONObject json) {
                                    //Try to call same method again
                                    Log.d("response123", "CALLING AGAIN");
                                    request(url, method, callBack);
                                }

                                @Override
                                public void onFail(VolleyError error) {
                                    callBack.onFail(error);
                                }
                            });
                        }
                        else{
                            Log.d("response123", error.toString());
                            callBack.onFail(error);
                        }

                    }
                }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };
        NetworkRequests.getInstance(ctx).addToRequestQueue(jsonObjectRequest);
    }

    /**
     * Regain access by getting a new auth token from the refresh token
     * @param callBack gets called when the request is done
     */
    private void regainAccess(VolleyCallBack callBack) {
        //If the user gets the access denied message, we just need to get a new access token.
        authClient.getAccessTokenFromRefreshToken(accessToken.refreshToken, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {

                boolean acSuccess = accessToken.setTokenFromJson(json);

                //If the token was successfully set from the json, then tell it went well.
                if (acSuccess) {
                    //Save the new token, and call success.
                    SharedPreferencesHandler.storeFordAccessToken(accessToken);
                    callBack.onSuccess(json);
                } else {
                    //If token could not be set, do the same as in onFail
                    onFail(new VolleyError());
                }
            }

            @Override
            public void onFail(VolleyError error) {
                DisplayMessageHandler.displayToastMessage("Could not get new token");
                callBack.onFail(error);
            }
        });
    }

    /**
     * Used to send vehicle action requests, like lock doors, or start engine
     * @param vin VIN number of vehicle
     * @param action The action to do, like "engine/start"
     * @param method HTTP method
     * @param callBack Gets called when the request is done
     */
    public void vehicleActionRequest(String vin, String action, int method, VolleyCallBack callBack) {
        String url = "https://usapi.cv.ford.com/api/vehicles/v2/" + vin + "/" + action;

        request(url, method, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    String commandId = json.getString("commandId");
                    if (!commandId.isEmpty()) {
                        checkVehicleStatus(vin, action, commandId, callBack, 1);
                    }
                    else{
                        onFail(new VolleyError());
                    }
                } catch (Exception e) {
                    DisplayMessageHandler.displayToastMessage("Something went wrong with action");
                }
            }

            @Override
            public void onFail(VolleyError error) {
                callBack.onFail(error);
            }
        });
    }

    /**
     * When an action is done, check if the action is executed successfully
     * @param vin VIN number of vehicle
     * @param action The action to do, like "engine/start"
     * @param commandId The commandId that is received from the vehicle action request
     * @param callBack Gets called when the request is done
     * @param attempts The amount of attempts tried to check for status
     */
    public void checkVehicleStatus(String vin, String action, String commandId, VolleyCallBack callBack, int attempts) {
        //If vehicle status has not been returned within the attempts, then the request is timed out
        if (attempts >= 20) {
            callBack.onFail(new VolleyError());
            return;
        }
        String url = "https://usapi.cv.ford.com/api/vehicles/v2/" + vin + "/" + action + "/" + commandId;

        request(url, Request.Method.GET, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    //Get the status, if 200, the action is done, if 502 action is still ongoing
                    int status = json.getInt("status");
                    if (status == 200) {
                        callBack.onSuccess(json);
                        return;
                    } else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checkVehicleStatus(vin, action, commandId, callBack, attempts + 1);
                            }
                        }, 3000);
                    }
                } catch (Exception e) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            checkVehicleStatus(vin, action, commandId, callBack, attempts + 1);
                        }
                    }, 3000);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                callBack.onFail(error);
            }
        });
    }

    private Map getRequestHeaders() {
        HashMap headers = new HashMap();
        headers.put("auth-token", accessToken.value);
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "en-US");
        headers.put("User-Agent", "FordPass/5 CFNetwork/1327.0.4 Darwin/21.2.0");
        headers.put("Content-Type", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Application-Id", "1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
        return headers;
    }
}
