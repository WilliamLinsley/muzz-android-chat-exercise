package com.github.williamlinsley.muzzchat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.williamlinsley.muzzchat.data.repository.MessageRepository
import com.github.williamlinsley.muzzchat.domain.model.Message
import com.github.williamlinsley.muzzchat.domain.model.Sender
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import kotlin.random.Random

class ChatViewModel(
    private val repository: MessageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        observeMessages()
    }

    private fun observeMessages() {
        viewModelScope.launch {
            repository.observeMessages().collect { messages ->
                _uiState.update { it.copy(messages = messages) }
            }
        }
    }

    fun onInputChanged(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun sendMessage() {
        val content = _uiState.value.inputText.trim()
        if (content.isEmpty()) return

        // Create my outgoing message
        val outgoing = Message(
            text = content,
            sender = Sender.ME,
            timestamp = Instant.now()
        )

        viewModelScope.launch {
            repository.addMessage(outgoing)
        }

        // Clear input field
        _uiState.update { it.copy(inputText = "") }

        // Trigger response if toggle on
        if (_uiState.value.autoReplyEnabled) {
            autoReply()
        }
    }

    private fun autoReply() {
        viewModelScope.launch {
            // Small delay, simulate human response
            delay(Random.nextLong(1500, 3000))

            // Replies
            val cannedReplies = listOf(
                "It's great to hear from you!",
                "Really? I had no idea!",
                "Well, since you put it that way...",
                "I'm going to have to think about that one.",
                "Sure, why not?",
                "That would be an ecumenical matter.",
                "Yes."
            )

            // Randomise response
            val replyText = cannedReplies.random()

            val reply = Message(
                text = replyText,
                sender = Sender.OTHER,
                timestamp = Instant.now()
            )

            repository.addMessage(reply)
        }
    }

    fun setAutoReplyEnabled(enabled: Boolean) {
        _uiState.update { it.copy(autoReplyEnabled = enabled) }
    }


}
