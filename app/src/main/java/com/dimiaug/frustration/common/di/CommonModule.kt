package com.dimiaug.frustration.common.di

import androidx.room.Room.databaseBuilder
import com.dimiaug.frustration.common.domain.IsThereSettingsUseCase
import com.dimiaug.frustration.common.domain.interfaces.CommonInjection
import com.dimiaug.frustration.common.domain.interfaces.SettingsRepo
import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.common.gateways.settings.data.SettingsDao
import com.dimiaug.frustration.common.gateways.settings.repo.SettingsRepoImpl
import com.dimiaug.frustration.common.ui.controllers.MainController
import com.dimiaug.frustration.common.ui.presenters.MainPresenter
import org.koin.dsl.module

val commonModule = module {

    single<CommonInjection> {
        CommonInjectionImpl()
    }

    /* UI */
    factory { params -> MainPresenter(params.get()) }
    factory { params -> MainController(params[0], params[1], get()) }

    /* domain */
    single { IsThereSettingsUseCase(get()) }

    /* persistence */
    single {
        databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build();
    }

    /* settings */
    single<SettingsRepo> { SettingsRepoImpl(get()) }
    single<SettingsDao> { get<AppDatabase>().settingsDao() }

}