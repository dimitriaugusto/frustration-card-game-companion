package com.dimiaug.frustration.main.optionsMenu.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController

class OptionsMenuViewModel : ViewModel() {

    var showMenu by mutableStateOf(false)
        private set

    var showAboutDialog by mutableStateOf(false)
        private set

    fun toggleOptionsMenu() {
        showMenu = !showMenu
    }

    fun toggleAboutDialog() {
        showAboutDialog = !showAboutDialog
    }
}
