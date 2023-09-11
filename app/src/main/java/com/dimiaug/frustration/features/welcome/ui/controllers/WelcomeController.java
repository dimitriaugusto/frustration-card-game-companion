package com.dimiaug.frustration.features.welcome.ui.controllers;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dimiaug.frustration.R;

public class WelcomeController {
    private final View mView;
    private final Fragment mFragment;

    public WelcomeController(View view, Fragment fragment) {
        mView = view;
        this.mFragment = fragment;
    }

    public void setListeners() {
        mView.findViewById(R.id.start_game).setOnClickListener(
                view1 -> onStartGameClicked()
        );
    }

    private void onStartGameClicked() {
        NavHostFragment.findNavController(mFragment)
                .navigate(R.id.action_welcome_fragment_to_game_table_fragment);
    }
}
