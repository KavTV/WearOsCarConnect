
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Battery {

    @SerializedName("batteryHealth")
    @Expose
    private BatteryHealth batteryHealth;
    @SerializedName("batteryStatusActual")
    @Expose
    private BatteryStatusActual batteryStatusActual;

    public BatteryHealth getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(BatteryHealth batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public BatteryStatusActual getBatteryStatusActual() {
        return batteryStatusActual;
    }

    public void setBatteryStatusActual(BatteryStatusActual batteryStatusActual) {
        this.batteryStatusActual = batteryStatusActual;
    }

}
