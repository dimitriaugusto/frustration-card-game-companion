package com.dimiaug.frustration.features.welcome.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val welcomeModule = module {

    viewModel {
        com.dimiaug.frustration.features.welcome.ui.WelcomeViewModel()
    }
}