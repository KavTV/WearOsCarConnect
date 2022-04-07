package com.kav.wearoscarconnect.composables

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.*


@Composable
fun ChipExample(
        modifier: Modifier = Modifier,
        iconModifier: Modifier = Modifier,
        icon: Painter
) {
    Chip(
            modifier = modifier,
            onClick = { /* ... */ },
            label = {
                Text(
                        text = "5 minute Meditation",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                )
            },
            icon = {
                Icon(
                        painter = icon,
                        contentDescription = "triggers meditation action",
                        modifier = iconModifier
                )
            },
            colors = ChipDefaults.chipColors(
                    backgroundColor = Color(0xFF1b1e23),
                    contentColor = Color(0xFFfafafa)
            )
    )
}
