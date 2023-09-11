package com.dimiaug.frustration.features.welcome.entrypoints;

import static org.koin.java.KoinJavaComponent.inject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimiaug.frustration.features.welcome.ui.controllers.WelcomeController;
import com.dimiaug.frustration.features.welcome.domain.interfaces.WelcomeInjection;
import com.dimiaug.frustration.features.welcome.ui.presenters.WelcomePresenter;

public class WelcomeFragment extends Fragment {

    private final WelcomeInjection mInjection =
            (WelcomeInjection) inject(WelcomeInjection.class).getValue();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WelcomePresenter presenter = mInjection.getWelcomePresenter(inflater, container);
        return presenter.present();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WelcomeController controller = mInjection.getWelcomeController(view, this);
        controller.setListeners();
    }
}