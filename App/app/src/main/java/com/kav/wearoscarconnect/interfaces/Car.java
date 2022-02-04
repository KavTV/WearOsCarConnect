package com.kav.wearoscarconnect.interfaces;

import org.json.JSONObject;

public interface Car {
    public JSONObject status();
    public JSONObject details();
    public boolean start();
    public boolean stop();
    public boolean lock();
    public boolean unlock();
    public void addListener(CarListener carListener);
    public void removeListener(CarListener carListener);
}
