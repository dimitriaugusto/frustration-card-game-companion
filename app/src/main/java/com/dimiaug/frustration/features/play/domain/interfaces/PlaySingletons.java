package com.dimiaug.frustration.features.play.domain.interfaces;

import com.dimiaug.frustration.features.play.entrypoints.view.GameTableView;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.reset.ResetGameDialog;
import com.dimiaug.frustration.features.play.entrypoints.viewmodel.GameTableViewModel;

public interface PlaySingletons {
    GameTableViewModel getGameTableViewModel();

    GameTableView getGameTableView();

    FirstPlayDialog getFirstPlayDialog();

    NextPlayDialog getNextPlayDialog();

    EditPlayDialog getEditPlayDialog();

    ResetGameDialog getResetGameDialog();
}
