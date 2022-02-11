package com.kav.wearoscarconnect.interfaces;

import org.json.JSONObject;

public interface Car {
    /**
     * Gets the status of the vehicle, info etc.
     */
    public void status();

    /**
     * Sends a request for vehicle to refresh its status
     */
    public void statusRefresh();

    /**
     * Gets the details of the vehicle, like color.
     */
    public void details();

    /**
     * Starts the vehicle motor
     */
    public void start();

    /**
     * Stops the vehicle motor
     */
    public void stop();

    /**
     * locks the vehicle
     */
    public void lock();

    /**
     * unlocks the vehicle
     */
    public void unlock();

    /**
     * Adds a car to the listener for callbacks when actions are done
     * @param carListener carlistener interface that receives callback when actions done
     */
    public void addListener(CarListener carListener);

    /**
     * Removes the listener from getting callbacks
     * @param carListener the carlistener that gets removed
     */
    public void removeListener(CarListener carListener);
}
