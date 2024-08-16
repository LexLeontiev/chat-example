package com.github.lexleontiev.chatexample.data


data class Message(
    val id: Int,
    val content: String,
    val timestamp: Long,
    val isSentByUser: Boolean,
    val isRead: Boolean = false
) {

    companion object {

        fun new(isSentByUser: Boolean, text: String): Message = Message(
            id = 0,
            content = text,
            timestamp = System.currentTimeMillis(),
            isSentByUser = isSentByUser
        )

        fun mock(
            isSentByUser: Boolean,
            id: Int = 0,
            text: String? = null,
            timestamp: Long = System.currentTimeMillis()
        ): Message = Message(
            id = id,
            content = text ?: "Test message",
            timestamp = timestamp,
            isSentByUser = isSentByUser
        )

        fun randomMessageText(): String = msgTextMocks.random()

        private val msgTextMocks = listOf(
            "Hi there!",
            "How are you?",
            "What's up?",
            "Nice to meet you!",
            "Tell me more about yourself.",
            "Do you have any hobbies?",
            "What do you like to do for fun?",
            "Where are you from?",
            "What's your favorite movie?",
            "Do you enjoy traveling?",
            "What kind of music do you listen to?",
            "Are you more of a morning or a night person?",
            "What’s your dream job?",
            "Do you have any pets?",
            "What’s your favorite food?",
            "What do you do for work?",
            "Have you read any good books lately?",
            "Do you like cooking?",
            "What’s your favorite way to spend a weekend?",
            "Are you into sports?",
            "Do you have a favorite quote?",
            "What’s something interesting about you?",
            "Do you like coffee or tea?",
            "What’s your favorite season?",
            "Do you enjoy outdoor activities?",
            "Are you a cat or a dog person?",
            "What’s your favorite type of cuisine?",
            "Do you enjoy watching TV shows?",
            "Have you ever been to [insert city]?",
            "What’s your favorite holiday?",
            "Do you like going to the beach?",
            "What’s your favorite way to relax?",
            "Do you have any siblings?",
            "What’s something you’re passionate about?",
            "Do you like to dance?",
            "What’s your favorite type of dessert?",
            "Are you a foodie?",
            "What’s a place you’ve always wanted to visit?",
            "Do you enjoy hiking?",
            "What’s your favorite time of day?",
            "Do you have any goals for this year?",
            "What’s your idea of a perfect date?",
            "Do you play any instruments?",
            "What’s your favorite board game?",
            "Do you enjoy art?",
            "What’s something you’re really good at?",
            "Do you prefer texting or talking on the phone?",
            "What’s your favorite social media platform?",
            "Do you believe in love at first sight?",
            "What’s the most spontaneous thing you’ve ever done?",
        )
    }
}

