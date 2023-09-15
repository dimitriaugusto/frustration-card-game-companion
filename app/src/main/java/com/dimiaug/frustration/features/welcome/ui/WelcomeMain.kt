package com.dimiaug.frustration.features.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimiaug.frustration.R
import com.dimiaug.frustration.common.ui.AnnouncementText
import com.dimiaug.frustration.common.ui.BasicButton
import org.koin.compose.koinInject

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinInject()
) {
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
            onClickAction = { viewModel.onStartGameClicked() })
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeEntrypointPreview() {
    WelcomeScreen()
}