package com.github.lexleontiev.chatexample.app.ui.chat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews
import com.github.lexleontiev.chatexample.library.Message
import com.github.lexleontiev.chatexample.library.Message.Companion.mockList


internal const val TIME_BETWEEN_MSG_WITHOUT_SPACING_MS = 20 * 1000

internal fun addSpacing(msg: Message, prevMsg: Message?): Boolean {
    if (prevMsg == null) return true
    val sameUser = prevMsg.isSentByUser == msg.isSentByUser
    val exceedThreshold = msg.timestamp - prevMsg.timestamp > TIME_BETWEEN_MSG_WITHOUT_SPACING_MS
    return exceedThreshold || !sameUser
}

@Composable
internal fun MessageList(
    messages: List<Message>,
    listState: LazyListState,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(messages.size) { index ->
            val message = messages[index]
            val previousMessage = messages.getOrNull(index - 1)
            MessageItem(
                message = message,
                addSpacing = addSpacing(message, previousMessage)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun MessageListPreview() = MaterialTheme {
    MessageList(
        messages = mockList(),
        listState = rememberLazyListState()
    )
}