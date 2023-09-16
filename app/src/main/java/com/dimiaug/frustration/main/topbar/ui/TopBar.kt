package com.dimiaug.frustration.main.topbar.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dimiaug.frustration.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FrustrationTopBar(optionsMenu: @Composable () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(id = R.string.app_name),
            )
        },
        actions = { optionsMenu() }
    )
}