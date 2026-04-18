package com.example.easybot.ui.chat

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easybot.domain.model.MessageModel
import com.example.easybot.domain.repository.ChatRepository
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewModel(
    private val repository: ChatRepository
) : ViewModel() {
    val messageList = mutableStateListOf<MessageModel>()

    @SuppressLint("NewApi")
    fun sendMessage(question : String){
        if (question.isBlank()) {
            messageList.add(
                MessageModel(
                    "Error: Message cannot be empty.",
                    "model",
                    getCurrentTimestamp()
                )
            )
            return
        }
        viewModelScope.launch {
            try {
                messageList.add(
                    MessageModel(question, "user", getCurrentTimestamp())
                )
                messageList.add(MessageModel("Typing...", "model", getCurrentTimestamp()))
                val response = repository.sendMessage(messageList, question)
                messageList.removeLast()
                messageList.add(
                    MessageModel(response, "model", getCurrentTimestamp())
                )

            } catch (e: Exception){
                messageList.removeLast()
                messageList.add(
                    MessageModel(
                        "Error: " + e.message.toString(),
                        "model",
                        getCurrentTimestamp()
                    )
                )
            }
        }
    }

    private fun getCurrentTimestamp() : String {
        val getCurrent = SimpleDateFormat("hh : mm a", Locale.getDefault())
        return getCurrent.format(Date())
    }
}