package com.dimiaug.frustration

import android.app.Application
import com.dimiaug.frustration.common.appModule
import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.common.gateways.repo.AppRepository
import com.dimiaug.frustration.features.play.playModule
import org.koin.core.context.startKoin

class FrustrationApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                playModule
            )
        }
    }

    val repository: AppRepository
        get() = AppRepository.getInstance(database)
    private val database: AppDatabase
        get() = AppDatabase.getInstance(this)

}