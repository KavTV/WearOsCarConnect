package com.kav.wearoscarconnect;

import android.content.Context;
import android.content.SharedPreferences;

import com.kav.wearoscarconnect.models.AccessToken;

import java.util.Calendar;

/**
 * Used to store and retrieve data from shared preferences
 */
public class SharedPreferencesHandler {
    static Context ctx;
    static SharedPreferences sharedPrefPrivate;
    static SharedPreferences.Editor sharedEditor;

    public static void setSharedPreferences(Context ctx){
        sharedPrefPrivate = ctx.getSharedPreferences("com.kav.wearoscarconnect.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        sharedEditor = sharedPrefPrivate.edit();
    }

    /**
     * Store AccessToken for ford vehicle
     * @param accessToken
     */
    public static void storeFordAccessToken(AccessToken accessToken){
        sharedEditor.putString("SelectedCarBrand", "Ford");
        sharedEditor.putString("FordAccessToken", accessToken.value);
        sharedEditor.putLong("FordAccessExpire", accessToken.expiresAt.getTimeInMillis());
        sharedEditor.putString("FordAccessRefresh", accessToken.refreshToken);

        //Save changes
        sharedEditor.apply();
    }

    public static void storeVIN(String vin){
        sharedEditor.putString("VehicleVIN", vin);

        sharedEditor.apply();
    }

    public static String getCarBrand(){
        return sharedPrefPrivate.getString("SelectedCarBrand","");
    }

    /**
     * Get the previous used access token used for ford vehicles
     * @return a new AccessToken with stored values
     */
    public static AccessToken getFordAccessToken(){
        //s1 is for returning a default value if key could not be found
        String value = sharedPrefPrivate.getString("FordAccessToken","");
        long expire = sharedPrefPrivate.getLong("FordAccessExpire",0);
        String refresh = sharedPrefPrivate.getString("FordAccessRefresh","");

        //Return null if not all values are available
        if(value.isEmpty() || expire == 0 || refresh.isEmpty()){
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(expire);

        return new AccessToken(value,calendar, refresh);
    }

    public static String getVIN(){
        return sharedPrefPrivate.getString("VehicleVIN","");
    }

}
