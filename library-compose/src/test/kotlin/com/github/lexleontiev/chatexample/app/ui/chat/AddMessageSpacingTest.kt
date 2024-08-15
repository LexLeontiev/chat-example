package com.github.lexleontiev.chatexample.app.ui.chat

import com.github.lexleontiev.chatexample.library.android.Message
import org.junit.Test

class AddMessageSpacingTest {

    @Test
    fun `my messages without spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20000,
            isSentByUser = true
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = false
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `my messages with spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20001,
            isSentByUser = true
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = true
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `messages from another user without spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20000,
            isSentByUser = false
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = false
        )
        val expected = false
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `messages from another user with spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20001,
            isSentByUser = false
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = false
        )
        val expected = true
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `messages form different users before threshold`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20000,
            isSentByUser = true
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = false
        )
        val expected = true
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `messages form different users after threshold`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg = Message(
            id = 0,
            content = "prevMsg",
            timestamp = timestamp - 20001,
            isSentByUser = false
        )
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = true
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }

    @Test
    fun `first message without spacing`() {
        val timestamp = System.currentTimeMillis()
        val prevMsg: Message? = null
        val msg = Message(
            id = 1,
            content = "msg",
            timestamp = timestamp,
            isSentByUser = true
        )
        val expected = false
        val actual = addSpacing(msg, prevMsg)
        assert(expected == actual)
    }
}