package com.maxi.petzify

import android.app.Application
import com.maxi.petzify.data.core.storage.Pref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Petzify : Application() {
    companion object {
        lateinit var pref: Pref
    }

    override fun onCreate() {
        super.onCreate()
        pref = Pref(applicationContext)
    }
}