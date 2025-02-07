package com.example.gymbroschedule.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import com.example.gymbroschedule.R
import com.example.gymbroschedule.Screens.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.ExerciseListView,
        Screen.WeightRecord,
        Screen.MySchedule
    )

    Box {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.home_app_bar),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(screen.title) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Gray
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -32.dp) // Adjust positioning above BottomNavigation bar
                .size(64.dp) // Ensure button matches size in the example
                .clip(CircleShape)
                .background(Color.Black) // Button background color (matches the image)
                .shadow(8.dp, CircleShape) // Add shadow for elevation
        ) {

            IconButton(
                modifier = Modifier.align(Alignment.Center)
                ,onClick = { navController.navigate(Screen.AddScreen.route + "/0L")}) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(32.dp)
                    .clip(RoundedCornerShape(180.dp))
                    .background(colorResource(id = R.color.floating_button_green)), // Adjust icon size to match the image
                tint = Color.White // Icon color
            )
            }
        }
    }
}