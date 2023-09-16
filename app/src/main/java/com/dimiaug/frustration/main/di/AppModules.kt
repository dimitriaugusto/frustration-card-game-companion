package com.dimiaug.frustration.main.di

import android.app.Application
import com.dimiaug.frustration.common.di.commonModule
import com.dimiaug.frustration.main.optionsMenu.di.optionsMenuModule
import com.dimiaug.frustration.features.play.di.playModule
import com.dimiaug.frustration.features.userSettings.di.userSettingModule
import com.dimiaug.frustration.features.welcome.di.welcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

fun startKoinModules(application: Application) {
    GlobalContext.startKoin {
        androidLogger()
        androidContext(application)
        modules(
            commonModule,
            optionsMenuModule,
            welcomeModule,
            userSettingModule,
            playModule
        )
    }
}
