package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.lexleontiev.chatexample.feature.chat.ChatViewModel
import com.github.lexleontiev.chatexample.feature.chat.ScreenState
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews
import com.github.lexleontiev.chatexample.feature.chat.components.AnimationConstants.MESSAGE_LIST_ANIM_DURATION_MS
import kotlinx.coroutines.launch
import kotlin.math.max


@Composable
internal fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    // don't collect data between onStop and onStart states
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ScreenState.Result -> {
            val messages = state.data.messages
            val coroutineScope = rememberCoroutineScope()
            val listState = rememberLazyListState()
            var visible by remember { mutableStateOf(false) }
            val isAtBottom by remember {
                derivedStateOf {
                    val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    lastVisibleIndex >= messages.size - 2
                }
            }
            LaunchedEffect(messages.size) {
                if (isAtBottom) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(max(0, messages.size - 1))
                    }
                }
            }
            LaunchedEffect(key1 = Unit) {
                visible = true
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(MESSAGE_LIST_ANIM_DURATION_MS)
                )
            ) {
                Column(modifier = modifier.fillMaxSize()) {
                    MessageList(
                        messages = messages,
                        listState = listState,
                        modifier = Modifier.weight(1f)
                    )
                    MessageInput {
                        viewModel.sendMessage(it)
                    }
                }
            }
        }
        is ScreenState.Progress -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingCircle(text = "Chat is loading...")
            }
        }
        is ScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ScreenStub(
                    primaryText = "An error is occurred",
                    secondaryText = "Reload feed"
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun ChatScreenPreview() = MaterialTheme {
    ChatScreen()
}