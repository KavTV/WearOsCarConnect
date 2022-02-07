package com.kav.wearoscarconnect;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.kav.wearoscarconnect.adapters.MainMenuAdapter;
import com.kav.wearoscarconnect.fordmodels.FordVehicleStatus;
import com.kav.wearoscarconnect.interfaces.CarListener;
import com.kav.wearoscarconnect.models.StatusListItem;

import java.util.ArrayList;

public class InformationActivity extends Activity implements CarListener {

    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<StatusListItem> statusListItem = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);

        WearableRecyclerView wearableRecyclerView = findViewById(R.id.informationRecyclerView);



        wearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        wearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this));

        //Add items to list
        statusListItem.add(new StatusListItem(R.drawable.icon_1, "Item 1"));
        statusListItem.add(new StatusListItem(R.drawable.icon_1, "Item 1"));
        statusListItem.add(new StatusListItem(R.drawable.icon_1, "Item 1"));
        statusListItem.add(new StatusListItem(R.drawable.icon_1, "Item 1"));
        statusListItem.add(new StatusListItem(R.drawable.icon_1, "Item 1"));


        wearableRecyclerView.setAdapter(new MainMenuAdapter(this, statusListItem, new MainMenuAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer menuPosition) {

            }
        }));

        swipeRefreshLayout = findViewById(R.id.informationSwipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onInformationRefresh();
            }
        });
    }

    void onInformationRefresh(){

    }

    @Override
    public void onStatusChanged(FordVehicleStatus obj) {

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
