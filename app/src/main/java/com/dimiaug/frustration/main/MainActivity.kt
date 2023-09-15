package com.dimiaug.frustration.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.dimiaug.frustration.common.ui.theme.FrustrationTheme
import com.dimiaug.frustration.features.welcome.ui.WelcomeScreen

class NewMainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrustrationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Frus app") },
                            navigationIcon = { },
                        )
                             },
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        WelcomeScreen()
                    }
                }
            }
        }
    }
}