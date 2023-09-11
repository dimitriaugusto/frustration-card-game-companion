package com.dimiaug.frustration.features.welcome

import com.dimiaug.frustration.features.welcome.entrypoints.WelcomeController
import com.dimiaug.frustration.features.welcome.entrypoints.WelcomePresenter
import org.koin.dsl.module

val welcomeModule = module {
    single { WelcomeController() }
    single { WelcomePresenter() }
}