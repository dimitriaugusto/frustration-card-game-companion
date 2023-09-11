package com.dimiaug.frustration.features.play.di;

import static org.koin.core.parameter.ParametersHolderKt.parametersOf;
import static org.koin.java.KoinJavaComponent.inject;

import android.app.Activity;
import android.content.Context;

import com.dimiaug.frustration.features.play.domain.interfaces.PlayInjection;
import com.dimiaug.frustration.features.play.ui.view.GameTableView;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.features.play.ui.view.dialog.reset.ResetGameDialog;
import com.dimiaug.frustration.features.play.ui.viewmodel.GameTableViewModel;

import kotlin.Lazy;

public class PlayInjectionImpl implements PlayInjection {

    private final Lazy<GameTableViewModel> mGameTableViewModel = inject(GameTableViewModel.class);

    @Override
    public GameTableViewModel getGameTableViewModel() {
        return mGameTableViewModel.getValue();
    }

    @Override
    public GameTableView getGameTableView(Activity activity) {
        Lazy<GameTableView> mGameTableView = inject(
                GameTableView.class, null, () -> parametersOf(activity)
        );
        return mGameTableView.getValue();
    }

    @Override
    public FirstPlayDialog getFirstPlayDialog(Context context) {
        Lazy<FirstPlayDialog> mFirstPlayDialog = inject(
                FirstPlayDialog.class, null, () -> parametersOf(context)
        );
        return mFirstPlayDialog.getValue();
    }

    @Override
    public NextPlayDialog getNextPlayDialog(Context context) {
        Lazy<NextPlayDialog> mNextPlayDialog = inject(
                NextPlayDialog.class, null, () -> parametersOf(context)
        );
        return mNextPlayDialog.getValue();
    }

    @Override
    public EditPlayDialog getEditPlayDialog(Context context) {
        Lazy<EditPlayDialog> mEditPlayDialog = inject(
                EditPlayDialog.class, null, () -> parametersOf(context)
        );
        return mEditPlayDialog.getValue();
    }

    @Override
    public ResetGameDialog getResetGameDialog(Context context) {
        Lazy<ResetGameDialog> mResetGameDialog = inject(
                ResetGameDialog.class, null, () -> parametersOf(context)
        );
        return mResetGameDialog.getValue();
    }
}
