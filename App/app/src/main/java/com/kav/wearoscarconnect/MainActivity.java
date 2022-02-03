package com.kav.wearoscarconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kav.wearoscarconnect.databinding.ActivityMainBinding;
import com.kav.wearoscarconnect.models.AccessToken;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private ApiRequest apiRequest;
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.startForegroundService(new Intent(this, AuthClient.class));

        apiRequest = new ApiRequest(this,"");
        accessToken = new AccessToken();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
    }

    public void tryRequest(View v){
        AuthClient authClient = new AuthClient(this,"9fb503e0-715b-47e8-adfd-ad4b7770f73b","1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
        AccessToken accessToken = new AccessToken();

        authClient.getAccessTokenFromCredentials("kasperrindomjeppesen@gmail.com","!", accessToken);

    }
}