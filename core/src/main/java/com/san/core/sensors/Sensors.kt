package com.san.core.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

object Sensors {

    fun getSensorManager(context: Context): SensorManager? {
        return context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    }

    fun SensorManager?.getSensorList() = this?.getSensorList(Sensor.TYPE_ALL) ?: emptyList()


}