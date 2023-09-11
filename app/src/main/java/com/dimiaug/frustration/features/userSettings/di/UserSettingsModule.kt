package com.dimiaug.frustration.features.userSettings.di

import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.features.userSettings.domain.interfaces.UserSettingsRepo
import com.dimiaug.frustration.features.userSettings.domain.useCases.GetSettingsUseCase
import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsDao
import com.dimiaug.frustration.features.userSettings.gateways.repo.UserUserSettingsRepoImpl
import org.koin.dsl.module

val userSettingModule = module {

    /* domain */
    single {
        GetSettingsUseCase(
            get(),
            get()
        )
    }

    /* gateways */
    single<UserSettingsRepo> {
        UserUserSettingsRepoImpl(
            get(),
            get()
        )
    }
    single<UserSettingsDao> { get<AppDatabase>().settingsDao() }
}