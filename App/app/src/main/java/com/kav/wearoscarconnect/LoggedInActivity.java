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
import com.kav.wearoscarconnect.models.FordCar;

public class LoggedInActivity extends Activity implements CarListener {

    ImageButton unlockButton;
    ImageButton lockButton;

    Vibrator vibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loggedin);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Bundle extras = getIntent().getExtras();

        //Since we only got one brand of car, we will just create it here.
        SelectedCar.car = new FordCar(this, extras.getString("vin"), extras.getString("username"), extras.getString("password"));
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
        vibrateWithMessage("something");
//        SelectedCar.car.unlock();
    }
    public void lockCar(){
//        SelectedCar.car.lock();
    }

    private void vibrateWithMessage(String message){
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    //region CarListener interface
    @Override
    public void onStatusChanged(FordVehicleStatus obj) {
        //TODO: Could change the image to a car with doors open if car is unlocked, etc.

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
