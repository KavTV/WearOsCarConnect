
package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindowPosition {

    @SerializedName("driverWindowPosition")
    @Expose
    private DriverWindowPosition driverWindowPosition;
    @SerializedName("passWindowPosition")
    @Expose
    private PassWindowPosition passWindowPosition;
    @SerializedName("rearDriverWindowPos")
    @Expose
    private RearDriverWindowPos rearDriverWindowPos;
    @SerializedName("rearPassWindowPos")
    @Expose
    private RearPassWindowPos rearPassWindowPos;

    public DriverWindowPosition getDriverWindowPosition() {
        return driverWindowPosition;
    }

    public void setDriverWindowPosition(DriverWindowPosition driverWindowPosition) {
        this.driverWindowPosition = driverWindowPosition;
    }

    public PassWindowPosition getPassWindowPosition() {
        return passWindowPosition;
    }

    public void setPassWindowPosition(PassWindowPosition passWindowPosition) {
        this.passWindowPosition = passWindowPosition;
    }

    public RearDriverWindowPos getRearDriverWindowPos() {
        return rearDriverWindowPos;
    }

    public void setRearDriverWindowPos(RearDriverWindowPos rearDriverWindowPos) {
        this.rearDriverWindowPos = rearDriverWindowPos;
    }

    public RearPassWindowPos getRearPassWindowPos() {
        return rearPassWindowPos;
    }

    public void setRearPassWindowPos(RearPassWindowPos rearPassWindowPos) {
        this.rearPassWindowPos = rearPassWindowPos;
    }

}
