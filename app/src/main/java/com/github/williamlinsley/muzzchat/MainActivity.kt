package com.github.williamlinsley.muzzchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.github.williamlinsley.muzzchat.data.local.AppDatabase
import com.github.williamlinsley.muzzchat.data.repository.MessageRepositoryImpl
import com.github.williamlinsley.muzzchat.ui.chat.ChatScreen
import com.github.williamlinsley.muzzchat.ui.chat.ChatViewModel

class MainActivity : ComponentActivity() {

    // Not using a DI framework here (Hilt/Koin) since the app only has one ViewModel.
    // This activity creates the database + repository and passes them into the ViewModel.
    private val viewModel: ChatViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Build Room database once for the activity's lifecycle
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "messages.db"
                ).build()

                // Create repository
                val repo = MessageRepositoryImpl(db.messageDao())

                @Suppress("UNCHECKED_CAST")
                return ChatViewModel(repo) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                ChatScreen(viewModel = viewModel)
            }
        }
    }
}
