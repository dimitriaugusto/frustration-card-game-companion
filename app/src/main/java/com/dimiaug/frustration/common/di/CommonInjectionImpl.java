package com.dimiaug.frustration.common.di;

import static org.koin.core.parameter.ParametersHolderKt.parametersOf;
import static org.koin.java.KoinJavaComponent.inject;

import com.dimiaug.frustration.common.domain.interfaces.CommonInjection;
import com.dimiaug.frustration.common.ui.controllers.OptionsMenuController;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;
import com.dimiaug.frustration.main.OldMainActivity;

import kotlin.Lazy;

public class CommonInjectionImpl implements CommonInjection {
    @Override
    public MainPresenter getMainPresenter(OldMainActivity mainActivity) {
        Lazy<MainPresenter> presenter =
                inject(MainPresenter.class, null, () -> parametersOf(mainActivity));
        return presenter.getValue();
    }

    @Override
    public OptionsMenuController getMainController(OldMainActivity mainActivity, MainPresenter presenter) {
        Lazy<OptionsMenuController> controller =
                inject(OptionsMenuController.class, null,
                        () -> parametersOf(mainActivity, presenter));
        return controller.getValue();
    }
}
