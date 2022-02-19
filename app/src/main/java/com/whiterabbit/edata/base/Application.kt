package com.whiterabbit.edata

import android.app.Application

class Application: Application() {

    companion object {
        lateinit var instance: com.whiterabbit.edata.Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}