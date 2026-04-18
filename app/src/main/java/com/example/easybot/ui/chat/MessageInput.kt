package com.example.easybot.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easybot.ui.theme.ColorModelMessage
import com.example.easybot.ui.theme.ColorUserMessage

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
                textStyle = TextStyle(color = ColorModelMessage),
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