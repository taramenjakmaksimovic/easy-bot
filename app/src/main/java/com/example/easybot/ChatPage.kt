package com.example.easybot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easybot.ui.theme.ColorModelMessage
import com.example.easybot.ui.theme.ColorUserMessage


@Composable
fun ChatPage(modifier: Modifier=Modifier, viewModel: ChatViewModel){
    Column(
        modifier = modifier.background(Color.White)
    ) {
        AppHeader()
        MessageList(modifier=Modifier.weight(1f), messageList = viewModel.messageList)
        MessageInput(
            onMessageSend = {
                viewModel.sendMessage(it)
            }
        )
    }
}
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
@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.align(
                    if (isModel) Alignment.BottomStart else Alignment.BottomEnd
                ).padding(
                    start = if (isModel) 8.dp else 70.dp,
                    end = if (isModel) 70.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ).clip(RoundedCornerShape(48f))
                    .background(if (isModel) ColorModelMessage else ColorUserMessage)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SelectionContainer {
                        Text(
                            text = messageModel.message,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End ) {
                        Text(
                            text = messageModel.timestamp,
                            fontSize = 10.sp,
                            color = Color.LightGray,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MessageInput(onMessageSend: (String)->Unit) {
    var message by remember {
        mutableStateOf("")
    }
    var showError by remember {
        mutableStateOf(false)
    }
    val focusRequester = remember { FocusRequester() }
    Column {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f).focusRequester(focusRequester),
                value = message,
                onValueChange = {
                    message = it
                    showError = false
                },
                isError = showError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ColorModelMessage,
                    unfocusedBorderColor = ColorUserMessage,
                    cursorColor = ColorUserMessage,
                    focusedLabelColor = ColorModelMessage,
                    unfocusedLabelColor = ColorUserMessage
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if(message.isNotEmpty()) {
                            onMessageSend(message)
                            message=""
                        } else {
                            showError = true
                        }
                    }
                )
            )
            IconButton(onClick = {
                if (message.isNotEmpty()) {
                    onMessageSend(message)
                    message = ""
                }
                else {
                    showError=true
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = ColorUserMessage
                )
            }
        }
        if (showError) {
            Text(
                text = "Message cannot be empty!",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                fontWeight = FontWeight.W500
            )
        }

    }
}

@Composable
fun AppHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF624793)),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.padding(16.dp),
            text= "Easy Bot",
            color = Color.White,
            fontSize =  22.sp
        )
    }
}