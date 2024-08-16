package com.github.lexleontiev.chatexample.feature.chat

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class ChatScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<ChatActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun app_displays_list_of_items() {
        composeTestRule.onNodeWithTag("ChatAppBar").assertIsDisplayed()
        composeTestRule.waitUntilExactlyOneExists(
            matcher = hasTestTag("MessageInput_input_field"),
            timeoutMillis = 3000
        )
        composeTestRule.onAllNodesWithTag("MessageItem").assertCountEquals(6)

    }
}