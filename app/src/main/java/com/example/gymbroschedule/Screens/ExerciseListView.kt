package com.example.gymbroschedule.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.ui.theme.BottomNavigationBar

@Composable
fun ExerciseView(
    navController: NavController,
    viewModel: GymViewModel
){


    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        topBar = { AppBarView(title = "Schedules"){ navController.navigateUp() } },
        floatingActionButton = {
            // Use a Box to position the dropdown menu relative to the FAB
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                // This Box contains the dropdown menu and the FAB
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // Fixed height for the menu area

                    // Use a Box to wrap AnimatedVisibility and avoid ColumnScope
                    AnimatedVisibility(visible = expanded) {
                        Column(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(24.dp))
                                .padding(end = 157.dp, bottom = 10.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(shape = RoundedCornerShape(50.dp)),
                                backgroundColor = Color.Blue
                            ) {
                                androidx.compose.material.IconButton(
                                    onClick = {
                                        expanded = false
                                        navController.navigate(Screen.AddScreen.route + "/0L")
                                    }
                                ) {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.baseline_list_alt_24),
                                        contentDescription = "Add Screen",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Card(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(shape = RoundedCornerShape(50.dp)),
                                backgroundColor = Color.Blue
                            ) {
                                // Option 2: Navigate to Weight Record Screen
                                androidx.compose.material.IconButton(
                                    onClick = {
                                        expanded = false
                                        navController.navigate(Screen.WeightRecord.route)
                                    }
                                ) {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.weightscale),
                                        contentDescription = "Weight Record",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp)) // Space between menu and FAB

//                    FloatingActionButton(
//                        modifier = Modifier.clip(shape = RoundedCornerShape(6.dp)),
//                        onClick = { expanded = !expanded },
//                        backgroundColor = colorResource(id = R.color.floating_button_green)
//                    ) {
//                        Icon(Icons.Default.Add, contentDescription = "Add")
//                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(80.dp)
//                    .background(Color.Black)
//                    .padding(bottom = 28.dp) // Lower the bottom bar for the curve effect
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(Alignment.BottomCenter),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Top // Align to the top for better visibility
//                ) {
//
//                    // First item
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clickable {
//                                selectedIndex = 0
//                                navController.navigate(Screen.HomeScreen.route)
//                            },
//                        contentAlignment = Alignment.TopCenter // Align the content to the top center
//                    ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.SpaceBetween // Space between icon and text
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .size(40.dp)
//                                    .background(
//                                        color = if (selectedIndex == 0) Color.Green else Color.Transparent,
//                                        shape = CircleShape
//                                    ),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                androidx.compose.material3.Icon(
//                                    imageVector = Icons.Default.Home,
//                                    contentDescription = "Home",
//                                    tint = if (selectedIndex == 0) Color.Black else Color.White
//                                )
//                            }
//                            Text("Home", color = if (selectedIndex == 0) Color.Green else Color.White, fontSize = 12.sp) // Set font size to avoid overflow
//                        }
//                    }
//
//                    // Second item
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clickable {
//                                selectedIndex = 1
//                                navController.navigate(Screen.ExerciseListView.route)
//                            },
//                        contentAlignment = Alignment.TopCenter // Align the content to the top center
//                    ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .size(40.dp)
//                                    .background(
//                                        color = if (selectedIndex == 1) Color.Green else Color.Transparent,
//                                        shape = CircleShape
//                                    ),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                androidx.compose.material3.Icon(
//                                    painter = painterResource(id = R.drawable.baseline_list_alt_24),
//                                    contentDescription = "Schedule",
//                                    tint = if (selectedIndex == 1) Color.Black else Color.White
//                                )
//                            }
//                            Text("Schedule", color = if (selectedIndex == 1) Color.Green else Color.White, fontSize = 12.sp)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.weight(1f)) // Space for the central button
//
//                    // Third item
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clickable {
//                                selectedIndex = 2
//                                navController.navigate(Screen.MySchedule.route)
//                            },
//                        contentAlignment = Alignment.TopCenter // Align the content to the top center
//                    ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .size(40.dp)
//                                    .background(
//                                        color = if (selectedIndex == 2) Color.Green else Color.Transparent,
//                                        shape = CircleShape
//                                    ),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                androidx.compose.material3.Icon(
//                                    painter = painterResource(id = R.drawable.baseline_checklist_24),
//                                    contentDescription = "Exercises",
//                                    tint = if (selectedIndex == 2) Color.Black else Color.White
//                                )
//                            }
//                            Text("Exercises", color = if (selectedIndex == 2) Color.Green else Color.White, fontSize = 12.sp)
//                        }
//                    }
//
//                    // Fourth item
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .clickable {
//                                selectedIndex = 3
//                                navController.navigate(Screen.WeightRecord.route)
//                            },
//                        contentAlignment = Alignment.TopCenter // Align the content to the top center
//                    ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .size(40.dp)
//                                    .background(
//                                        color = if (selectedIndex == 3) Color.Green else Color.Transparent,
//                                        shape = CircleShape
//                                    ),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                androidx.compose.material3.Icon(
//                                    modifier = Modifier.size(24.dp),
//                                    painter = painterResource(id = R.drawable.weightscale),
//                                    contentDescription = "Weight",
//                                    tint = if (selectedIndex == 3) Color.Black else Color.White
//                                )
//                            }
//                            Text("Weight", color = if (selectedIndex == 3) Color.Green else Color.White, fontSize = 12.sp)
//                        }
//                    }
//                }
//
//                // Central button positioned above the bottom bar
//                FloatingActionButton(
//                    onClick = {
//                        selectedIndex = -1
//                        navController.navigate(Screen.AddScreen.route + "/0L")
//                        /* Handle Scan & Pay action */ },
//                    modifier = Modifier
//                        .align(Alignment.TopCenter)
//                        .offset(y = -26.dp), // Adjust vertical offset to create overlap
//                    contentColor = Color.White,
//                    backgroundColor = colorResource(id = R.color.floating_button_green),
//                    shape = CircleShape
//                ) {
//                    androidx.compose.material3.Icon(
//                        Icons.Default.Add,
//                        contentDescription = "Add Schedule"
//                    )
//                }
//            }
        },
    ) {

        val gymList = viewModel.getAllInfo.collectAsState(initial = listOf())
        val gyms = remember { mutableStateOf(gymList.value.toMutableList()) }

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ){
            items(gymList.value,key = {gym -> gym.id}){
                    gym ->
                GymItem(gym = gym, navController = navController, viewModel = viewModel, onClick = {
                    val id =gym.id
                    navController.navigate(Screen.AddScreen.route + "/$id")
                })
            }
        }

    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GymItem(gym: Gym,
            onClick:()->Unit,
            navController: NavController,
            viewModel: GymViewModel
){


    var isExpanded by remember { mutableStateOf(false) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },

        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.outter_card),
        onClick = {
            isExpanded = !isExpanded
        },
    )
    {
        Column(
        ) {

            Column(modifier = Modifier
                .padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(text = gym.Day,  color = Color.Black, fontFamily = FontFamily.Default, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                        Row(){
                            Text(
                                text = "${gym.Workout} - Workout",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {

                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier
                                .background(color = colorResource(id = R.color.weight_card))
                                .clip(RoundedCornerShape(12.dp)),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row {
                                        Icon(
                                            modifier = Modifier.padding(bottom = 5.dp),
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = Color.White

                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(text = "Edit")
                                    }
                                },
                                onClick = {
                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
                                    showMenu = false
                                },
                            )

                            DropdownMenuItem(text = {
                                Row {
                                    Icon(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Delete")
                                }
                            },
                                onClick = {
                                    showDialog = true

                                    showMenu = false
                                }
                            )
                        }

                        if (showDialog) {
                            AnimatedVisibility(
                                visible = showDialog,
                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                            ) {
                                AlertDialog(
                                    onDismissRequest = {
                                        showDialog = false
                                    },
                                    title = {
                                        Text(
                                            text = "Confirm Deletion",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp
                                        )
                                    },
                                    text = {
                                        Text(
                                            "Are you sure you want to delete this Schedule?",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                viewModel.deleteAnInfo(gym)
                                                showDialog = false
                                            },
                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)

                                        ) {
                                            Text("Delete")
                                        }
                                    },
                                    dismissButton = {
                                        Button(
                                            onClick = {
                                                showDialog = false
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = colorResource(
                                                    id = R.color.Sky_Blue
                                                )
                                            )

                                        ) {
                                            Text("Cancel")
                                        }
                                    }
                                )
                            }
                        }
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                    if (isExpanded) {
                        Text(text = "Tap to Collapse", color = Color.Black)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Collapse"
                        )
                    } else {
                        Text(text = "Tap to view all Exercises", color = Color.Black)
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
            }


            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec =  tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 100))

            ) {


                var cardSize by remember { mutableStateOf(Size.Zero) } // Size of the card
                var scale by remember { mutableStateOf(1f) } // Current scale, starts at 1 (original size)
                val originalScale = 1f // Original scale
                val originalOffset = Offset(0f, 0f) // Original offset (no drag)

                var offset by remember { mutableStateOf(Offset(0f, 0f)) } // Current drag offset

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .onSizeChanged { size ->
                            // Convert IntSize to Size (Float values)
                            cardSize = Size(size.width.toFloat(), size.height.toFloat())
                        }
                        .graphicsLayer(
                            scaleX = scale,  // Apply the zoom scale
                            scaleY = scale,  // Apply the zoom scale
                            translationX = offset.x,  // Apply translation for drag (X)
                            translationY = offset.y  // Apply translation for drag (Y)
                        )
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { change ->
                                        if (change.isConsumed) return@forEach

                                        // Detect zoom (scale) changes
                                        if (event.changes.size == 2) {
                                            val zoom = event.calculateZoom()
                                            scale = (scale * zoom).coerceIn(
                                                1f,
                                                3f
                                            )  // Limit zoom scale between 1 and 3
                                        }

                                        // Detect dragging (pan) changes
                                        val pan = event.changes
                                            .first()
                                            .positionChange()
                                        offset = Offset(
                                            (offset.x + pan.x).coerceIn(
                                                -cardSize.width.toFloat() * (scale - 1f),
                                                cardSize.width.toFloat() * (scale - 1f)
                                            ),
                                            (offset.y + pan.y).coerceIn(
                                                -cardSize.height.toFloat() * (scale - 1f),
                                                cardSize.height.toFloat() * (scale - 1f)
                                            )
                                        )
                                    }
                                    // Wait for the user to release the touch (gesture end)
                                    event.changes
                                        .firstOrNull { it.pressed.not() }
                                        ?.let {
                                            scale = originalScale // Reset scale
                                            offset = originalOffset // Reset offset
                                        }
                                }
                            }
                        }
                ){
                    // Content inside the card goes here
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 4.dp,
                                end = 4.dp,
                                bottom = 4.dp
                            ), backgroundColor = colorResource(id = R.color.inner_card)
                    ) {


                    Column(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 5.dp,
                            bottom = 8.dp
                        )
                    ) {


                        if (isExpanded) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center){
                                Text(text = "Exercises", fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "1st week",
                                    modifier = Modifier.weight(1f),
                                    fontWeight = FontWeight.SemiBold,
                                    textDecoration = TextDecoration.Underline
                                )
                                if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "2nd week",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.SemiBold,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                                if(gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()){
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "3rd week",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.SemiBold,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. ${gym.Exercise1}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "1. ${gym.Exercise1R2}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                if(gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()){
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "1. ${gym.Exercise1R3}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. ${gym.Exercise2}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "2. ${gym.Exercise2R2}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                if( gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()){
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "2. ${gym.Exercise2R3}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. ${gym.Exercise3}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "3. ${gym.Exercise3R2}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                if(gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()){
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )

                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "3. ${gym.Exercise3R3}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "4. ${gym.Exercise4}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "4. ${gym.Exercise4R2}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                if(gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()){
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )
                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "4. ${gym.Exercise4R3}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                if(gym.Exercise5.isNotEmpty()) {

                                    Text(
                                        text = "5. ${gym.Exercise5}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                        Divider(
                                            modifier = Modifier
                                                .width(2.dp)
                                                .height(20.dp)
                                                .background(Color.Black)
                                        )
                                        Spacer(modifier = Modifier.width(22.dp))
                                    }




                                    if (gym.Exercise5r2.isNotEmpty()) {
                                        Text(
                                            text = "5. ${gym.Exercise5r2}",
                                            color = Color.Black,
                                            modifier = Modifier.weight(1f)
                                        )

                                    }
                                }


                                if(gym.Exercise5R3.isNotEmpty()) {
                                    Divider(
                                        modifier = Modifier
                                            .width(2.dp)
                                            .height(20.dp)
                                            .background(Color.Black)
                                    )

                                    Spacer(modifier = Modifier.width(22.dp))
                                    Text(
                                        text = "5. ${gym.Exercise5R3}",
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }



//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
                            if(gym.ExtraExercise.isNotEmpty()){
                                Spacer(modifier = Modifier.padding(4.dp))

                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (gym.ExtraExercise.isNotEmpty()) {
                                    Text(
                                        text = "Additional Exercise - ${gym.ExtraExercise}",
                                        color = Color.Black,
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.padding(2.dp))
                            Divider()
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val diplomaFont = FontFamily(Font(R.font.diploma))
                                Text(text = "The End!" , fontFamily =  diplomaFont, fontSize = 25.sp)
                            }


                        }
                    }
                }
                }
            }
        }
    }
}