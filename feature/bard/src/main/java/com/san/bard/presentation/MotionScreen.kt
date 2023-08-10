package com.san.bard.presentation

import android.hardware.Sensor
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san.core.sensors.Sensors
import com.san.core.sensors.Sensors.getSensorList

@Composable
fun MotionScreen() {
    val context = LocalContext.current
    var speed by remember {
        mutableStateOf("")
    }
    LazyColumn {
        items(Sensors.getSensorManager(context)?.getSensorList()!!) {
            SensorItem(it)
        }
        item {
            Text(text = "")
        }
    }
}

@Composable
fun SensorItem(sensor: Sensor) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Text(sensor.name)
    }
}

@Composable
@Preview
fun SensorPreview() {
    MotionScreen()
}