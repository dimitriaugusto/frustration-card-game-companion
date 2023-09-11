package com.dimiaug.frustration.features.welcome.domain.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dimiaug.frustration.features.welcome.ui.controllers.WelcomeController;
import com.dimiaug.frustration.features.welcome.ui.presenters.WelcomePresenter;

public interface WelcomeInjection {
    WelcomePresenter getWelcomePresenter(LayoutInflater inflater, ViewGroup container);

    WelcomeController getWelcomeController(View view, Fragment fragment);
}
