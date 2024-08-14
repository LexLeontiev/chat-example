package com.github.lexleontiev.chatexample.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.lexleontiev.chatexample.app.ChatViewModel
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews


@Composable
internal fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    // don't collect data between onStop and onStart states
    val chatData by viewModel.chatData.collectAsStateWithLifecycle()
    var input by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier.fillMaxSize()) {
        MessageList(messages = chatData.messages, modifier = Modifier.weight(1f))

        MessageInput(
            input = input,
            onInputChange = { input = it },
            onSendClick = {
                viewModel.sendMessage(input.text)
                input = TextFieldValue("")
            }
        )
    }
}

@ThemePreviews
@Composable
private fun ChatScreenPreview() = MaterialTheme {
    ChatScreen()
}