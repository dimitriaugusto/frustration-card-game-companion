package com.dimilo.frustration.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.GameTableModel;
import com.dimilo.frustration.model.PlayerRound;
import com.dimilo.frustration.model.PlayerTotal;
import com.dimilo.frustration.view.FirstRoundDialog;
import com.dimilo.frustration.view.GameTableView;
import com.dimilo.frustration.view.NextRoundDialog;

import static com.dimilo.frustration.utils.StringUtils.isEmpty;

public class GameTableFragment extends Fragment {

    private GameTableView mGameTableView;
    private FirstRoundDialog mFirstRoundDialog;
    private NextRoundDialog mNextRoundDialog;

    private GameTableModel mGameTable;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGameTableView = new GameTableView(getActivity());
        mFirstRoundDialog = new FirstRoundDialog(getContext());
        mNextRoundDialog = new NextRoundDialog(getContext());
        mGameTable = new GameTableModel(getContext());
        setupClickListeners();
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::addPlay);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::editPlay);
    }

    private void addPlay(View view) {
        PlayerRound play = mGameTable.getNextPlay();
        if (isEmpty(play.getPlayer())) addFirstRound();
        else addNextRound(play.getRound());
    }

    private void addFirstRound() {
        mFirstRoundDialog.show(new PlayerRound(1), this::onFirstRoundAdded);
    }

    private void onFirstRoundAdded(PlayerRound play) {
        if (play != null) {
            putPlayToGameTable(play);
            addFirstRound(); // pop for next new player
        }
    }

    private void addNextRound(int round) {
        PlayerRound play = mGameTable.getNextPlay();
        if (play.getRound() == round)
            mNextRoundDialog.show(play, this::onNextRoundAdded);
    }

    private void onNextRoundAdded(PlayerRound play) {
        PlayerTotal total = putPlayToGameTable(play);
        updateGameTableView(play, total);
        addNextRound(play.getRound());
    }

    private PlayerTotal putPlayToGameTable(PlayerRound play) {
        PlayerTotal total = mGameTable.put(play);
        return total;
    }

    private void updateGameTableView(PlayerRound play, PlayerTotal total) {
        mGameTableView.put(play);
        mGameTableView.put(total);
    }

    private void editPlay(View view) {
    }

}