package com.example.gymbroschedule.Screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.ui.theme.BottomNavigationBar

@Composable
fun MyExerciseView(
    navController: NavController,
    viewModel: GymViewModel
) {


    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(2) }

    Scaffold(
        topBar = { AppBarView(title = "Schedules") { navController.navigateUp() } },
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
//                            Text(
//                                "Home",
//                                color = if (selectedIndex == 0) Color.Green else Color.White,
//                                fontSize = 12.sp
//                            ) // Set font size to avoid overflow
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
//                            Text(
//                                "Schedule",
//                                color = if (selectedIndex == 1) Color.Green else Color.White,
//                                fontSize = 12.sp
//                            )
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
//                            Text(
//                                "Exercises",
//                                color = if (selectedIndex == 2) Color.Green else Color.White,
//                                fontSize = 12.sp
//                            )
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
//                            Text(
//                                "Weight",
//                                color = if (selectedIndex == 3) Color.Green else Color.White,
//                                fontSize = 12.sp
//                            )
//                        }
//                    }
//                }
//
//                // Central button positioned above the bottom bar
//                FloatingActionButton(
//                    onClick = {
//                        selectedIndex = -1
//                        navController.navigate(Screen.AddScreen.route + "/0L")
//                        /* Handle Scan & Pay action */
//                    },
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    MyGymItem1()
                    MyGymItem2()
                    MyGymItem3()
                    MyGymItem4()
                    MyGymItem5()
                    MyGymItem6()
                    MyGymItem7()

                }
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem1(isExpandable: Boolean = true) {

    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.outter_card),
        onClick = {
            if (isExpandable) {
                isExpanded = !isExpanded
            }
                  },
    )
    {
        Column(
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Sunday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Back - Workout",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                }
                if (isExpandable) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center
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
            }


            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. Bend Over Row",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. Wide grip lats pulldown",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )

                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. Single arm lats pulldown",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. T bar row(upper back)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. T bar row(lats focused)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. Seated cable row",
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
                                text = "4. Single arm seated cable row",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. Dumbbell Row",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text(
                                text = "5. Deadlift",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))





                            Text(
                                text = "5. DeadLift",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


                        }



                        Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Forearms",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem2() {


    var isExpanded by remember { mutableStateOf(true) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Monday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Biceps/Triceps - Workout",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2nd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3rd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
//                                Text(
//                                    text = "1. Barbell curl + Skull crusher(Straight Bar)",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
                                //Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1.  Barbell curl + Skull crusher(EZ Bar)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
//                                Text(
//                                    text = "2. Cable curl + Dumbell triceps extention ",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
                                //Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. Peacher Curl + Rope triceps extention",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
//                                Text(
//                                    text = "3. Spider curl + Tricep pushdown(Straight bar)",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
                               // Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. dumbell curl/ inclined dumbbell curl + Tricep pushdown(Straight bar)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                            Text(
//                                text = "4.Rope hammer curl + Tricep Pushdown(rope)",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. Dumbell hammer curl + Tricep pushdown(rope)",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )


                        }
                     //   Spacer(modifier = Modifier.padding(6.dp))

//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//
//
//                            Text(
//                                text = "5. 1",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//
//
//
//
//
//                            Text(
//                                text = "5. 2",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//
//
//
//
//
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "5. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//
//                        }


//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
                         Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Abs",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem3() {


    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Tuesday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Legs - Workout",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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

//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3rd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. Squats",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. Squats",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. Bulgerian Squat",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. Leg curl",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. ",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. RDL",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. Leg extention",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )

//
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4. Leg extention",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. Calfs",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )


                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text(
                                text = "5. Calfs",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))





                            Text(
                                text = "5. Machine Squats",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "5. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )

                        }


//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
//                        Spacer(modifier = Modifier.padding(4.dp))
//
//
//                        Row(
//                            Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            Text(
//                                text = "Additional Exercise - Forearms",
//                                color = Color.Black,
//                            )
//
//
//                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem4() {


    var isExpanded by remember { mutableStateOf(true) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Wednesday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Chest - Workout",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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

//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3rd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. Bench Press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. Inclined Bench Press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. Inclined Chest fly",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. Inclined Chest fly",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. Inclined dumbell press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. Inclined dumbell press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4. pec deck fly  ",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. lower chest cable fly ",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
//
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. cable chest fly ",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )


                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text(
                                text = "5. cable chest fly",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))





                            Text(
                                text = "5. dumbell press",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "5. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )

                        }


//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
                        Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Forearms",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem5() {


    var isExpanded by remember { mutableStateOf(true) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Thursday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Shoulder - Workout",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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

//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3rd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. Barbell press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. shoulder machine press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. Dumbell lateral Raise",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. Cable lateral raise",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. Dumbbell rear delt row",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. Dumbbell Press",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4. Face pull",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. Dumbbell rear delt row",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )


                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text(
                                text = "5. Shrugs",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))





                            Text(
                                text = "5. Shrugs",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "5. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )

                        }


//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
                        Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Abs",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem6() {


    var isExpanded by remember { mutableStateOf(true) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Friday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Biceps/Triceps - Workout",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2nd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )
//
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3rd week",
//                                    modifier = Modifier.weight(1f),
//                                    fontWeight = FontWeight.SemiBold,
//                                    textDecoration = TextDecoration.Underline
//                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. Spider curl + Tricep pushdown(Straight bar)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. Barbell curl + Skull crusher(Straight Bar)",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//
//
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "1. Cable curl + Dumbell triceps extention ",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. Barbell curl + Skull crusher(Straight Bar)",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. 2",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//
//
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "2. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//

                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. Cable curl + Dumbell triceps extention ",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 2",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
//
//
//                                Divider(
//                                    modifier = Modifier
//                                        .width(2.dp)
//                                        .height(20.dp)
//                                        .background(Color.Black)
//                                )
//
//                                Spacer(modifier = Modifier.width(22.dp))
//                                Text(
//                                    text = "3. 3",
//                                    color = Color.Black,
//                                    modifier = Modifier.weight(1f)
//                                )
                           }

                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4. Rope hammer curl + Tricep Pushdown(rope)",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. 2",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "4. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )


                        }
//                        Spacer(modifier = Modifier.padding(6.dp))
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//
//
//                            Text(
//                                text = "5. 1",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//                            Spacer(modifier = Modifier.width(22.dp))
//
//
//
//
//
//                            Text(
//                                text = "5. 2",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//
//
//
//
//
//                            Divider(
//                                modifier = Modifier
//                                    .width(2.dp)
//                                    .height(20.dp)
//                                    .background(Color.Black)
//                            )
//
//                            Spacer(modifier = Modifier.width(22.dp))
//                            Text(
//                                text = "5. 3",
//                                color = Color.Black,
//                                modifier = Modifier.weight(1f)
//                            )
//
//                        }
//
//
////                            Spacer(modifier = Modifier.padding(6.dp))
////                            Divider()
                        Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Forearms",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGymItem7() {


    var isExpanded by remember { mutableStateOf(true) }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

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

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column {


                        Text(
                            text = "Saturday",
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row() {
                            Text(
                                text = "Rest Day",
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

//                        IconButton(onClick = { showMenu = true }) {
//                            Icon(
//                                imageVector = Icons.Default.MoreVert,
//                                contentDescription = "More options"
//                            )
//                        }
//                        DropdownMenu(
//                            modifier = Modifier
//                                .background(color = Color.Blue)
//                                .clip(RoundedCornerShape(12.dp)),
//                            expanded = showMenu,
//                            onDismissRequest = { showMenu = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = {
//                                    Row {
//                                        Icon(
//                                            modifier = Modifier.padding(bottom = 5.dp),
//                                            imageVector = Icons.Default.Edit,
//                                            contentDescription = "Edit",
//                                            tint = Color.White
//
//                                        )
//                                        Spacer(modifier = Modifier.width(8.dp))
//                                        Text(text = "Edit")
//                                    }
//                                },
//                                onClick = {
//                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
//                                    showMenu = false
//                                },
//                            )
//
//                            DropdownMenuItem(text = {
//                                Row {
//                                    Icon(
//                                        modifier = Modifier.padding(bottom = 5.dp),
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete",
//                                        tint = Color.White
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Delete")
//                                }
//                            },
//                                onClick = {
//                                    showDialog = true
//
//                                    showMenu = false
//                                }
//                            )
//                        }

//                        if (showDialog) {
//                            AnimatedVisibility(
//                                visible = showDialog,
//                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
//                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
//                            ) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        showDialog = false
//                                    },
//                                    title = {
//                                        Text(
//                                            text = "Confirm Deletion",
//                                            fontWeight = FontWeight.ExtraBold,
//                                            fontSize = 18.sp
//                                        )
//                                    },
//                                    text = {
//                                        Text(
//                                            "Are you sure you want to delete this Schedule?",
//                                            fontWeight = FontWeight.Bold,
//                                            fontSize = 16.sp
//                                        )
//                                    },
//                                    confirmButton = {
//                                        Button(
//                                            onClick = {
//                                                viewModel.deleteAnInfo(gym)
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//
//                                        ) {
//                                            Text("Delete")
//                                        }
//                                    },
//                                    dismissButton = {
//                                        Button(
//                                            onClick = {
//                                                showDialog = false
//                                            },
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = colorResource(
//                                                    id = R.color.Sky_Blue
//                                                )
//                                            )
//
//                                        ) {
//                                            Text("Cancel")
//                                        }
//                                    }
//                                )
//                            }
//                        }
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
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 50))

            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp)), backgroundColor = colorResource(id = R.color.inner_card)
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
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Exercises",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold
                                )
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

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "1. 1",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. 2",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "1. 3",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2. 1",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. 2",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "2. 3",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                            }
                            Spacer(modifier = Modifier.padding(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "3. 1",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. 2",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )


                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )

                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "3. 3",
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
                                text = "4. 1",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. 2",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "4. 3",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )


                        }
                        Spacer(modifier = Modifier.padding(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            Text(
                                text = "5. 1",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(22.dp))





                            Text(
                                text = "5. 2",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )





                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(20.dp)
                                    .background(Color.Black)
                            )

                            Spacer(modifier = Modifier.width(22.dp))
                            Text(
                                text = "5. 3",
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

                        }


//                            Spacer(modifier = Modifier.padding(6.dp))
//                            Divider()
                        Spacer(modifier = Modifier.padding(4.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Additional Exercise - Forearms",
                                color = Color.Black,
                            )


                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                        Divider()
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(text = "The End!", fontFamily = diplomaFont, fontSize = 25.sp)
                        }


                    }
                }
            }
        }
    }
}
