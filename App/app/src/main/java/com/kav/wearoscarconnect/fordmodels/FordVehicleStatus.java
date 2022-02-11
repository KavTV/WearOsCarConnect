package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kav.wearoscarconnect.interfaces.CarInformation;

public class FordVehicleStatus implements CarInformation {

    @SerializedName("vehiclestatus")
    @Expose
    private Vehiclestatus vehiclestatus;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Vehiclestatus getVehiclestatus() {
        return vehiclestatus;
    }

    public void setVehiclestatus(Vehiclestatus vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return returns the lock status of car
     */
    @Override
    public String getLockStatus() {
        return getVehiclestatus().getLockStatus().getValue();
    }

    /**
     * @return returns the distance in the fuel is left
     */
    @Override
    public double getDistanceLeft() {
        return getVehiclestatus().getFuel().getDistanceToEmpty();
    }

    /**
     * @return returns how much distance in adblue is left
     */
    @Override
    public String getAdblueLeft() {
        return getVehiclestatus().getDieselSystemStatus().getUreaRange().getValue();
    }

    /**
     * @return returns the last time vehicle data was refreshed
     */
    @Override
    public String getLastRefresh() {
        return getVehiclestatus().getLastRefresh();
    }
}
