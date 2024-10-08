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

const val IMITATE_NETWORK_CALL_DELAY_MS = 2000L

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Progress)
    val uiState: StateFlow<ScreenState> = _uiState

    init {
        _uiState.value = ScreenState.Progress
        viewModelScope.launch {
            delay(IMITATE_NETWORK_CALL_DELAY_MS)
            chatRepo.getMessages()
                .collect { result ->
                    result.onSuccess { messages ->
                        val chatData = ChatData(messages)
                        _uiState.value = ScreenState.Result(chatData)
                    }.onFailure { _ ->
                        _uiState.value = ScreenState.Error
                    }
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

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    // use only for debug build
    fun debugReceivingMessage() {
        val message = Message.new(
            text = Message.randomMessageText(),
            isSentByUser = false
        )
        viewModelScope.launch {
            try {
                chatRepo.sendMessage(message)
            } catch (e: Exception) {
               // show error in UI
            }
        }
    }

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    // use only for debug build
    fun debugClearChat() {
        viewModelScope.launch {
            try {
                chatRepo.removeAllMessages()
            } catch (e: Exception) {
                // show error in UI
            }

        }
    }
}
