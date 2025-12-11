package com.github.williamlinsley.muzzchat.domain.model

import java.time.Instant

data class Message(
    val id: Int = 0,
    val text: String,
    val sender: Sender,
    val timestamp: Instant
)
