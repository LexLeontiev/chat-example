package com.github.lexleontiev.chatexample.app.ui.chat

import com.github.lexleontiev.chatexample.library.android.Message
import org.junit.Test

class AddTimeSectionTest {

    @Test
    fun `messages without time spacing`() {
        val hourMs = 1 * 60 * 60 * 1000
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - hourMs,
            isSentByUser = true
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = false
        val actual = addTimeSection(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `messages with time spacing`() {
        val hourMs = 1 * 60 * 60 * 1000
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - hourMs - 1,
            isSentByUser = true
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = true
        val actual = addTimeSection(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `first message with time spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg: Message? = null
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = true
        val actual = addTimeSection(msg, prevMsg)
        assert(expected == actual)
    }


}