package com.dimiaug.frustration.main

import android.app.Application
import com.dimiaug.frustration.main.di.startKoinModules

class FrustrationApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoinModules(this@FrustrationApp)
    }
}