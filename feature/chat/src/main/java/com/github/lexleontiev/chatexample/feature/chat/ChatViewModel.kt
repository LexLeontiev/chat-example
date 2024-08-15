package com.github.lexleontiev.chatexample.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lexleontiev.chatexample.data.Message
import com.github.lexleontiev.chatexample.data.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepository
) : ViewModel() {

    private val _chatData = MutableStateFlow(ChatData.empty())
    val chatData: StateFlow<ChatData> = _chatData

    init {
        viewModelScope.launch {
            chatRepo.getMessages()
                .collect { messages ->
                    _chatData.value = ChatData(messages)
                }
        }
    }

    fun sendMessage(content: String) {
        val message = Message.new(
            text = content,
            isSentByUser = true
        )
        viewModelScope.launch {
            chatRepo.sendMessage(message)
        }
    }

    // use only for debug build
    fun debugReceivingMessage() {
        val message = Message.new(
            text = "Hi there, How you doing?",
            isSentByUser = false
        )
        viewModelScope.launch {
            chatRepo.sendMessage(message)
        }
    }

    // use only for debug build
    fun debugClearChat() {
        viewModelScope.launch {
            chatRepo.removeAllMessages()
        }
    }
}
