package com.github.williamlinsley.muzzchat.data.repository

import com.github.williamlinsley.muzzchat.data.local.MessageDao
import com.github.williamlinsley.muzzchat.data.local.MessageEntity
import com.github.williamlinsley.muzzchat.domain.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl(
    private val dao: MessageDao
) : MessageRepository {

    override fun observeMessages(): Flow<List<Message>> =
        dao.observeMessages().map { entities ->
            entities.map { entity ->
                Message(
                    id = entity.id,
                    text = entity.text,
                    sender = entity.sender,
                    timestamp = entity.timestamp
                )
            }
        }

    override suspend fun addMessage(message: Message) {
        dao.insert(
            MessageEntity(
                id = message.id,
                text = message.text,
                sender = message.sender,
                timestamp = message.timestamp
            )
        )
    }
}
