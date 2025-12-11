package com.github.williamlinsley.muzzchat.ui.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.williamlinsley.muzzchat.domain.model.Message
import java.time.Duration

@Composable
fun MessageList(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 8.dp)
    ) {
        itemsIndexed(messages) { index, message ->

            val previous = messages.getOrNull(index - 1)

            // Only show header if time elapsed > 1 hour
            val showHeader =
                previous == null ||
                        Duration.between(previous.timestamp, message.timestamp).toHours() >= 1

            // Group together if time elapsed <= 20 seconds
            val isTightGroup =
                previous != null &&
                        previous.sender == message.sender &&
                        Duration.between(previous.timestamp, message.timestamp).seconds <= 20

            Column(modifier = Modifier.fillMaxSize()) {

                if (showHeader) {
                    TimestampHeader(message.timestamp)
                }

                Column(modifier = Modifier.padding(top = if (isTightGroup) 4.dp else 12.dp)) {
                    MessageRow(message)
                }
            }
        }
    }
}
