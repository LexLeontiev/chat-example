package com.github.lexleontiev.chatexample.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews
import com.github.lexleontiev.chatexample.library.Message
import com.github.lexleontiev.chatexample.library.Message.Companion.mockList


@Composable
internal fun ChatScreen(
    messages: State<List<Message>>,
    onMessageSend: (String) -> Unit
) {
    var input by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        MessageList(messages = messages.value, modifier = Modifier.weight(1f))

        MessageInput(
            input = input,
            onInputChange = { input = it },
            onSendClick = {
                onMessageSend(input.text)
                input = TextFieldValue("")
            }
        )
    }
}

@ThemePreviews
@Composable
private fun ChatScreenPreview() = MaterialTheme {
    val messages = remember { mutableStateOf(mutableListOf<Message>().apply {
        addAll(mockList())
    }) }
    ChatScreen(
        messages = messages,
        onMessageSend = {
            val newMessage = Message.new(
                text = it,
                isSentByUser = true
            )
            messages.value.add(newMessage)
        }
    )
}