package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

import java.util.Calendar;

public class LoggedInActivity extends Activity implements CarListener {

    ImageButton unlockButton;
    ImageButton lockButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loggedin);

        Bundle extras = getIntent().getExtras();

        AccessToken ac = new AccessToken(extras.getString("accessTokenValue"),(Calendar)extras.getParcelable("accessTokenExpire"),extras.getString("accessTokenRefresh"));

        //Since we only got one brand of car, we will just create it here.
        SelectedCar.car = new FordCar(this, extras.getString("vin"),ac);
        SelectedCar.car.addListener(this);

        Button statusButton = findViewById(R.id.statusButton);
        unlockButton = findViewById(R.id.unlockButton);
        lockButton = findViewById(R.id.lockButton);

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoActivity();
            }
        });

        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unlockCar();
            }
        });

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lockCar();
            }
        });
    }

    public void infoActivity(){
        //Open up the informaiton activity where
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void unlockCar(){
        SelectedCar.car.status();
//        SelectedCar.car.unlock();
    }
    public void lockCar(){
        SelectedCar.car.lock();
    }

    private void vibrateWithMessage(String message){
        DisplayMessageHandler.displayToastMessageVibration(message);
    }


    //region CarListener interface
    @Override
    public void onStatusChanged(FordVehicleStatus obj) {
        //TODO: Could change the image to a car with doors open if car is unlocked, etc.
        DisplayMessageHandler.displayToastMessage("status changed");
    }

    @Override
    public void onDetails() {

    }

    @Override
    public void onStart(boolean started) {
        vibrateWithMessage("Vehicle started");
    }

    @Override
    public void onStop(boolean stopped) {
        vibrateWithMessage("Vehicle stopped");
    }

    @Override
    public void onLock(boolean locked) {
        vibrateWithMessage("Vehicle locked");
    }

    @Override
    public void onUnlock(boolean unlocked) {
        vibrateWithMessage("Vehicle unlocked");
    }
    //endregion
}
