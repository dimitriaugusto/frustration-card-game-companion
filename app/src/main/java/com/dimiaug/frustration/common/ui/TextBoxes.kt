package com.dimiaug.frustration.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dimiaug.frustration.common.ui.theme.AnnouncementHPad
import com.dimiaug.frustration.common.ui.theme.AnnouncementVPad

@Composable
fun AnnouncementText(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = AnnouncementHPad)
            .padding(vertical = AnnouncementVPad),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
