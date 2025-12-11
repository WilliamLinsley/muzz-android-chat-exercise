package com.github.williamlinsley.muzzchat.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.williamlinsley.muzzchat.ui.chat.components.MessageInputBar
import com.github.williamlinsley.muzzchat.ui.chat.components.MessageList
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    viewModel: ChatViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Scroll to bottom when messages change
    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(uiState.messages.lastIndex)
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Message list takes all available space
            MessageList(
                messages = uiState.messages,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
            )

            // Input bar at bottom
            MessageInputBar(
                inputText = uiState.inputText,
                onInputChanged = viewModel::onInputChanged,
                onSendClicked = viewModel::sendMessage,
                modifier = Modifier
            )
        }
    }
}
