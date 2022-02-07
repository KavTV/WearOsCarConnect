package com.kav.wearoscarconnect.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallBack {
    void onSuccess(JSONObject json);
    void onFail(VolleyError error);
}
