package com.kav.wearoscarconnect.models;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

public class ChipInformation {
    public String Message;
    public int Drawable;

    public ChipInformation(String message, int drawable){
        Message = message;
        Drawable = drawable;
    }
}
