package com.kav.wearoscarconnect.interfaces;

import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;

import org.json.JSONObject;

public interface CarListener {
    public void onStatusChanged(FordVehicleStatus obj);
    public void onDetails();
    public void onStart(boolean started);
    public void onStop(boolean stopped);
    public void onLock(boolean locked);
    public void onUnlock(boolean unlocked);

}
