package com.github.williamlinsley.muzzchat.ui.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.williamlinsley.muzzchat.domain.model.Message

@Composable
fun MessageBubble(
    message: Message,
    isMine: Boolean
) {
    // Different colour for send/receive
    val bubbleColor = if (isMine) MaterialTheme.colorScheme.primary else Color(0xFFE8E8E8)
    val textColor = if (isMine) Color.White else Color.Black

    Surface(
        color = bubbleColor,
        shape = RoundedCornerShape(14.dp),
        tonalElevation = 1.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            androidx.compose.material3.Text(
                text = message.text,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
