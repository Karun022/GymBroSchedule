package com.example.gymbroschedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

@Composable
fun TypingAnimation(text: String, typingSpeed: Long = 1000) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        while (true) { // Loop forever
            displayedText = "Hello "
            for (char in text) {
                displayedText += char
                delay(typingSpeed)
            }
            // Optionally, add a delay before restarting the animation
            delay(1000) // Adjust the pause duration before restarting
        }
    }

    Text(text = displayedText , fontSize = 18.sp)
}

@Composable
fun HomeAppBarView(title: String) {
    TopAppBar(
        modifier = Modifier
            .height(100.dp)
            .clip(shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp))
            .zIndex(1f),
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.home_app_bar),

    ) {
        val diplomaFont = FontFamily(Font(R.font.diploma))

        // Wrap the Text in a Box to align it properly
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.AccountCircle , contentDescription = "Account")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, ),
            contentAlignment = Alignment.CenterStart
        ) {




               TypingAnimation(
                text = title,
                typingSpeed = 200 // Adjust the speed as needed
               )




        }
    }
}