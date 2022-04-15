package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.kav.wearoscarconnect.interfaces.CarInformation;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

import java.util.Calendar;

public class LoggedInActivity extends Activity implements CarListener {

    ImageButton unlockButton;
    ImageButton lockButton;
    ImageButton engineStartButton;
    ImageView checkmark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMessageHandler.setup(this);

        setContentView(R.layout.activity_loggedin);

        Bundle extras = getIntent().getExtras();

        //Add the car if null
        if(SelectedCar.car == null){
            AccessToken ac = new AccessToken(
                    extras.getString("accessTokenValue"),
                    (Calendar) extras.getParcelable("accessTokenExpire"),
                    extras.getString("accessTokenRefresh"));

            SelectedCar.car = new FordCar(this, extras.getString("vin"), ac);
        }

        //Add this activity as a listener
        SelectedCar.car.addListener(this);

        Button statusButton = findViewById(R.id.statusButton);
        unlockButton = findViewById(R.id.unlockButton);
        lockButton = findViewById(R.id.lockButton);
        engineStartButton = findViewById(R.id.motorStartButton);
        checkmark = findViewById(R.id.checkmarkView);

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoActivity();
            }
        });

        unlockButton.setOnLongClickListener(longClickListener);
        lockButton.setOnLongClickListener(longClickListener);
        engineStartButton.setOnLongClickListener(longClickListener);
        checkmark.setOnDragListener(dragListener);
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, myShadowBuilder, view, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int dragAction = dragEvent.getAction();

            //When the button gets dropped on the checkmark we want to see what button it is
            //And then run the method for the button
            if (dragAction == DragEvent.ACTION_DROP) {
                final View v = (View) dragEvent.getLocalState();

                switch (v.getId()) {
                    case R.id.lockButton:
                        lockCar();
                        break;
                    case R.id.unlockButton:
                        unlockCar();
                        break;
                    case R.id.motorStartButton:
                        startCar();
                        break;
                }

            }
            return true;
        }
    };


    public void infoActivity() {
        //Open up the informaiton activity where
        Intent intent = new Intent(this, InformationComposeActivity.class);
        startActivity(intent);
    }

    public void unlockCar() {
        vibrateWithMessage("Unlocking car");
        SelectedCar.car.unlock();
    }

    public void lockCar() {
        vibrateWithMessage("Locking car");
        SelectedCar.car.lock();
    }
    public void startCar(){
        vibrateWithMessage("Starting car");
        SelectedCar.car.start();}

    private void vibrateWithMessage(String message) {
        DisplayMessageHandler.displayToastMessageVibration(message);
    }


    //region CarListener interface
    @Override
    public void onStatusChanged(CarInformation obj) {
        //TODO: Could change the image to a car with doors open if car is unlocked, etc.
    }

    @Override
    public void onDetails() {

    }

    @Override
    public void onStart(boolean started) {
        if(started){
            vibrateWithMessage("Vehicle started");
        }
        else{
            vibrateWithMessage("Failed to start vehicle");
        }

    }

    @Override
    public void onStop(boolean stopped) {
        if(stopped){
            vibrateWithMessage("Vehicle stopped");
        }
        else{
            vibrateWithMessage("Failed to stop vehicle");
        }
    }

    @Override
    public void onLock(boolean locked) {
        if(locked){
            vibrateWithMessage("Vehicle locked");
        }
        else{
            vibrateWithMessage("Failed to lock vehicle");
        }
    }

    @Override
    public void onUnlock(boolean unlocked) {
        if(unlocked){
            vibrateWithMessage("Vehicle unlocked");
        }
        else{
            vibrateWithMessage("Failed to unlock vehicle");
        }
    }
    //endregion
}
