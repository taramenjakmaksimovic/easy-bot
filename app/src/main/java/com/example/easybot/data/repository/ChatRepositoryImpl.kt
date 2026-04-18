package com.example.easybot.data.repository

import com.example.easybot.data.remote.GeminiService
import com.example.easybot.domain.model.MessageModel
import com.example.easybot.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val service : GeminiService = GeminiService()
) : ChatRepository {
    override suspend fun sendMessage(
        messages: List<MessageModel>,
        question: String
    ): String {
        return service.sendMessage(messages,question)
    }
}