package com.dimilo.frustration.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.GameTable;
import com.dimilo.frustration.model.Play;
import com.dimilo.frustration.model.Summary;
import com.dimilo.frustration.view.dialog.ResetGameDialog;
import com.dimilo.frustration.view.dialog.play.EditPlayDialog;
import com.dimilo.frustration.view.dialog.play.FirstPlayDialog;
import com.dimilo.frustration.view.dialog.play.NextPlayDialog;
import com.dimilo.frustration.view.GameTableView;

import static com.dimilo.frustration.utils.StringUtils.isEmpty;

public class GameTableFragment extends Fragment {

    private GameTableView mGameTableView;
    private FirstPlayDialog mFirstPlayDialog;
    private NextPlayDialog mNextPlayDialog;
    private EditPlayDialog mEditPlayDialog;
    private ResetGameDialog mResetGameDialog;

    private GameTable mGameTable;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initModelAndView();
        initDialogs();
        setupClickListeners();
    }

    private void initModelAndView() {
        mGameTable = new GameTable(getContext());
        mGameTableView = new GameTableView(getActivity());
    }

    private void initDialogs() {
        mFirstPlayDialog = new FirstPlayDialog(getContext());
        mNextPlayDialog = new NextPlayDialog(getContext());
        mEditPlayDialog = new EditPlayDialog(getContext());
        mResetGameDialog = new ResetGameDialog(getContext());
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::showAddPlayDialog);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::showEditPlayDialog);
        getActivity().findViewById(R.id.reset_game).setOnClickListener(this::showResetGameDialog);
    }

    private void showResetGameDialog(View view) {
        mResetGameDialog.show(this::onResetConfirmed);
    }

    private void onResetConfirmed() {
        getParentFragmentManager().beginTransaction().remove(this).commit();
        NavHostFragment.findNavController(this).navigate(R.id.action_reopen_game_table_fragment);
    }

    private void showAddPlayDialog(View view) {
        if (!mGameTable.isGameFinished())
            if (isEmpty(mGameTable.getNextPlay().getPlayer())) showAddFirstPlayDialog();
            else showAddNextPlayDialog(mGameTable.getNextPlay().getRound());
    }

    private void showAddFirstPlayDialog() {
        mFirstPlayDialog.show(new Play(1), this::onPlayerFirstPlayAdded);
    }

    private void onPlayerFirstPlayAdded(Play play) {
        if (!isEmpty(play.getPlayer())) {
            updateGameTableView(play, putPlayToGameTable(play));
            showAddFirstPlayDialog(); // pop for next new player
        }
    }

    private void showAddNextPlayDialog(int round) {
        Play play = mGameTable.getNextPlay();
        if (play.getRound() == round)
            mNextPlayDialog.show(play, this::onPlayerNextPlayAdded);
    }

    private void onPlayerNextPlayAdded(Play play) {
        if (!isEmpty(play.getPlayer())) {
            updateGameTableView(play, putPlayToGameTable(play));
            showAddNextPlayDialog(play.getRound());
        }
    }

    private void showEditPlayDialog(View view) {
        mEditPlayDialog.show(new Play(), this::onPlayEdited);
    }

    private void onPlayEdited(Play play) {
        if (!isEmpty(play.getPlayer()))
            updateGameTableView(play, putPlayToGameTableIfPresent(play));
    }

    private Summary putPlayToGameTable(Play play) {
        return mGameTable.put(play);
    }

    private Summary putPlayToGameTableIfPresent(Play play) {
        return mGameTable.putIfPresent(play);
    }

    private void updateGameTableView(Play play, Summary total) {
        if (!isEmpty(play.getPlayer()) && !isEmpty(total.getPlayer())) {
            mGameTableView.put(play);
            mGameTableView.put(total);
        }
    }

}
