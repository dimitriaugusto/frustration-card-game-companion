package com.dimiaug.frustration.features.play;

import static org.koin.java.KoinJavaComponent.inject;

import com.dimiaug.frustration.features.play.domain.interfaces.PlaySingletons;
import com.dimiaug.frustration.features.play.entrypoints.view.GameTableView;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.features.play.entrypoints.view.dialog.reset.ResetGameDialog;
import com.dimiaug.frustration.features.play.entrypoints.viewmodel.GameTableViewModel;

import kotlin.Lazy;

public class PlaySingletonsImpl implements PlaySingletons {

    private final Lazy<GameTableViewModel> mGameTableViewModel = inject(GameTableViewModel.class);
    private final Lazy<GameTableView> mGameTableView = inject(GameTableView.class);
    private final Lazy<FirstPlayDialog> mFirstPlayDialog = inject(FirstPlayDialog.class);
    private final Lazy<NextPlayDialog> mNextPlayDialog = inject(NextPlayDialog.class);
    private final Lazy<EditPlayDialog> mEditPlayDialog = inject(EditPlayDialog.class);
    private final Lazy<ResetGameDialog> mResetGameDialog = inject(ResetGameDialog.class);

    @Override
    public GameTableViewModel getGameTableViewModel() {
        return mGameTableViewModel.getValue();
    }

    @Override
    public GameTableView getGameTableView() {
        return mGameTableView.getValue();
    }

    @Override
    public FirstPlayDialog getFirstPlayDialog() {
        return mFirstPlayDialog.getValue();
    }

    @Override
    public NextPlayDialog getNextPlayDialog() {
        return mNextPlayDialog.getValue();
    }

    @Override
    public EditPlayDialog getEditPlayDialog() {
        return mEditPlayDialog.getValue();
    }

    @Override
    public ResetGameDialog getResetGameDialog() {
        return mResetGameDialog.getValue();
    }
}
