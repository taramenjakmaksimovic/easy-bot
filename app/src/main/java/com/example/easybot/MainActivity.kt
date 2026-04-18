package com.example.easybot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.easybot.data.repository.ChatRepositoryImpl
import com.example.easybot.ui.chat.ChatPage
import com.example.easybot.ui.chat.ChatViewModel
import com.example.easybot.ui.chat.ChatViewModelFactory
import com.example.easybot.ui.theme.EasyBotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ChatRepositoryImpl()
        val factory = ChatViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ChatViewModel::class.java]

        setContent {
            ChatPage(viewModel=viewModel)
        }
    }
}

