package com.dimiaug.frustration.features.welcome.entrypoints;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dimiaug.frustration.R;

public class WelcomeController {
    private Fragment mFragment;

    public void setListeners(View view, Fragment fragment) {
        mFragment = fragment;
        view.findViewById(R.id.start_game).setOnClickListener(view1 -> onStartGameClicked());
    }

    private void onStartGameClicked() {
        NavHostFragment.findNavController(mFragment)
                .navigate(R.id.action_welcome_fragment_to_game_table_fragment);
    }
}
