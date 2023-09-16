package com.dimiaug.frustration.main.optionsMenu.di

import com.dimiaug.frustration.main.optionsMenu.ui.OptionsMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val optionsMenuModule = module {
    viewModel { OptionsMenuViewModel() }
}