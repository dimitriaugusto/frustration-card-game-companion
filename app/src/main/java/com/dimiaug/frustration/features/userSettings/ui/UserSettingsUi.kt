package com.dimiaug.frustration.features.userSettings.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.dimiaug.frustration.common.ui.SimpleDialog
import org.koin.compose.koinInject

@Composable
fun UserSettingsUi(
    viewModel: UserSettingsViewModel = koinInject()
) {
    if (viewModel.showUserSettingsDialog) {
        SettingsDialog(
            { viewModel.toggleSettingsDialog() },
        )
    }
}

@Composable
fun SettingsDialog(
    onDismissRequest: () -> Unit, viewModel: UserSettingsViewModel = koinInject()
) {
    var userSettingsString by remember {
        mutableStateOf("Loading...")
    }
    viewModel.userSettings.observe(LocalLifecycleOwner.current) {
        userSettingsString = it.toString()
    }
    SimpleDialog(onDismissRequest = onDismissRequest, text = userSettingsString)
}
