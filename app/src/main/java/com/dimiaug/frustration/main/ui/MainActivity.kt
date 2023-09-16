package com.dimiaug.frustration.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dimiaug.frustration.common.ui.theme.FrustrationTheme
import com.dimiaug.frustration.main.optionsMenu.ui.OptionsMenu
import com.dimiaug.frustration.main.topbar.ui.FrustrationTopBar
import com.dimiaug.frustration.features.welcome.ui.WelcomeScreen

class NewMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrustrationTheme {
                FrustrationAppComposable()
            }
        }
    }
}

@Composable
private fun FrustrationAppComposable() {
    Scaffold(
        topBar = {
            FrustrationTopBar {
                OptionsMenu()
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            WelcomeScreen()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    FrustrationTheme {
        FrustrationAppComposable()
    }
}