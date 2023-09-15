package com.dimiaug.frustration.common.domain.interfaces;

import com.dimiaug.frustration.common.ui.controllers.OptionsMenuController;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;
import com.dimiaug.frustration.main.OldMainActivity;

public interface CommonInjection {
    MainPresenter getMainPresenter(OldMainActivity mainActivity);

    OptionsMenuController getMainController(OldMainActivity mainActivity, MainPresenter presenter);
}
