package com.dimiaug.frustration.main.optionsMenu.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dimiaug.frustration.R
import com.dimiaug.frustration.common.ui.SimpleDialog
import com.dimiaug.frustration.features.userSettings.ui.UserSettingsUi
import org.koin.compose.koinInject

@Composable
fun OptionsMenu(
    viewModel: OptionsMenuViewModel = koinInject()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "optionsMenu") {
        composable("optionsMenu") {}
        composable("userSettings") { UserSettingsUi() }
    }

    if (viewModel.showAboutDialog) {
        AboutDialog(onDismissRequest = { viewModel.toggleAboutDialog() })
    }

    IconButton(onClick = { viewModel.toggleOptionsMenu() }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
    DropdownMenu(
        expanded = viewModel.showMenu,
        onDismissRequest = { viewModel.toggleOptionsMenu() },
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.action_settings)) },
            onClick = {
                viewModel.toggleOptionsMenu()
                navController.navigate("userSettings")
            },
        )
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.action_about)) },
            onClick = {
                viewModel.toggleOptionsMenu()
                viewModel.toggleAboutDialog()
            })
    }
}

@Composable
fun AboutDialog(onDismissRequest: () -> Unit) {
    SimpleDialog(onDismissRequest, "TODO print version number here")
}

