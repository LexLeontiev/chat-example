package com.github.lexleontiev.chatexample.feature.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.lexleontiev.chatexample.feature.chat.components.ChatAppBar
import com.github.lexleontiev.chatexample.feature.chat.components.ChatScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
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
                ChatAppBar(modifier = Modifier.statusBarsPadding())
            }
        ) { paddingValues ->
            ChatScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .navigationBarsPadding()
            )
        }
    }
}
