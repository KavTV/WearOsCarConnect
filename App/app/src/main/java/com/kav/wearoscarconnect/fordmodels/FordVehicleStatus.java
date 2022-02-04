package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FordVehicleStatus {

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
}
