package com.dimiaug.frustration.features.welcome.ui.java.controllers

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.dimiaug.frustration.R
import com.dimiaug.frustration.main.NewMainActivity

class WelcomeController(private val mView: View, private val mFragment: Fragment) {
    fun setListeners() {
        mView.findViewById<View>(R.id.start_game)
            .setOnClickListener { view1: View? -> onStartGameClicked() }
    }

    private fun onStartGameClicked() {
        mFragment.startActivity(
            Intent(
                mFragment.activity,
                NewMainActivity::class.java
            )
        )
        //        NavHostFragment.findNavController(mFragment)
//                .navigate(R.id.action_welcome_fragment_to_game_table_fragment);
    }
}