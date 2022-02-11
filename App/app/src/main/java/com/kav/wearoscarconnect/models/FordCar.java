package com.kav.wearoscarconnect.models;


import android.content.Context;
import android.os.VibrationEffect;
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
    AccessToken accessToken;
    String vin;
    Context ctx;
    boolean setupDone = false;
    ArrayList<CarListener> carListeners = new ArrayList<CarListener>();

    public FordCar(Context ctx, String vin, AccessToken ac) {
        this.ctx = ctx;
        this.vin = vin;
        accessToken = ac;
        apiRequest = new ApiRequest(ctx, ac);
    }

    /**
     * Get response status, even tho the request returns 200 OK
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
    public void status() {

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
    }

    @Override
    public void statusRefresh() {
        apiRequest.request("https://usapi.cv.ford.com/api/vehicles/v2/" + vin + "/status", Request.Method.PUT, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {

            }

            @Override
            public void onFail(VolleyError error) {

            }
        });
    }

    @Override
    public void details() {

    }

    @Override
    public void start() {
        apiRequest.vehicleActionRequest(vin, "engine/start", Request.Method.PUT, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                for (CarListener listener : carListeners) {
                    listener.onStart(true);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                for (CarListener listener : carListeners) {
                    listener.onStart(false);
                }
            }
        });
    }

    @Override
    public void stop() {
        apiRequest.vehicleActionRequest(vin, "engine/start", Request.Method.DELETE, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                for (CarListener listener : carListeners) {
                    listener.onStop(true);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                for (CarListener listener : carListeners) {
                    listener.onStop(false);
                }
            }
        });
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
        apiRequest.vehicleActionRequest(vin, "doors/lock", Request.Method.DELETE, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                for (CarListener listener : carListeners) {
                    listener.onUnlock(true);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                for (CarListener listener : carListeners) {
                    listener.onUnlock(false);
                }
            }
        });

    }

    public void addListener(CarListener listener) {
        carListeners.add(listener);
    }

    public void removeListener(CarListener listener) {
        carListeners.remove(listener);
    }
}
