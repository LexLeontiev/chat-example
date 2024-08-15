package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews
import com.github.lexleontiev.chatexample.data.Message
import com.github.lexleontiev.chatexample.data.Message.Companion.mock


internal const val MSG_SPACING_DELAY_MS = 20 * 1000 // 20 seconds
internal const val TIME_SECTION_DELAY_MS = 60 * 60 * 1000 // 1 hour

internal fun addSpacing(msg: Message, prevMsg: Message?): Boolean {
    if (prevMsg == null) return false
    val sameUser = prevMsg.isSentByUser == msg.isSentByUser
    val exceedThreshold = msg.timestamp - prevMsg.timestamp > MSG_SPACING_DELAY_MS
    return exceedThreshold || !sameUser
}

internal fun addTimeSection(msg: Message, prevMsg: Message?): Boolean {
    if (prevMsg == null) return true
    return msg.timestamp - prevMsg.timestamp > TIME_SECTION_DELAY_MS
}

@Composable
internal fun MessageList(
    messages: List<Message>,
    listState: LazyListState,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        reverseLayout = true,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(messages.size) { index ->
            val message = messages[index]
            // +1 because the list is reversed
            val previousMessage = messages.getOrNull(index + 1)
            MessageItem(
                message = message,
                addSpacing = addSpacing(message, previousMessage)
            )
            if (addTimeSection(message, previousMessage)) {
                TimeSectionHeader(timestamp = message.timestamp)
            }
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

private fun mockList(): List<Message> {
    val timestamp = System.currentTimeMillis() - 2 * TIME_SECTION_DELAY_MS
    return listOf(
        mock(true, "Hello", timestamp = timestamp),
        mock(true, "How are you?", timestamp = timestamp + 1000),
        mock(false, "Hey hey, I'm fine :)", timestamp = timestamp + 2000),
        mock(false, "What's new?", timestamp = timestamp + TIME_SECTION_DELAY_MS + 3000),
        mock(true, "All good", timestamp + TIME_SECTION_DELAY_MS + 10000),
        mock(true, "I'm alright as well, thank you", timestamp + TIME_SECTION_DELAY_MS + 10000 + MSG_SPACING_DELAY_MS + 1000)
    ).reversed()
}