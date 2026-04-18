package com.example.easybot.domain.repository

import com.example.easybot.domain.model.MessageModel

interface ChatRepository {
    suspend fun sendMessage(
        messages: List<MessageModel>,
        question: String
    ) : String
}