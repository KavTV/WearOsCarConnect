package com.kav.wearoscarconnect.interfaces;

import org.json.JSONObject;

public interface Car {
    public void status();
    public void statusRefresh();
    public void details();
    public void start();
    public void stop();
    public void lock();
    public void unlock();
    public void addListener(CarListener carListener);
    public void removeListener(CarListener carListener);
}
