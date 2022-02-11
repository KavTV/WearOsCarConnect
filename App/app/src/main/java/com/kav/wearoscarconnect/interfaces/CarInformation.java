package com.kav.wearoscarconnect.interfaces;

public interface CarInformation {
    /**
     *
     * @return returns the lock status of car
     */
    public String getLockStatus();

    /**
     *
     * @return returns the distance in the fuel is left
     */
    public double getDistanceLeft();

    /**
     *
     * @return returns how much distance in adblue is left
     */
    public String getAdblueLeft();

    /**
     *
     * @return returns the last time vehicle data was refreshed
     */
    public String getLastRefresh();
}
