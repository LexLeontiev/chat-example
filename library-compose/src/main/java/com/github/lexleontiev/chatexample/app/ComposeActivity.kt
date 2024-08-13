package com.github.lexleontiev.chatexample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.lexleontiev.chatexample.app.ui.components.ChatScreen
import com.github.lexleontiev.chatexample.app.ui.components.Message
import com.github.lexleontiev.chatexample.app.ui.components.Message.Companion.mockList

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

@Preview
@Composable
fun AppMain() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Sarah") },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            val messages = remember { mutableStateOf(mutableListOf<Message>().apply {
                addAll(mockList())
            }) }
            ChatScreen(
                messages = messages,
                onMessageSend = {
                    val newMessage = Message(
                        content = it,
                        timestamp = System.currentTimeMillis(),
                        isSentByUser = true
                    )
                    messages.value.add(newMessage)
                }
            )
        }
    }
}
