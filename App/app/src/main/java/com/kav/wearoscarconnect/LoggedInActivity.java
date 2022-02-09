package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Region;
import android.media.Image;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    ImageView checkmark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loggedin);

        Bundle extras = getIntent().getExtras();

        AccessToken ac = new AccessToken(extras.getString("accessTokenValue"), (Calendar) extras.getParcelable("accessTokenExpire"), extras.getString("accessTokenRefresh"));

        //Since we only got one brand of car, we will just create it here.
        SelectedCar.car = new FordCar(this, extras.getString("vin"), ac);
        SelectedCar.car.addListener(this);

        Button statusButton = findViewById(R.id.statusButton);
        unlockButton = findViewById(R.id.unlockButton);
        lockButton = findViewById(R.id.lockButton);
        checkmark = findViewById(R.id.checkmarkView);

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoActivity();
            }
        });

        unlockButton.setOnLongClickListener(longClickListener);
        lockButton.setOnLongClickListener(longClickListener);
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
                }

            }
            return true;
        }
    };


    public void infoActivity() {
        //Open up the informaiton activity where
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void unlockCar() {
//        SelectedCar.car.status();
        SelectedCar.car.unlock();
    }

    public void lockCar() {
        SelectedCar.car.lock();
    }

    private void vibrateWithMessage(String message) {
        DisplayMessageHandler.displayToastMessageVibration(message);
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
