package com.github.lexleontiev.chatexample.app.ui.chat

import androidx.compose.foundation.layout.Arrangement
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


@Composable
internal fun MessageList(
    messages: List<Message>,
    listState: LazyListState,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(messages.size) { index ->
            MessageItem(message = messages[index])
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