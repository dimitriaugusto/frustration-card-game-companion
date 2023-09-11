package com.dimiaug.frustration.features.play.domain.interfaces;

import android.app.Activity;
import android.content.Context;

import com.dimiaug.frustration.features.play.ui.view.GameTableView;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.reset.ResetGameDialog;
import com.dimiaug.frustration.features.play.ui.viewmodel.GameTableViewModel;

public interface PlayInjection {
    GameTableViewModel getGameTableViewModel();

    GameTableView getGameTableView(Activity activity);

    FirstPlayDialog getFirstPlayDialog(Context context);

    NextPlayDialog getNextPlayDialog(Context context);

    EditPlayDialog getEditPlayDialog(Context context);

    ResetGameDialog getResetGameDialog(Context context);
}
