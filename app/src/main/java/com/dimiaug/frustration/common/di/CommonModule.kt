package com.dimiaug.frustration.common.di

import androidx.room.Room.databaseBuilder
import com.dimiaug.frustration.common.domain.interfaces.CommonInjection
import com.dimiaug.frustration.common.domain.interfaces.Loggr
import com.dimiaug.frustration.common.gateways.log.LoggrImpl
import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.common.ui.controllers.OptionsMenuController
import com.dimiaug.frustration.common.ui.presenters.MainPresenter
import org.koin.dsl.module

val commonModule = module {

    single<CommonInjection> {
        CommonInjectionImpl()
    }

    /* UI */
    factory { params -> MainPresenter(params.get(), get()) }
    factory { params ->
        OptionsMenuController(
            params[0],
            params[1],
            get(),
            get()
        )
    }

    /* domain */


    /* general gateways */
    single<Loggr> { LoggrImpl() }

    /* persistence */
    single {
        databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build();
    }

}