package com.github.williamlinsley.muzzchat.ui.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.williamlinsley.muzzchat.domain.model.Message
import com.github.williamlinsley.muzzchat.domain.model.Sender

@Composable
fun MessageRow(message: Message) {
    val isMine = message.sender == Sender.ME

    // Align text according to sent/received
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        MessageBubble(message = message, isMine = isMine)
    }
}
