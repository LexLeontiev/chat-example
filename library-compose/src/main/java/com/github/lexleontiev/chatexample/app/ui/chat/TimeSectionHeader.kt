package com.github.lexleontiev.chatexample.app.ui.chat

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lexleontiev.chatexample.app.ui.ThemePreviews
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


val dayFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE")
val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timestamp),
        ZoneId.systemDefault()
    )
}

@Composable
internal fun TimeSectionHeader(timestamp: Long) {
    val localDateTime = remember(timestamp) {
        timestampToLocalDateTime(timestamp)
    }
    val day = localDateTime.format(dayFormatter)
    val time = localDateTime.format(timeFormatter)

    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(day)
            }
            append(" $time")
        },
        color = Color.Gray,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@ThemePreviews
@Composable
private fun TimeSectionHeaderPreview() = MaterialTheme {
    TimeSectionHeader(System.currentTimeMillis())
}