package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews


@Suppress("LongMethod")
@Composable
internal fun MessageInput(onSend: (String) -> Unit) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var isFocused by remember { mutableStateOf(false) }
    val hasText by remember {
        derivedStateOf {
            textState.text.isNotEmpty()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RectangleShape)
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = if (isFocused) ChatColors.purple else ChatColors.mediumGray,
                    shape = RoundedCornerShape(24.dp)
                )
                .clip(RoundedCornerShape(24.dp))
                .heightIn(min = 48.dp, max = 150.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .testTag("MessageInput_input_field"),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(48.dp)
                .alpha(if (hasText) 1f else 0.5f)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ChatColors.orange, ChatColors.purple)
                    )
                )
                .clickable(hasText) {
                    onSend(textState.text)
                    textState = TextFieldValue("")
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }
}

@ThemePreviews
@Composable
private fun MessageInputPreview() = MaterialTheme {
    MessageInput {}
}
