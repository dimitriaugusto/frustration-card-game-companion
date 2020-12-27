package com.dimiaug.frustration.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dimiaug.frustration.R;
import com.dimiaug.frustration.model.Play;
import com.dimiaug.frustration.ui.dialog.ResetGameDialog;
import com.dimiaug.frustration.ui.dialog.play.EditPlayDialog;
import com.dimiaug.frustration.ui.dialog.play.FirstPlayDialog;
import com.dimiaug.frustration.ui.dialog.play.NextPlayDialog;
import com.dimiaug.frustration.ui.dialog.play.PlaysDialogBase;
import com.dimiaug.frustration.viewmodel.GameTableViewModel;

import static com.dimiaug.frustration.utils.StringUtils.isEmpty;

public class GameTableFragment extends Fragment {

    private GameTableView mGameTableView;
    private FirstPlayDialog mFirstPlayDialog;
    private NextPlayDialog mNextPlayDialog;
    private EditPlayDialog mEditPlayDialog;
    private ResetGameDialog mResetGameDialog;

    private GameTableViewModel mViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        initViews();
        initDialogs();
        setupClickListeners();
        observeData();

        if (savedInstanceState != null)
            repeatShowDialog(savedInstanceState);
    }

    private void repeatShowDialog(Bundle savedInstanceState) {
        if (savedInstanceState.getBoolean(mFirstPlayDialog.getDialogShowingKey(), false))
            showAddFirstPlayDialog(new Play(savedInstanceState));
        else if (savedInstanceState.getBoolean(mNextPlayDialog.getDialogShowingKey(), false))
            showAddNextPlayDialog(new Play(savedInstanceState));
        else if (savedInstanceState.getBoolean(mEditPlayDialog.getDialogShowingKey(), false))
            showEditPlayDialog(new Play(savedInstanceState));
        else if (savedInstanceState.getBoolean(mResetGameDialog.getDialogShowingKey(), false))
            showResetGameDialog();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mFirstPlayDialog.isShowing()) saveStateAndDismiss(outState, mFirstPlayDialog);
        else if (mNextPlayDialog.isShowing()) saveStateAndDismiss(outState, mNextPlayDialog);
        else if (mEditPlayDialog.isShowing()) saveStateAndDismiss(outState, mEditPlayDialog);
        else if (mResetGameDialog.isShowing()) saveStateAndDismiss(outState, mResetGameDialog);
    }

    private void saveStateAndDismiss(Bundle outState, PlaysDialogBase dialog) {
        outState.putBoolean(dialog.getDialogShowingKey(), true);
        outState.putAll(dialog.getPlayState().getState());
        dialog.dismiss();
    }

    private void saveStateAndDismiss(Bundle outState, ResetGameDialog dialog) {
        outState.putBoolean(dialog.getDialogShowingKey(), true);
        dialog.dismiss();
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(getActivity()).get(GameTableViewModel.class);
    }

    private void initViews() {
        if (mGameTableView != null) mGameTableView.dispose();
        mGameTableView = new GameTableView(getActivity());
    }

    private void initDialogs() {
        mFirstPlayDialog = new FirstPlayDialog(getContext());
        mNextPlayDialog = new NextPlayDialog(getContext());
        mEditPlayDialog = new EditPlayDialog(getContext());
        mResetGameDialog = new ResetGameDialog(getContext());
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::onAddPlayClicked);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::onEditPlayClicked);
        getActivity().findViewById(R.id.reset_game).setOnClickListener(this::onResetGameClicked);
    }

    private void observeData() {
        mViewModel.getAllPlays().observe(getViewLifecycleOwner(),
                plays -> plays.forEach(mGameTableView::put));
        mViewModel.getAllSummaries().observe(getViewLifecycleOwner(),
                summaries -> summaries.forEach(mGameTableView::put));
    }

    private void onResetGameClicked(View view) {
        showResetGameDialog();
    }

    private void showResetGameDialog() {
        mResetGameDialog.show(this::onResetConfirmed);
    }

    private void onResetConfirmed() {
        mViewModel.clearGame();
        initViews();
    }

    private void onAddPlayClicked(View view) {
        if (!mViewModel.isGameFinished()) {
            Play nextPlay = mViewModel.getNextPlay();
            if (isEmpty(nextPlay.getPlayer())) showAddFirstPlayDialog(new Play(1));
            else showAddNextPlayDialog(nextPlay);
        }
    }

    private void showAddFirstPlayDialog(Play play) {
        mFirstPlayDialog.show(play, this::onPlayerFirstPlayAdded);
    }

    private void onPlayerFirstPlayAdded(Play play) {
        if (!isEmpty(play.getPlayer())) {
            mViewModel.addPlay(play);
            showAddFirstPlayDialog(new Play(1)); // pop for next new player
        }
    }

    private void showAddNextPlayDialog(Play play) {
        mNextPlayDialog.show(play, this::onPlayerNextPlayAdded);
    }

    private void onPlayerNextPlayAdded(Play play) {
        if (!isEmpty(play.getPlayer())) {
            mViewModel.addPlay(play);

            Play nextPlay = mViewModel.getNextPlay();
            if (nextPlay.getRound() == play.getRound())
                showAddNextPlayDialog(nextPlay);
        }
    }

    private void onEditPlayClicked(View view) {
        showEditPlayDialog(new Play());
    }

    private void showEditPlayDialog(Play play) {
        mEditPlayDialog.show(play, this::onPlayEdited);
    }

    private void onPlayEdited(Play play) {
        if (!isEmpty(play.getPlayer()))
            mViewModel.updatePlay(play);
    }

}
