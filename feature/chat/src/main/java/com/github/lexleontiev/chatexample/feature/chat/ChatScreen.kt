package com.github.lexleontiev.chatexample.feature.chat

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
import com.github.lexleontiev.chatexample.feature.chat.components.AnimationConstants.MESSAGE_LIST_ANIM_DURATION_MS
import com.github.lexleontiev.chatexample.feature.chat.components.LoadingCircle
import com.github.lexleontiev.chatexample.feature.chat.components.MessageInput
import com.github.lexleontiev.chatexample.feature.chat.components.MessageList
import com.github.lexleontiev.chatexample.feature.chat.components.ScreenStub
import kotlinx.coroutines.launch


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
                // Update this only when the condition changes.
                derivedStateOf {
                    val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo
                        .firstOrNull()?.index ?: Int.MAX_VALUE
                    // If you see the last two messages, the new message triggers scrolling to the
                    // latest one
                    lastVisibleIndex <= 2
                }
            }
            // Check every time the number of messages changes
            LaunchedEffect(messages.size) {
                if (isAtBottom) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            }
            // Start enter animation just once
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
                    primaryText = "A network error has occurred",
                    secondaryText = "Waiting for connection...",
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