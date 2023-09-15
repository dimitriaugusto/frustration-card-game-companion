package com.dimiaug.frustration.features.welcome.di

import com.dimiaug.frustration.features.welcome.ui.java.controllers.WelcomeController
import com.dimiaug.frustration.features.welcome.domain.interfaces.WelcomeInjection
import com.dimiaug.frustration.features.welcome.ui.java.presenters.WelcomePresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val welcomeModule = module {
    single<WelcomeInjection> { WelcomeInjectionImpl() }

    viewModel {
        com.dimiaug.frustration.features.welcome.ui.WelcomeViewModel()
    }

    factory { params ->
        WelcomeController(
            params[0],
            params[1]
        )
    }
    factory { params ->
        WelcomePresenter(
            params[0],
            params[1]
        )
    }
}