package com.example.easybot

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class ChatViewModel : ViewModel() {
    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-pro",
        apiKey = Constants.apiKey
    )

    @SuppressLint("NewApi")
    fun sendMessage(question : String){
        if (question.isBlank()) {
            messageList.add(MessageModel("Error: Message cannot be empty.", "model", getCurrentTimestamp()))
            return
        }
        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }.toList()
                )

                messageList.add(MessageModel(question, "user", getCurrentTimestamp()))
                messageList.add(MessageModel("Typing...", "model", getCurrentTimestamp()))
                val response = chat.sendMessage(question)
                messageList.removeLast()
                messageList.add(MessageModel(response.text.toString(), "model", getCurrentTimestamp()))
            } catch (e: Exception){
                messageList.removeLast()
                messageList.add(MessageModel("Error: " + e.message.toString(), "model", getCurrentTimestamp()))
            }
        }
    }

    private fun getCurrentTimestamp() : String {
        val getCurrent = SimpleDateFormat("hh : mm a", java.util.Locale.getDefault())
        return getCurrent.format(Date())
    }
}