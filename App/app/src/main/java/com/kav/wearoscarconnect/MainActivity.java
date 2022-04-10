package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

//import androidx.wear.tiles.manager.TileUiClient;

import com.android.volley.VolleyError;
import com.kav.wearoscarconnect.databinding.ActivityMainBinding;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

import org.json.JSONObject;

public class MainActivity extends Activity {

//    private TileUiClient tileUiClient;

    private TextView mTextView;
    private ActivityMainBinding binding;
    private Button loginButton;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMessageHandler.setup(this);

        //CHECK IF USER HAS ALREADY LOGGED IN BEFORE RENDERING THIS ACTIVITY
        //Set context
        SharedPreferencesHandler.setSharedPreferences(this);

        //If null is returned, then no tokens have been stored.
        AccessToken retrievedToken = SharedPreferencesHandler.getFordAccessToken();
        if (retrievedToken != null) {
            String vin = SharedPreferencesHandler.getVIN();

            if (!vin.isEmpty()) {
                String carBrand = SharedPreferencesHandler.getCarBrand();
                //If different car brands is added in the future.
                switch (carBrand) {
                    case "Ford":
                        SelectedCar.car = new FordCar(this, vin, retrievedToken);
                        break;
                }

                loggedInActivity(retrievedToken, vin, carBrand);
            }
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        FrameLayout rootLayout = (FrameLayout) findViewById(R.id.frameContainer1);
//        tileUiClient = new TileUiClient(this, new ComponentName(this, ActionTileService.class), rootLayout);
//        tileUiClient.connect();

        loginButton = findViewById(R.id.loginButton);

        mTextView = binding.text;

        ScrollView scrollView = findViewById(R.id.loginScrollView);

//        Set focus for the rotary bezel to work
        scrollView.requestFocus();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
//        tileUiClient.close();
    }

    public void login(View v) {
        //While logging in, the user should not be able to request again
        loginButton.setEnabled(false);

        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        EditText vin = findViewById(R.id.vinText);

        AuthClient authClient = new AuthClient(ctx, SavedSettings.clientId, SavedSettings.region);

        authClient.getAccessTokenFromCredentials(username.getText().toString(), password.getText().toString(), new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                //If the login succeeded, then open a new activity where user can control vehicle

                try {
                    AccessToken accessToken = new AccessToken();
                    accessToken.setTokenFromJson(json);

                    //Store token and vehicle vin
                    SharedPreferencesHandler.storeFordAccessToken(accessToken);
                    SharedPreferencesHandler.storeVIN(vin.getText().toString());

                    //INIT selectedCar
                    String carBrand = SharedPreferencesHandler.getCarBrand();
                    switch (carBrand) {
                        case "Ford":
                            SelectedCar.car = new FordCar(ctx, vin.getText().toString(), accessToken);
                            break;
                    }

                    loggedInActivity(accessToken, vin.getText().toString(), "Ford");
                } catch (Exception e) {
                    //If something went wrong, enable he login button again for the user to try again
                    loginButton.setEnabled(true);
                    failedToast();
                }

            }

            @Override
            public void onFail(VolleyError error) {
                //If something went wrong, enable he login button again for the user to try again
                loginButton.setEnabled(true);
                failedToast();
            }
        });
    }

    /**
     * Go to the logged in activity and remove this activity
     * @param accessToken access token for the car
     * @param vin vin number for the car to be controlled
     * @param carBrand the brand of the car
     */
    private void loggedInActivity(AccessToken accessToken, String vin, String carBrand) {
        //Start the logged in activity where the user can control vehicle
        Intent intent = new Intent(this, LoggedInActivity.class);
        //Pass credential
        //Could serialize with GSON, but read it is faster putting in the individual values
        //since it is a small object and would be faster.
        intent.putExtra("accessTokenValue", accessToken.value);
        intent.putExtra("accessTokenExpire", accessToken.expiresAt);
        intent.putExtra("accessTokenRefresh", accessToken.refreshToken);
        intent.putExtra("vin", vin);
        intent.putExtra("SelectedCarBrand", carBrand);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void failedToast() {
        DisplayMessageHandler.displayToastMessageVibration("Could not log in..", VibrationEffect.EFFECT_DOUBLE_CLICK);
    }


}