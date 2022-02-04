
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CcsSettings {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("location")
    @Expose
    private Integer location;
    @SerializedName("vehicleConnectivity")
    @Expose
    private Integer vehicleConnectivity;
    @SerializedName("vehicleData")
    @Expose
    private Integer vehicleData;
    @SerializedName("drivingCharacteristics")
    @Expose
    private Integer drivingCharacteristics;
    @SerializedName("contacts")
    @Expose
    private Integer contacts;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getVehicleConnectivity() {
        return vehicleConnectivity;
    }

    public void setVehicleConnectivity(Integer vehicleConnectivity) {
        this.vehicleConnectivity = vehicleConnectivity;
    }

    public Integer getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(Integer vehicleData) {
        this.vehicleData = vehicleData;
    }

    public Integer getDrivingCharacteristics() {
        return drivingCharacteristics;
    }

    public void setDrivingCharacteristics(Integer drivingCharacteristics) {
        this.drivingCharacteristics = drivingCharacteristics;
    }

    public Integer getContacts() {
        return contacts;
    }

    public void setContacts(Integer contacts) {
        this.contacts = contacts;
    }

}
