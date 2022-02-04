
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DoorStatus {

    @SerializedName("rightRearDoor")
    @Expose
    private RightRearDoor rightRearDoor;
    @SerializedName("leftRearDoor")
    @Expose
    private LeftRearDoor leftRearDoor;
    @SerializedName("driverDoor")
    @Expose
    private DriverDoor driverDoor;
    @SerializedName("passengerDoor")
    @Expose
    private PassengerDoor passengerDoor;
    @SerializedName("hoodDoor")
    @Expose
    private HoodDoor hoodDoor;
    @SerializedName("tailgateDoor")
    @Expose
    private TailgateDoor tailgateDoor;
    @SerializedName("innerTailgateDoor")
    @Expose
    private InnerTailgateDoor innerTailgateDoor;

    public RightRearDoor getRightRearDoor() {
        return rightRearDoor;
    }

    public void setRightRearDoor(RightRearDoor rightRearDoor) {
        this.rightRearDoor = rightRearDoor;
    }

    public LeftRearDoor getLeftRearDoor() {
        return leftRearDoor;
    }

    public void setLeftRearDoor(LeftRearDoor leftRearDoor) {
        this.leftRearDoor = leftRearDoor;
    }

    public DriverDoor getDriverDoor() {
        return driverDoor;
    }

    public void setDriverDoor(DriverDoor driverDoor) {
        this.driverDoor = driverDoor;
    }

    public PassengerDoor getPassengerDoor() {
        return passengerDoor;
    }

    public void setPassengerDoor(PassengerDoor passengerDoor) {
        this.passengerDoor = passengerDoor;
    }

    public HoodDoor getHoodDoor() {
        return hoodDoor;
    }

    public void setHoodDoor(HoodDoor hoodDoor) {
        this.hoodDoor = hoodDoor;
    }

    public TailgateDoor getTailgateDoor() {
        return tailgateDoor;
    }

    public void setTailgateDoor(TailgateDoor tailgateDoor) {
        this.tailgateDoor = tailgateDoor;
    }

    public InnerTailgateDoor getInnerTailgateDoor() {
        return innerTailgateDoor;
    }

    public void setInnerTailgateDoor(InnerTailgateDoor innerTailgateDoor) {
        this.innerTailgateDoor = innerTailgateDoor;
    }

}
