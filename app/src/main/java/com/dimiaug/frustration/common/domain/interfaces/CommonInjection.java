package com.dimiaug.frustration.common.domain.interfaces;

import com.dimiaug.frustration.common.entrypoints.MainActivity;
import com.dimiaug.frustration.common.ui.controllers.OptionsMenuController;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;

public interface CommonInjection {
    MainPresenter getMainPresenter(MainActivity mainActivity);

    OptionsMenuController getMainController(MainActivity mainActivity, MainPresenter presenter);
}
