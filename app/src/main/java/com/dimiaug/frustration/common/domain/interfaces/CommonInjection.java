package com.dimiaug.frustration.common.domain.interfaces;

import com.dimiaug.frustration.common.entrypoints.MainActivity;
import com.dimiaug.frustration.common.ui.controllers.MainController;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;

public interface CommonInjection {
    MainPresenter getMainPresenter(MainActivity mainActivity);

    MainController getMainController(MainActivity mainActivity, MainPresenter presenter);
}
