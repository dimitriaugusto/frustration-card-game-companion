package com.dimiaug.frustration.features.welcome.di

import com.dimiaug.frustration.features.welcome.ui.controllers.WelcomeController
import com.dimiaug.frustration.features.welcome.domain.interfaces.WelcomeInjection
import com.dimiaug.frustration.features.welcome.ui.presenters.WelcomePresenter
import org.koin.dsl.module

val welcomeModule = module {
    single<WelcomeInjection> { WelcomeInjectionImpl() }

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