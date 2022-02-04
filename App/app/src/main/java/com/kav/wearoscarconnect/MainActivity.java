package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kav.wearoscarconnect.databinding.ActivityMainBinding;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

import org.json.JSONObject;

public class MainActivity extends Activity implements CarListener {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private AccessToken accessToken = new AccessToken();
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
    }

    public void infoActivity(View v){
        //Open up the informaiton activity where
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void tryRequest(View v){
        AuthClient authClient = new AuthClient(this,"9fb503e0-715b-47e8-adfd-ad4b7770f73b","1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");

        authClient.getAccessTokenFromCredentials("kasperrindomjeppesen@gmail.com", "", new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                //Try to get the token values and put them into an AccessToken
                try {
                    String newToken = json.getString("access_token");
                    int expiresAt = Integer.parseInt(json.getString("expires_in"));
                    String refreshToken = json.getString("refresh_token");

                    //Set the new token values.
                    accessToken.value = newToken;
                    accessToken.expiresAt = accessToken.findExpireDate(expiresAt);
                    accessToken.refreshToken = refreshToken;

                    ApiRequest apiRequest = new ApiRequest(ctx,newToken);

                    FordCar fordCar = new FordCar(apiRequest, "WF0PXXGCHPLT16661");
                    SelectedCar.car = fordCar;
                }
                catch (Exception e){

                }

            }
        });

    }

    @Override
    public void onStatusChanged(FordVehicleStatus obj) {

    }
}