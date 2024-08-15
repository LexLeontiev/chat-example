package com.github.lexleontiev.chatexample.feature.chat


sealed class ScreenState {
    data class Result(val data: ChatData): ScreenState()
    data object Progress: ScreenState()
    data object Error: ScreenState()
}