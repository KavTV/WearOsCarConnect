package com.kav.wearoscarconnect.interfaces;

import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;

import org.json.JSONObject;

public interface CarListener {
    /**
     * Callback when status is received
     * @param carInfo The information properties of the car
     */
    public void onStatusChanged(CarInformation carInfo);

    /**
     * Callback when details received
     * Details of the vehicle like color
     */
    public void onDetails();

    /**
     *Callback when vehicle started or failed to
     * @param started True if the vehicle successfully started
     */
    public void onStart(boolean started);

    /**
     * Callback when vehicled motor stopped or failed to
     * @param stopped True of vehicle successfully stopped
     */
    public void onStop(boolean stopped);

    /**
     * Callback when vehicle got locked or failed to
     * @param locked true if vehicle successfully locked
     */
    public void onLock(boolean locked);

    /**
     * Callback when vehicle got unlocked or failed to
     * @param unlocked true if vehicle successfully unlocked
     */
    public void onUnlock(boolean unlocked);

}
