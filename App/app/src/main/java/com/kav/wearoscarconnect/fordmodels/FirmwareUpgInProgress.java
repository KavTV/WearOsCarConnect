
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FirmwareUpgInProgress {

    @SerializedName("value")
    @Expose
    private Boolean value;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
