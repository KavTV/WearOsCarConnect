
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Odometer {

    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
