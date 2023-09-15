package com.dimiaug.frustration.features.welcome.di;

import static org.koin.core.parameter.ParametersHolderKt.parametersOf;
import static org.koin.java.KoinJavaComponent.inject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dimiaug.frustration.features.welcome.ui.java.controllers.WelcomeController;
import com.dimiaug.frustration.features.welcome.domain.interfaces.WelcomeInjection;
import com.dimiaug.frustration.features.welcome.ui.java.presenters.WelcomePresenter;

import kotlin.Lazy;

public class WelcomeInjectionImpl implements WelcomeInjection {
    @Override
    public WelcomePresenter getWelcomePresenter(LayoutInflater inflater, ViewGroup container) {
        Lazy<WelcomePresenter> presenter = inject(
                WelcomePresenter.class, null, () -> parametersOf(inflater, container)
        );
        return presenter.getValue();
    }

    @Override
    public WelcomeController getWelcomeController(View view, Fragment fragment) {
        Lazy<WelcomeController> controller = inject(
                WelcomeController.class, null, () -> parametersOf(view, fragment)
        );
        return controller.getValue();
    }
}
