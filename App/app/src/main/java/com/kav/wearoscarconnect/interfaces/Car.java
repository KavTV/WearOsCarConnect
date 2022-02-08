package com.kav.wearoscarconnect.interfaces;

import org.json.JSONObject;

public interface Car {
    public JSONObject status();
    public JSONObject details();
    public void start();
    public void stop();
    public void lock();
    public void unlock();
    public void addListener(CarListener carListener);
    public void removeListener(CarListener carListener);
}
