package com.dimiaug.frustration

import android.app.Application
import com.dimiaug.frustration.common.appModule
import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.common.gateways.repo.AppRepository
import com.dimiaug.frustration.features.play.di.playModule
import com.dimiaug.frustration.features.welcome.welcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

//import org.koin.core.context.startKoin

class FrustrationApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FrustrationApp)
            modules(
                appModule,
                playModule,
                welcomeModule
            )
        }
    }

    val repository: AppRepository
        get() = AppRepository.getInstance(database)
    private val database: AppDatabase
        get() = AppDatabase.getInstance(this)

}