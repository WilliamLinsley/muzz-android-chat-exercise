# Muzz Android Chat Exercise

This project is my implementation of the Muzz Android chat UI exercise.  
It’s a small Compose-based app that simulates a chat conversation with locally stored messages.

## How to run
- Open the project in Android Studio (Giraffe or newer)
- Let Gradle sync
- Build and run on any device/emulator running API 24+

No additional setup is required.

## Tech used
- Kotlin
- Jetpack Compose (Material 3)
- ViewModel + StateFlow
- Coroutines
- Room (local persistence)

## Architecture
The app follows a simple structure:

- **UI layer (Compose)**  
  `ChatScreen` observes a `ChatViewModel` and renders the message list and input bar.
- **ViewModel**  
  Holds `ChatUiState`, sends messages, handles auto-replies, and listens to stored messages.
- **Data layer**  
  Room database → DAO → Repository → ViewModel.
- **Dependency injection**  
  Done manually in `MainActivity` using a small ViewModel factory.  
  This avoids adding Hilt/Koin for a one-screen app.

## Notes on the implementation
- Messages are grouped when the same sender sends them within 20 seconds.
- Timestamp headers appear when more than one hour has passed between messages.
- A toggle is included to enable/disable auto-replies (useful for testing grouping).
- Messages persist locally via Room.

## If I had more time
I would look at adding UI tests and unit tests, improving bubble styling and overall UI.
