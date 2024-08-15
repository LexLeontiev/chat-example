package com.github.lexleontiev.chatexample.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lexleontiev.chatexample.data.Message
import com.github.lexleontiev.chatexample.data.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Progress)
    val uiState: StateFlow<ScreenState> = _uiState

    init {
        _uiState.value = ScreenState.Progress
        viewModelScope.launch {
            delay(2000) // imitate network call
            chatRepo.getMessages()
                .collect { messages ->
                    _uiState.value = ScreenState.Result(ChatData(messages))
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
