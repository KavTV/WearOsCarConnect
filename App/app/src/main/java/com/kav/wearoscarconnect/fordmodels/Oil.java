
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Oil {

    @SerializedName("oilLife")
    @Expose
    private String oilLife;
    @SerializedName("oilLifeActual")
    @Expose
    private Integer oilLifeActual;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getOilLife() {
        return oilLife;
    }

    public void setOilLife(String oilLife) {
        this.oilLife = oilLife;
    }

    public Integer getOilLifeActual() {
        return oilLifeActual;
    }

    public void setOilLifeActual(Integer oilLifeActual) {
        this.oilLifeActual = oilLifeActual;
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
