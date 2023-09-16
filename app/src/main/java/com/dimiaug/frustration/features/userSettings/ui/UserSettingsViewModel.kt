package com.dimiaug.frustration.features.userSettings.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dimiaug.frustration.features.userSettings.domain.useCases.GetSettingsUseCase

class UserSettingsViewModel(
    getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    var showUserSettingsDialog by mutableStateOf(true)
        private set

    val userSettings = getSettingsUseCase.execute()

    fun toggleSettingsDialog() {
        showUserSettingsDialog = !showUserSettingsDialog
    }
}
