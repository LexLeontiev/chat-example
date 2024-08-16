package com.github.lexleontiev.chatexample.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.lexleontiev.chatexample.data.LocalDataSource
import com.github.lexleontiev.chatexample.feature.chat.ChatViewModel
import com.github.lexleontiev.chatexample.feature.chat.R
import com.github.lexleontiev.chatexample.feature.chat.ThemePreviews


@Composable
internal fun ChatAppBar(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        modifier = modifier.fillMaxWidth().testTag("ChatAppBar"),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Sarah",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* no-op */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu",
                    modifier = Modifier.rotate(90f)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        viewModel.debugReceivingMessage()
                    }) {
                    Text("Receive message")
                }
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        viewModel.debugClearChat()
                    }) {
                    Text("Clear chat")
                }
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        // For debug purposes only
                        LocalDataSource.throwFailure = true
                    }) {
                    Text("Emulate data retrieval error")
                }
            }
        },
        backgroundColor = Color.White,
        contentColor = ChatColors.darkGray,
        elevation = 4.dp
    )
}


@ThemePreviews
@Composable
private fun ChatAppBarPreview() = MaterialTheme {
    ChatAppBar()
}