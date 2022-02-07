package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.android.volley.VolleyError;
import com.kav.wearoscarconnect.databinding.ActivityMainBinding;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.interfaces.VolleyCallBack;
import com.kav.wearoscarconnect.models.AccessToken;
import com.kav.wearoscarconnect.models.FordCar;

import org.json.JSONObject;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private AccessToken accessToken = new AccessToken();
    private Button loginButton;
    Context ctx = this;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginButton = findViewById(R.id.loginButton);

        mTextView = binding.text;

        ScrollView scrollView = findViewById(R.id.loginScrollView);

        //Set focus for the rotary bezel to work
        scrollView.requestFocus();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void login(View v){
        loginButton.setEnabled(false);

        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        EditText vin = findViewById(R.id.vinText);

        AuthClient authClient = new AuthClient(ctx,"9fb503e0-715b-47e8-adfd-ad4b7770f73b","1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");

        authClient.getAccessTokenFromCredentials(username.getText().toString(), password.getText().toString(), new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject json) {
                //If the login succeeded, then open a new activity where user can control vehicle
                //TODO: Save the token locally
                loggedInActivity(username.getText().toString(), password.getText().toString(), vin.getText().toString());
            }

            @Override
            public void onFail(VolleyError error) {
                //If something went wrong, enable he login button again for the user to try again
                loginButton.setEnabled(true);
                failedToast();
            }
        });
    }

    private void loggedInActivity(String username, String password, String vin){
        //Start the logged in activity where the user can control vehicle
        Intent intent = new Intent(this, LoggedInActivity.class);
        //Pass credential
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("vin",vin);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void failedToast(){
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK));
        Toast.makeText(this,"Could not log in..", Toast.LENGTH_SHORT).show();
    }



}