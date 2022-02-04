
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("fuelLevel")
    @Expose
    private Double fuelLevel;
    @SerializedName("distanceToEmpty")
    @Expose
    private Double distanceToEmpty;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(Double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public Double getDistanceToEmpty() {
        return distanceToEmpty;
    }

    public void setDistanceToEmpty(Double distanceToEmpty) {
        this.distanceToEmpty = distanceToEmpty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
