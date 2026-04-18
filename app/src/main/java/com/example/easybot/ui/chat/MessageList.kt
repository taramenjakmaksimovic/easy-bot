package com.example.easybot.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easybot.R
import com.example.easybot.domain.model.MessageModel
import com.example.easybot.ui.theme.ColorModelMessage

@Composable
fun MessageList(modifier: Modifier=Modifier, messageList : List<MessageModel>) {
    if(messageList.isEmpty()) {
        Column(
            modifier=modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement=Arrangement.Center
        ) {
            Icon(painter = painterResource(
                id = R.drawable.baseline_question_answer_24),
                modifier = Modifier.size(60.dp),
                contentDescription = "Icon",
                tint = ColorModelMessage)
            Text("Ask me anything", fontSize = 22.sp, color = ColorModelMessage)
        }
    } else {
        LazyColumn(
            reverseLayout = true,
            modifier = modifier
        ) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)


            }
        }
    }
}