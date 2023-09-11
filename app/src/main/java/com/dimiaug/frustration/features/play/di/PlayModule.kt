package com.dimiaug.frustration.features.play.di

import com.dimiaug.frustration.features.play.domain.interfaces.PlaySingletons
import com.dimiaug.frustration.features.play.entrypoints.view.GameTableView
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.EditPlayDialog
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.FirstPlayDialog
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.NextPlayDialog
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.reset.ResetGameDialog
import com.dimiaug.frustration.features.play.entrypoints.viewmodel.GameTableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playModule = module {
    single<PlaySingletons> { PlaySingletonsImpl() }

    /* domain objects */
    //

    /* view objects */
    viewModel { GameTableViewModel(get()) }
    factory { params -> GameTableView(params.get()) }
    factory { params -> FirstPlayDialog(params.get()) }
    factory { params -> NextPlayDialog(params.get()) }
    factory { params -> EditPlayDialog(params.get()) }
    factory { params -> ResetGameDialog(params.get()) }

    /* gateway objects */
    //
}