package com.dimiaug.frustration.features.welcome.ui

import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    fun onStartGameClicked() {
        android.util.Log.i("FRUS", "clicled")
    }
}
