package com.bin.movie

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.viewbinding.BuildConfig
import com.bin.movie.data.local.BinSharedPref
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BinMovieApp : Application() {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun baseUrl(): String
    external fun apiKey(): String

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupHawk()
        lockOrientation()
    }

    private fun setupHawk() {
        BinSharedPref.appInit(this)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun lockOrientation() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

        })
    }
}