package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews
import com.github.lexleontiev.chatexample.feature.chat.components.AnimationConstants.LOADING_ANIM_DURATION_MS


@Composable
fun LoadingCircle(text: String) {
    var visible by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(LOADING_ANIM_DURATION_MS)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(112.dp)
                    .padding(horizontal = 16.dp),
                color = ChatColors.orange,
                strokeWidth = 3.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = text,
                fontSize = 16.sp,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
        }
    }
    LaunchedEffect(key1 = Unit) {
        visible = true
    }
}

@ThemePreviews
@Composable
private fun LoadingCirclePreview() = MaterialTheme {
    LoadingCircle("Chat is loading...")
}

