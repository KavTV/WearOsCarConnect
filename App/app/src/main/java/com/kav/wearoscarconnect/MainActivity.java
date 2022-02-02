package com.kav.wearoscarconnect;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kav.wearoscarconnect.databinding.ActivityMainBinding;

import org.json.JSONObject;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiRequest = new ApiRequest(this,"");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
    }

    public void tryRequest(View v){
        AuthClient ac = new AuthClient(this,"9fb503e0-715b-47e8-adfd-ad4b7770f73b","1E8C7794-FF5F-49BC-9596-A1E0C86C5B19");
        ac.getAccessTokenFromCredentials("kasperrindomjeppesen@gmail.com","");
    }
}