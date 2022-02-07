package com.kav.wearoscarconnect.models;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kav.wearoscarconnect.ApiRequest;
import com.kav.wearoscarconnect.AuthClient;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.Car;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.fordmodels.Example;


import org.json.JSONObject;

import java.util.ArrayList;

public class FordCar implements Car {

    ApiRequest apiRequest;
    AuthClient authClient;
    AccessToken accessToken = new AccessToken();
    String vin;
    Context ctx;
    boolean setupDone = false;
    ArrayList<CarListener> carListeners = new ArrayList<CarListener>();

    public FordCar(Context ctx, String vin, String username, String password){
        this.ctx = ctx;
        this.vin = vin;
        init(username, password);
    }

    private void init(String username, String password){
        //Client id is always the same
        this.authClient = new AuthClient(ctx,"9fb503e0-715b-47e8-adfd-ad4b7770f73b","1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
        authClient.getAccessTokenFromCredentials(username, password, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    String newToken = json.getString("access_token");
                    int expiresAt = Integer.parseInt(json.getString("expires_in"));
                    String refreshToken = json.getString("refresh_token");

                    //Set the new token values.
                    accessToken.value = newToken;
                    accessToken.expiresAt = accessToken.findExpireDate(expiresAt);
                    accessToken.refreshToken = refreshToken;

                    apiRequest = new ApiRequest(ctx,newToken);
                    setupDone = true;
                    Log.d("response123", "DONE");
                }
                catch (Exception e){
                    setupDone = false;
                }

            }

            @Override
            public void onFail(VolleyError error) {

            }
        });
    }

    @Override
    public JSONObject status() {

        apiRequest.request("https://usapi.cv.ford.com/api/vehicles/v4/"+vin+"/status?lrdt=01-01-1970%2000:00:00", Request.Method.GET, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                Gson gson = new Gson();
                FordVehicleStatus status = gson.fromJson(json.toString(), FordVehicleStatus.class);


                for (CarListener listener : carListeners) {
                    listener.onStatusChanged(status);
                }

            }

            @Override
            public void onFail(VolleyError error) {

            }
        });
        return null;
    }

    @Override
    public JSONObject details() {
        return null;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }

    public void addListener(CarListener listener){
        carListeners.add(listener);
    }
    public void removeListener(CarListener listener){
        carListeners.remove(listener);
    }
}
