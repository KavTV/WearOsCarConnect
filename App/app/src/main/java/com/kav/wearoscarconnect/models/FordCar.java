package com.kav.wearoscarconnect.models;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kav.wearoscarconnect.ApiRequest;
import com.kav.wearoscarconnect.AuthClient;
import com.kav.wearoscarconnect.DisplayMessageHandler;
import com.kav.wearoscarconnect.SavedSettings;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.Car;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.fordmodels.Example;


import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Representation of a ford car with its implementation of the car interface.
 */
public class FordCar implements Car {

    ApiRequest apiRequest;
    AuthClient authClient;
    AccessToken accessToken;
    String vin;
    Context ctx;
    boolean setupDone = false;
    ArrayList<CarListener> carListeners = new ArrayList<CarListener>();

    public FordCar(Context ctx, String vin, AccessToken ac) {
        this.ctx = ctx;
        this.vin = vin;
        accessToken = ac;
//        this.authClient = new AuthClient(ctx, SavedSettings.clientId, SavedSettings.region);
        apiRequest = new ApiRequest(ctx, ac);
    }

    private void init(String username, String password) {
        //Client id is always the same
//        this.authClient = new AuthClient(ctx, SavedSettings.clientId, SavedSettings.region);
//        authClient.getAccessTokenFromCredentials(username, password, new VolleyCallBack() {
//            @Override
//            public void onSuccess(JSONObject json) {
//                try {
//                    String newToken = json.getString("access_token");
//                    int expiresAt = Integer.parseInt(json.getString("expires_in"));
//                    String refreshToken = json.getString("refresh_token");
//
//                    //Set the new token values.
//                    accessToken.value = newToken;
//                    accessToken.expiresAt = accessToken.findExpireDate(expiresAt);
//                    accessToken.refreshToken = refreshToken;
//
//                    apiRequest = new ApiRequest(ctx, newToken);
//                    setupDone = true;
//
//                } catch (Exception e) {
//                    setupDone = false;
//                }
//
//            }
//
//            @Override
//            public void onFail(VolleyError error) {
//
//            }
//        });
    }

    private void regainAccess(VolleyCallBack callBack) {
        //If the user gets the access denied message, we just need to get a new access token.
        authClient.getAccessTokenFromRefreshToken(accessToken.refreshToken, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {

                boolean acSuccess = accessToken.setTokenFromJson(json);

                //If the token was successfully set from the json, then tell it went well.
                if (acSuccess) {
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
     * Get response status, oven tho the reqeuest returns 200 OK
     * The response could still be 401 or 400 if user is not allowed to get info about that vehicle
     *
     * @param json the json request
     * @return true if response code is bad, 400,401
     */
    private boolean checkBadResponse(JSONObject json) {
        try {
            //Get response status, oven tho the reqeuest returns 200 OK
            //The response could still be 401 or 400 if user is not allowed to get info about that vehicle
            int responseStatus = Integer.parseInt(json.getString("status"));

            if (responseStatus == 400 || responseStatus == 401) {
                DisplayMessageHandler.displayToastMessage("No access to vehicle (VIN)");
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public JSONObject status() {

        apiRequest.request("https://usapi.cv.ford.com/api/vehicles/v4/" + vin + "/status?lrdt=01-01-1970%2000:00:00", Request.Method.GET, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {

                //If we did not get a bad response then proceed getting status.
                if (!checkBadResponse(json)) {
                    Gson gson = new Gson();
                    FordVehicleStatus status = gson.fromJson(json.toString(), FordVehicleStatus.class);

                    Log.d("response123", "SUCCEEDED STATUS");

                    for (CarListener listener : carListeners) {
                        listener.onStatusChanged(status);
                    }
                }

            }

            @Override
            public void onFail(VolleyError error) {
                Log.d("response123", error.toString());

            }
        });
        return null;
    }

    @Override
    public JSONObject details() {
        return null;
    }

    @Override
    public void start() {
        return ;
    }

    @Override
    public void stop() {
        return ;
    }

    @Override
    public void lock() {
        apiRequest.vehicleActionRequest(vin, "doors/lock", Request.Method.PUT, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                for (CarListener listener : carListeners) {
                    listener.onLock(true);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                for (CarListener listener : carListeners) {
                    listener.onLock(false);
                }
            }
        });
    }

    @Override
    public void unlock() {
        return;
    }

    public void addListener(CarListener listener) {
        carListeners.add(listener);
    }

    public void removeListener(CarListener listener) {
        carListeners.remove(listener);
    }
}
