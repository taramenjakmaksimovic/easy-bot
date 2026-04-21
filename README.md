# 💬 Easy Bot

**Easy Bot** is a simple AI powered Android application that uses Google's Gemini language model to answer user questions.

---

## 📹 Demo

https://github.com/user-attachments/assets/66df5924-1833-4c5b-bcf8-ab969e7a6695





---

## 🚀 Getting started  

### Prerequisites  
- [Android Studio](https://developer.android.com/studio)
- Android SDK version 26 or higher
- Your own [API key](https://aistudio.google.com/app/apikey)

### Installation  

1. **Clone the Repository**:
   ```bash  
   git clone https://github.com/taramenjakmaksimovic/easy-bot.git
2. **Open in Android Studio**: <br>
Select "Open an Existing Project" and navigate to the cloned directory.
3. **Set your API key**: <br>
Navigate to `app/src/main/java/com/example/easybot/util/Constants.kt` and replace `"YOUR_API_KEY"` with your own API key.
4. **Run the app**: <br>
Select your emulator or connected device and click "Run" button.
---
## 🧠 AI integration

The app uses the Gemini API via Google AI Client SDK.

### How it works:
1. User enters a message
2. Message is passed to ChatViewModel
3. ViewModel sends request to ChatRepository
4. Repository calls GeminiService
5. Gemini API returns a generated response
6. Response is displayed in the UI

---

## 🏠 Architecture
The app follows MVVM (Model-View-ViewModel) architecture:

- Model: MessageModel (data representation of a chat message)
- View: Jetpack Compose UI (ChatPage, MessageList, MessageRow, MessageInput)
- ViewModel: ChatViewModel (manages UI state and business logic)
- Repository: ChatRepository (abstracts data source)
- Data source: GeminiService (communicates with Gemini API)
---

## 💡 Design decisions

- MVVM is used to separate UI and business logic
- Repository pattern is used to abstract AI data source
- State is managed using Compose state APIs
- Asynchronous operations are managed with Kotlin coroutines (viewModelScope)
- Custom ViewModelFactory is used to inject dependencies into ViewModel
- Input is disabled while waiting for response to avoid duplicate API calls
- Errors are handled and displayed in the chat UI
---

## 👾 Technologies
- Kotlin
- Jetpack Compose
- [Google AI client SDK](https://developer.android.com/ai/google-ai-client-sdk)
- [Android Studio](https://developer.android.com/studio)
