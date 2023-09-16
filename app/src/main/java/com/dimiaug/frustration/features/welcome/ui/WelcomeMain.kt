package com.dimiaug.frustration.features.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dimiaug.frustration.R
import com.dimiaug.frustration.common.ui.AnnouncementText
import com.dimiaug.frustration.common.ui.BasicButton
import com.dimiaug.frustration.common.ui.SimpleDialog
import org.koin.compose.koinInject

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinInject()
) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {}
        composable("play") { DummyPlayScreen() }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnnouncementText(
            modifier = Modifier.padding(vertical = 10.dp),
            text = stringResource(id = R.string.welcome_text),
        )
        BasicButton(
            text = stringResource(id = R.string.start_game),
            onClickAction = { navController.navigate("play") }
        )
    }
}

@Composable
fun DummyPlayScreen() {
    var showPlayDialog by remember { mutableStateOf(true) }
    if (showPlayDialog) {
        SimpleDialog(
            onDismissRequest = { showPlayDialog = !showPlayDialog },
            text = "TODO implement play screen",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeEntrypointPreview() {
    WelcomeScreen()
}