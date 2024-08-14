package com.github.lexleontiev.chatexample.app.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews
import kotlinx.coroutines.launch
import kotlin.math.max


@Composable
internal fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    // don't collect data between onStop and onStart states
    val chatData by viewModel.chatData.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val isAtBottom by remember {
        derivedStateOf {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleIndex >= chatData.messages.size - 2
        }
    }


    LaunchedEffect(chatData.messages.size) {
        if (isAtBottom) {
            coroutineScope.launch {
                listState.animateScrollToItem(max(0, chatData.messages.size - 1))
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        MessageList(
            messages = chatData.messages,
            listState = listState,
            modifier = Modifier.weight(1f)
        )
        MessageInput {
            viewModel.sendMessage(it)
        }
    }
}

@ThemePreviews
@Composable
private fun ChatScreenPreview() = MaterialTheme {
    ChatScreen()
}