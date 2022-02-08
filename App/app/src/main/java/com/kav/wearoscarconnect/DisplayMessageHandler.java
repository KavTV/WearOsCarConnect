package com.kav.wearoscarconnect;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class DisplayMessageHandler {

    static Context ctx;
    static Vibrator vibrator;

    public static void setup(Context context){
        ctx = context;
        vibrator = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static void displayToastMessage(String message){
        Toast.makeText(ctx, message,Toast.LENGTH_SHORT).show();
    }
    public static void displayToastMessageVibration(String message){
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
        Toast.makeText(ctx, message,Toast.LENGTH_SHORT).show();
    }
    public static void displayToastMessageVibration(String message, int effect){
        vibrator.vibrate(VibrationEffect.createPredefined(effect));
        Toast.makeText(ctx, message,Toast.LENGTH_SHORT).show();
    }
}
