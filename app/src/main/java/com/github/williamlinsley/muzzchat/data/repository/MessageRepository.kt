package com.github.williamlinsley.muzzchat.data.repository

import com.github.williamlinsley.muzzchat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun observeMessages(): Flow<List<Message>>
    suspend fun addMessage(message: Message)
}
