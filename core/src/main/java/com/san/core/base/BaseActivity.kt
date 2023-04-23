package com.san.core.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import timber.log.Timber

abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.i("BaseActivity - onCreate(): ${this.javaClass.simpleName}")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("BaseActivity - onStart(): ${this.javaClass.simpleName}")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("BaseActivity - onResume(): ${this.javaClass.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("BaseActivity - onPause(): ${this.javaClass.simpleName}")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("BaseActivity - onStop(): ${this.javaClass.simpleName}")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("BaseActivity - onRestart(): ${this.javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("BaseActivity - onDestroy(): ${this.javaClass.simpleName}")
    }
}
