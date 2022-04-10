package com.kav.wearoscarconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.kav.wearoscarconnect.composables.ChipExample
import com.kav.wearoscarconnect.interfaces.CarInformation
import com.kav.wearoscarconnect.interfaces.CarListener
import com.kav.wearoscarconnect.models.ChipInformation
import com.kav.wearoscarconnect.ui.theme.WearOsCarConnectTheme
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InformationComposeActivity : ComponentActivity(), CarListener {

    private val statusList =  mutableStateListOf<ChipInformation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Init

        //Add this activity to listener to get responses
        SelectedCar.car.addListener(this)

        //We want to get the newest information
        SelectedCar.car.statusRefresh()
        SelectedCar.car.status()

        //Load the design to screen
        setContent {
            WearOsCarConnectTheme {
                // A surface container using the 'background' color from the theme
                    AppDesign(statusList)
            }
        }

    }

    override fun onStatusChanged(carInfo: CarInformation?) {

        val newStatusList = mutableListOf<ChipInformation>()

        try {
            //GET THE TIME BETWEEN NOW AND LAST REFRESH
            val df = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
            val ldt1 = LocalDateTime.now()
            val ldt2 = LocalDateTime.parse(carInfo!!.lastRefresh, df)
            val d = Duration.between(ldt2, ldt1)
            newStatusList.add(
                ChipInformation(String.format("%d hours ago", d.toHours()),R.drawable.ic_history)
            )
        } catch (e: Exception) {
        }

        //Show specific icon depending on door lock status
        if (carInfo!!.lockStatus == "LOCKED") {
            newStatusList.add(ChipInformation(carInfo.lockStatus,R.drawable.ic_closedlock_white))
        } else {
            newStatusList.add(ChipInformation(carInfo.lockStatus, R.drawable.ic_openlock_white ))
        }

        //Add distance left for car
        newStatusList.add(
            ChipInformation(
                carInfo.distanceLeft.toString(),
                R.drawable.ic_gas
            )
        )

        //Adblue left
        newStatusList.add(ChipInformation(carInfo.adblueLeft, R.drawable.ic_wind))

        //Clear list when adding the new items
        statusList.clear()
        statusList.addAll(newStatusList)
    }

    override fun onDetails() {
        TODO("Not yet implemented")
    }

    override fun onStart(started: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStop(stopped: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onLock(locked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onUnlock(unlocked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        //Remove this from the car listener when activity closes
        SelectedCar.car.removeListener(this)

        super.onStop()
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppDesign(statusList: MutableList<ChipInformation>) {
    val listState = rememberScalingLazyListState()
    val coroutineScope = rememberCoroutineScope()

    //Use remember functions for better performance
    val focusRequester = remember { FocusRequester() }
    val rememberStatusList = remember {statusList}

    //Used to put time text and position indicator
    Scaffold(
        timeText = {
            if (!listState.isScrollInProgress) {
                TimeText()
            }
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState
            )
        }
    ){
        val contentModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
        val iconModifier = Modifier
            .size(24.dp)
            .wrapContentSize(align = Alignment.Center)

        //this is the scrollable list
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        listState.animateScrollBy(it.verticalScrollPixels)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable(),
            contentPadding = PaddingValues(
                top = 32.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 32.dp
            ),
            verticalArrangement = Arrangement.Center,
            state = listState
        ) {
            items(items = rememberStatusList){item ->
                ChipExample(item.Message,contentModifier, iconModifier, painterResource(id = item.Drawable))
            }

        }
    }
    //Got to request focus for rotary input to work
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WearOsCarConnectTheme {
        AppDesign(mutableListOf<ChipInformation>())
    }
}