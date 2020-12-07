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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        GameTableView gameTableView = new GameTableView((ConstraintLayout) view);
//        GameTableModel gameTable = new GameTableModel();

        gameTableView.put("dimi", 1, 3);
        gameTableView.put("dimi", 2, 40);
        gameTableView.put("dimi", 3, 45);
        gameTableView.put("dimi", 4, 50);
        gameTableView.put("dimi", 5, 105);
        gameTableView.put("suzan", 1, 105);
        gameTableView.put("suzan", 2, 10);
        gameTableView.put("suzan", 3, 105);
        gameTableView.put("suzan", 4, 5);
        gameTableView.put("suzan", 5, 105);
        gameTableView.put("rob", 1, 105);
        gameTableView.put("rob", 2, 10);
        gameTableView.put("rob", 3, 105);
        gameTableView.put("rob", 4, 5);
        gameTableView.put("rob", 5, 105);
        gameTableView.put("desi", 1, 105);
        gameTableView.put("desi", 2, 10);
        gameTableView.put("desi", 3, 105);
        gameTableView.put("desi", 4, 5);
        gameTableView.put("desi", 5, 105);
        gameTableView.put("mari", 5, 105);
        gameTableView.put("monica", 5, 105);
    }
}