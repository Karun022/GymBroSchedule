package com.example.gymbroschedule


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp




@Composable
fun AppBarView(
    title: String,
    onBackNavClicked:()->Unit ={}
){
    val navigationIcon:(@Composable () ->Unit)?=
        if (!title.contains("GymBroSchedule")){
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(title ={
        val diplomaFont = FontFamily(Font(R.font.diploma))
        Text(text=title,
            color = colorResource(id = R.color.Text),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp),
            fontFamily = diplomaFont
        )

    },
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp))
            .height(70.dp),
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )
}
