package com.dimiaug.frustration.features.play.domain.interfaces;

import android.app.Activity;
import android.content.Context;

import com.dimiaug.frustration.features.play.entrypoints.view.GameTableView;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.reset.ResetGameDialog;
import com.dimiaug.frustration.features.play.entrypoints.viewmodel.GameTableViewModel;

public interface PlaySingletons {
    GameTableViewModel getGameTableViewModel();

    GameTableView getGameTableView(Activity activity);

    FirstPlayDialog getFirstPlayDialog(Context context);

    NextPlayDialog getNextPlayDialog(Context context);

    EditPlayDialog getEditPlayDialog(Context context);

    ResetGameDialog getResetGameDialog(Context context);
}
