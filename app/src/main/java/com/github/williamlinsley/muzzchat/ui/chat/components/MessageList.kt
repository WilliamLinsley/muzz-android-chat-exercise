package com.github.williamlinsley.muzzchat.ui.chat.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.williamlinsley.muzzchat.domain.model.Message

@Composable
fun MessageList(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    LazyColumn() { }
}