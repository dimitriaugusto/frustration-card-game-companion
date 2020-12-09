package com.dimilo.frustration.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.PlayerRound;
import com.dimilo.frustration.ui.FirstRoundDialog;
import com.dimilo.frustration.ui.GameTableView;

public class GameTableFragment extends Fragment {

    private GameTableView mGameTableView;
    private FirstRoundDialog mFirstRoundDialog;

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
        setupClickListeners();
    }

    private void setupClickListeners() {
        getActivity().findViewById(R.id.add_play).setOnClickListener(this::addPlay);
        getActivity().findViewById(R.id.edit_play).setOnClickListener(this::editPlay);
    }

    private void addPlay(View view) {
        mFirstRoundDialog.show(new PlayerRound(1), play -> mGameTableView.put(play));
        // todo next rounds. above is first
    }

    private void editPlay(View view) {
    }

    public interface PlayCallback {
        void call(PlayerRound play);
    }

}