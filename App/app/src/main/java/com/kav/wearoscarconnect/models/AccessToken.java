package com.kav.wearoscarconnect.models;


import org.json.JSONObject;

import java.util.Calendar;

public class AccessToken {
    public String value;
    public Calendar expiresAt;
    public String refreshToken;

    public AccessToken(){}
    public AccessToken(String value, int expireSeconds, String refreshToken){
        this.value = value;
        this.refreshToken = refreshToken;
        this.expiresAt = findExpireDate(expireSeconds);
    }
    public AccessToken(String value, Calendar expireSeconds, String refreshToken){
        this.value = value;
        this.refreshToken = refreshToken;
        this.expiresAt = expireSeconds;
    }

    public Calendar findExpireDate(int expireSeconds){
        //Current date
        Calendar expire = Calendar.getInstance();

        //Add seconds from now to get the expire date
        expire.add(Calendar.SECOND, expireSeconds);

        return expire;
    }

    public boolean isExpired(){
        Calendar now = Calendar.getInstance();

        int j = expiresAt.compareTo(now);

        if (now.compareTo(expiresAt) >= 0){
            return true;
        }
        return false;
    }

    public boolean setTokenFromJson(JSONObject json){
        try {
            String newToken = json.getString("access_token");
            int expiresAt = json.getInt("expires_in");
            String refreshToken = json.getString("refresh_token");


            //Set the new token values.
            this.value = newToken;
            this.expiresAt = findExpireDate(expiresAt);
            this.refreshToken = refreshToken;

            return true;
        }
        catch (Exception e){
            return false;
        }
    }



}
