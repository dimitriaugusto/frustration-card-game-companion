package com.dimilo.frustration.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.PlayerRound;
import com.dimilo.frustration.model.PlayerTotal;
import com.dimilo.frustration.ui.GameTableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class GameTableFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(GameTableFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> Snackbar.make(view1, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


        ConstraintLayout gameTableLayout = getActivity().findViewById(R.id.gameTableLayout);
        ConstraintLayout playerTotalsLayout = getActivity().findViewById(R.id.playerTotalsLayout);
        GameTableView gameTableView = new GameTableView(gameTableLayout, playerTotalsLayout);

//        GameTableModel gameTable = new GameTableModel();
        PlayerRound play = new PlayerRound("dimi", 1, 30);
        PlayerTotal total = new PlayerTotal("dimi", 60, "SET 3\nSET 4");
        gameTableView.put(play);
        gameTableView.put(total);

    }
}