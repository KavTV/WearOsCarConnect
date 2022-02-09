package com.kav.wearoscarconnect;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.kav.wearoscarconnect.adapters.InformationListAdapter;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.fordmodels.Vehiclestatus;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.models.StatusListItem;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InformationActivity extends Activity implements CarListener {

    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<StatusListItem> statusListItem = new ArrayList<>();

    InformationListAdapter adapter = new InformationListAdapter(this, statusListItem, new InformationListAdapter.AdapterCallback() {
        @Override
        public void onItemClicked(final Integer menuPosition) {

        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);

        WearableRecyclerView wearableRecyclerView = findViewById(R.id.informationRecyclerView);


        wearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        wearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this));

        statusListItem.add(new StatusListItem(R.drawable.ic_refresh, "Loading.."));

        wearableRecyclerView.setAdapter(adapter);

        wearableRecyclerView.requestFocus();

        //Add this activity to listener to get responses
        SelectedCar.car.addListener(this);

        //We want to get the newest information
        SelectedCar.car.statusRefresh();
        SelectedCar.car.status();

        swipeRefreshLayout = findViewById(R.id.informationSwipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onInformationRefresh();
            }
        });
    }

    @Override
    public void onStop() {
        //Remove this from the car listener
        SelectedCar.car.removeListener(this);
        super.onStop();
    }

    void onInformationRefresh(){
        SelectedCar.car.statusRefresh();
        SelectedCar.car.status();
    }

    @Override
    public void onStatusChanged(FordVehicleStatus obj) {
        Vehiclestatus vehicleStat = obj.getVehiclestatus();

        statusListItem.clear();

        try {
            //GET THE TIME BETWEEN NOW AND LAST REFRESH
            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
            LocalDateTime ldt1 = LocalDateTime.now();
            LocalDateTime ldt2 = LocalDateTime.parse(vehicleStat.getLastRefresh(),df);
            Duration d = Duration.between(ldt2,ldt1);
            statusListItem.add(new StatusListItem(R.drawable.ic_history, String.format("%d hours ago", d.toHours())));
        }
        catch (Exception e){

        }

        //Show specific icon depending on door lock status
        if(vehicleStat.getLockStatus().getValue().equals("LOCKED")){
            statusListItem.add(new StatusListItem(R.drawable.ic_closedlock_white, vehicleStat.getLockStatus().getValue()));
        }
        else{
            statusListItem.add(new StatusListItem(R.drawable.ic_openlock_white, vehicleStat.getLockStatus().getValue()));
        }
        statusListItem.add(new StatusListItem(R.drawable.ic_gas, vehicleStat.getFuel().getDistanceToEmpty().toString()));
        statusListItem.add(new StatusListItem(R.drawable.ic_wind, vehicleStat.getDieselSystemStatus().getUreaRange().getValue()));

        //Tell that the list needs to be updated
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetails() {

    }

    @Override
    public void onStart(boolean started) {

    }

    @Override
    public void onStop(boolean stopped) {

    }

    @Override
    public void onLock(boolean locked) {

    }

    @Override
    public void onUnlock(boolean unlocked) {

    }
}
