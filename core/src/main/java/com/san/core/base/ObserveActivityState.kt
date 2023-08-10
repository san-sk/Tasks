package com.san.core.base

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber
import kotlin.reflect.KProperty

class ObserveActivityState {

    operator fun getValue(thisRef: Any, property: KProperty<*>):  Any{
        return thisRef
    }

    operator fun setValue(thisRef: ComponentActivity?, property: KProperty<*>, value: String) {
        thisRef?.lifecycle?.addObserver(object : LifecycleEventObserver {

            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Timber.tag("ActivityState").i(event.targetState.name)
            }

        })
    }

}