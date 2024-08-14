package com.github.lexleontiev.chatexample.app.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews
import com.github.lexleontiev.chatexample.library.Message


@Composable
internal fun MessageItem(message: Message) {
    val backgroundColor = if (message.isSentByUser) Color(0xFFFF4081) else Color(0xFFE0E0E0)
    val alignment = if (message.isSentByUser) Alignment.End else Alignment.Start
    val textColor = if (message.isSentByUser) Color.White else Color.Black
    val paddingStart = if (message.isSentByUser) 48.dp else 8.dp
    val paddingEnd = if (message.isSentByUser) 8.dp else 48.dp
    val r = 24.dp
    val shape = if (message.isSentByUser) RoundedCornerShape(r, r, 0.dp, r) else RoundedCornerShape(r, r, r, 0.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingStart, end = paddingEnd),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = alignment
        ) {
            Text(
                text = message.content,
                color = textColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .clip(shape)
                    .background(backgroundColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun MessageItemPreview() = MaterialTheme {
    Column {
        MessageItem(message = Message.mock(true))
        Spacer(modifier = Modifier.height(16.dp))
        MessageItem(message = Message.mock(false))
    }
}