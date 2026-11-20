package com.shieldtube.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShieldTubeApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
