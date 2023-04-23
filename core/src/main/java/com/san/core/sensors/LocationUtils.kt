package com.san.core.sensors

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

object LocationUtils {

    fun locationListener(callBack: (speed: String) -> Unit) = LocationListener { location ->
        var speed = location.speed // get speed in m/s
        // convert speed to km/h
        speed = (speed * 3.6).toFloat()
        Timber.d("Location - Current speed: $speed km/h");
    }

    fun getLocationManager(context: Context) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager


        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
    }


    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    fun getSpeed(locationManager: LocationManager?): Flow<String> {
        val flow = callbackFlow<String> {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0F,
                locationListener {
                }
            )
        }
        return flow
    }
}