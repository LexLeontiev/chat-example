package com.github.lexleontiev.chatexample.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.lexleontiev.chatexample.app.ui.components.ChatScreen
import com.github.lexleontiev.chatexample.library.Message


class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
            backgroundColor = MaterialTheme.colors.background,
        ) {
            val messages = remember { mutableStateOf(mutableListOf<Message>().apply {
                addAll(Message.mockList())
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
    }
}
