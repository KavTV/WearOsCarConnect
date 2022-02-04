package com.kav.wearoscarconnect.models;


import com.android.volley.Request;
import com.google.gson.Gson;
import com.kav.wearoscarconnect.ApiRequest;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.Car;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.fordmodels.Example;


import org.json.JSONObject;

import java.util.ArrayList;

public class FordCar implements Car {

    ApiRequest apiRequest;
    String vin;
    ArrayList<CarListener> carListeners = new ArrayList<CarListener>();

    public FordCar(ApiRequest apiRequest, String vin){
        this.apiRequest = apiRequest;
        this.vin = vin;
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
