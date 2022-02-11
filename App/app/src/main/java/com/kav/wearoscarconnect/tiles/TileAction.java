package com.kav.wearoscarconnect.tiles;

import android.content.Context;
import android.util.Log;

import com.kav.wearoscarconnect.DisplayMessageHandler;
import com.kav.wearoscarconnect.SelectedCar;
import com.kav.wearoscarconnect.SharedPreferencesHandler;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

/**
 * Actions that the tile(widget) can use
 */
public class TileAction {

    public TileAction(Context ctx) {

        //If the car is already set, then no need to set another one.
        if (SelectedCar.car != null) {
            return;
        }

        AccessToken retrievedToken = SharedPreferencesHandler.getFordAccessToken();
        if (retrievedToken != null) {
            String vin = SharedPreferencesHandler.getVIN();

            if (!vin.isEmpty()) {

                String carBrand = SharedPreferencesHandler.getCarBrand();
                //If we got different car brands in the future.
                switch (carBrand) {
                    case "Ford":
                        SelectedCar.car = new FordCar(ctx, vin, retrievedToken);
                        break;
                }
            }
        }
    }

    public void startCarEngine() {
        if (SelectedCar.car != null) {
            SelectedCar.car.start();
            Log.d("response123", "Tile Requested vehicle start");
        }
    }

    public void unlockCar() {
        if (SelectedCar.car != null) {
            SelectedCar.car.unlock();
            Log.d("response123", "Tile Requested unlock car");
        }
    }

    public void lockCar() {
        if (SelectedCar.car != null) {
            SelectedCar.car.lock();
            Log.d("response123", "Tile Requested lock car");
        }
    }

}
