package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews
import com.github.lexleontiev.chatexample.feature.chat.components.AnimationConstants.STUB_ANIM_DURATION_MS


@Composable
internal fun ScreenStub(
    primaryText: String,
    secondaryText: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var visible by remember { mutableStateOf(false) }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = tween(STUB_ANIM_DURATION_MS)
            )
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.width(320.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    text = primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.width(320.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Normal,
                    text = secondaryText
                )
            }
        }
        LaunchedEffect(key1 = Unit) {
            visible = true
        }
    }
}

@ThemePreviews
@Composable
private fun ScreenStubPreview() = MaterialTheme {
    ScreenStub(
        primaryText = "Text primary",
        secondaryText = "Text secondary",
    )
}