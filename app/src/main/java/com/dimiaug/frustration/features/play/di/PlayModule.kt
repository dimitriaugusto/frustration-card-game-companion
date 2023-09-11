package com.dimiaug.frustration.features.play.di

import com.dimiaug.frustration.features.play.domain.interfaces.PlayInjection
import com.dimiaug.frustration.features.play.gateways.repo.PlayRepository
import com.dimiaug.frustration.features.play.ui.view.GameTableView
import com.dimiaug.frustration.features.play.ui.view.dialog.play.EditPlayDialog
import com.dimiaug.frustration.features.play.ui.view.dialog.play.FirstPlayDialog
import com.dimiaug.frustration.features.play.ui.view.dialog.play.NextPlayDialog
import com.dimiaug.frustration.features.play.ui.view.dialog.reset.ResetGameDialog
import com.dimiaug.frustration.features.play.ui.viewmodel.GameTableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playModule = module {
    single<PlayInjection> { PlayInjectionImpl() }

    /* domain objects */
    //

    /* view objects */
    viewModel {
        GameTableViewModel(
            get(),
            get()
        )
    }
    factory { params ->
        GameTableView(
            params.get()
        )
    }
    factory { params ->
        FirstPlayDialog(
            params.get()
        )
    }
    factory { params ->
        NextPlayDialog(
            params.get()
        )
    }
    factory { params ->
        EditPlayDialog(
            params.get()
        )
    }
    factory { params ->
        ResetGameDialog(
            params.get()
        )
    }

    /* gateway objects */
    single { PlayRepository(get()) }
}