package com.dimiaug.frustration.common.di

import androidx.room.Room.databaseBuilder
import com.dimiaug.frustration.common.domain.interfaces.Loggr
import com.dimiaug.frustration.common.gateways.data.AppDatabase
import com.dimiaug.frustration.common.gateways.log.LoggrImpl
import org.koin.dsl.module

val commonModule = module {

    /* UI */

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