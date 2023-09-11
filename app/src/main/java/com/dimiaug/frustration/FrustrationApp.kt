package com.dimiaug.frustration

import android.app.Application
import com.dimiaug.frustration.common.di.commonModule
import com.dimiaug.frustration.features.play.di.playModule
import com.dimiaug.frustration.features.welcome.di.welcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FrustrationApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FrustrationApp)
            modules(
                commonModule,
                welcomeModule,
                playModule
            )
        }
    }

}