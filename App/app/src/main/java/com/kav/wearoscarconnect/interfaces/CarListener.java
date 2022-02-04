package com.kav.wearoscarconnect.interfaces;

import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;

public interface CarListener {
    public void onStatusChanged(FordVehicleStatus obj);
}
