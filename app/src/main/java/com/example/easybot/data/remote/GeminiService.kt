package com.example.easybot.data.remote

import com.example.easybot.domain.model.MessageModel
import com.example.easybot.util.AIConfig
import com.example.easybot.util.Constants
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

class GeminiService {
    private val generativeModel : GenerativeModel = GenerativeModel(
        modelName = AIConfig.MODEL_NAME,
        apiKey = Constants.API_KEY
    )

    suspend fun sendMessage(
        messages: List<MessageModel>,
        question: String
    ): String {
        val chat = generativeModel.startChat(
            history= messages.map {
                content(it.role){ text(it.message) }
            }
        )
        val response = chat.sendMessage(question)
        return  response.text.toString()
    }
}