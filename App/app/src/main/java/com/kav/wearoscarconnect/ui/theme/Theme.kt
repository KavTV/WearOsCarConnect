package com.kav.wearoscarconnect.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

@Composable
fun WearOsCarConnectTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = WearOsCarConnectPalette,
        typography = WearOsCarConnectTypography,
        // For shapes, we generally recommend using the default Material Wear shapes which are
        // optimized for round and non-round devices.
        content = content
    )

}