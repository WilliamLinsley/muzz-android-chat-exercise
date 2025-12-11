package com.github.williamlinsley.muzzchat.ui.chat

import com.github.williamlinsley.muzzchat.domain.model.Message

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val inputText: String = "",
    val autoReplyEnabled: Boolean = true
)
