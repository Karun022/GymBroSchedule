package com.example.gymbroschedule.Screens


import android.view.WindowInsets
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.ui.theme.BottomNavigationBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable

fun AddEditDetailView(
    id: Long,
    viewModel: GymViewModel,
    navController: NavController
) {
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var selectedIndex by remember { mutableStateOf(-1) }
    var isGymDayError by remember { mutableStateOf(false) }
    var isGymWorkoutError by remember { mutableStateOf(false) }
    var isExercise1Error by remember { mutableStateOf(false) }


    if (id != 0L) {
        val gym = viewModel.getAnInfoById(id)
            .collectAsState(initial = Gym(0L, "", "", "", "", "", "", ""))
        viewModel.gymDayState = gym.value.Day
        viewModel.gymWorkoutState = gym.value.Workout
        viewModel.Exercise1 = gym.value.Exercise1
        viewModel.Exercise1R2 = gym.value.Exercise1R2.toString()
        viewModel.Exercise1R3 = gym.value.Exercise1R3.toString()
        viewModel.Exercise2 = gym.value.Exercise2
        viewModel.Exercise2R2 = gym.value.Exercise2R2.toString()
        viewModel.Exercise2R3 = gym.value.Exercise2R3.toString()
        viewModel.Exercise3 = gym.value.Exercise3
        viewModel.Exercise3R2 = gym.value.Exercise3R2.toString()
        viewModel.Exercise3R3 = gym.value.Exercise3R3.toString()
        viewModel.Exercise4 = gym.value.Exercise4
        viewModel.Exercise4R2 = gym.value.Exercise4R2.toString()
        viewModel.Exercise4R3 = gym.value.Exercise4R3.toString()
        viewModel.Exercise5 = gym.value.Exercise5
        viewModel.Exercise5R2 = gym.value.Exercise5r2.toString()
        viewModel.Exercise5R3 = gym.value.Exercise5R3.toString()
        viewModel.ExtraExercise = gym.value.ExtraExercise

    } else {
        viewModel.gymDayState = ""
        viewModel.gymWorkoutState = ""
        viewModel.Exercise1 = ""
        viewModel.Exercise1R2 = ""
        viewModel.Exercise1R3 = ""
        viewModel.Exercise2 = ""
        viewModel.Exercise2R2 = ""
        viewModel.Exercise2R3 = ""
        viewModel.Exercise3 = ""
        viewModel.Exercise3R2 = ""
        viewModel.Exercise3R3 = ""
        viewModel.Exercise4 = ""
        viewModel.Exercise4R2 = ""
        viewModel.Exercise4R3 = ""
        viewModel.Exercise5 = ""
        viewModel.Exercise5R2 = ""
        viewModel.Exercise5R3 = ""
        viewModel.ExtraExercise = ""

    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var isClicked by remember { mutableStateOf(false) }





    Scaffold(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.background),
        scaffoldState = scaffoldState,
        floatingActionButton = {

            FloatingActionButton(onClick = { isClicked = true
                // Check if required fields are empty and update error states
                isGymDayError = viewModel.gymDayState.isEmpty()
                isGymWorkoutError = viewModel.gymWorkoutState.isEmpty()
                isExercise1Error = viewModel.Exercise1.isEmpty()

                if (!isGymDayError && !isGymWorkoutError && !isExercise1Error) {
                    val gym = Gym(
                        id = id,
                        Day = viewModel.gymDayState.trim(),
                        Workout = viewModel.gymWorkoutState.trim(),
                        Exercise1 = viewModel.Exercise1.trim(),
                        Exercise1R2 = viewModel.Exercise1R2.trim(),
                        Exercise1R3 = viewModel.Exercise1R3.trim(),
                        Exercise2 = viewModel.Exercise2.trim(),
                        Exercise2R2 = viewModel.Exercise2R2.trim(),
                        Exercise2R3 = viewModel.Exercise2R3.trim(),
                        Exercise3 = viewModel.Exercise3.trim(),
                        Exercise3R2 = viewModel.Exercise3R2.trim(),
                        Exercise3R3 = viewModel.Exercise3R3.trim(),
                        Exercise4 = viewModel.Exercise4.trim(),
                        Exercise4R2 = viewModel.Exercise4R2.trim(),
                        Exercise4R3 = viewModel.Exercise4R3.trim(),
                        Exercise5 = viewModel.Exercise5.trim(),
                        Exercise5r2 = viewModel.Exercise5R2.trim(),
                        Exercise5R3 = viewModel.Exercise5R3.trim(),
                        ExtraExercise = viewModel.ExtraExercise.trim()
                    )
                    if (id != 0L) {
                        viewModel.updateAnInfo(gym)
                    } else {
                        viewModel.addAnInfo(gym)
                    }
                    scope.launch {
                        delay(1000L)
                        navController.navigate(Screen.HomeScreen.route) // Navigate to previous screen
                    }
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Please fill the fields.")
                    }
                } }, modifier = Modifier
                .imePadding() // Adjust padding for the keyboard
                .padding(16.dp),  backgroundColor = colorResource(id = R.color.floating_button_green)){ Icon(
                 painterResource(id = R.drawable.baseline_save_24),
                contentDescription ="Save"
            )}
            
        },
        modifier = Modifier.wrapContentSize(),
        topBar = {
            Box {
                // Top bar with title and back navigation
                AppBarView(
                    title = if (id != 0L) stringResource(id = R.string.update_workouts)
                    else stringResource(id = R.string.add_workouts)
                ) {
                    navController.navigateUp()
                }

//                Box(
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .offset(y = 50.dp)
//                        .zIndex(1f)
//                        .padding(end = 25.dp) // Adjust padding for alignment
//                        .size(60.dp)
//                        .background(Color.Green, shape = CircleShape).clickable {
//                            isClicked = true
//                            // Check if required fields are empty and update error states
//                            isGymDayError = viewModel.gymDayState.isEmpty()
//                            isGymWorkoutError = viewModel.gymWorkoutState.isEmpty()
//                            isExercise1Error = viewModel.Exercise1.isEmpty()
//
//                            if (!isGymDayError && !isGymWorkoutError && !isExercise1Error) {
//                                val gym = Gym(
//                                    id = id,
//                                    Day = viewModel.gymDayState.trim(),
//                                    Workout = viewModel.gymWorkoutState.trim(),
//                                    Exercise1 = viewModel.Exercise1.trim(),
//                                    Exercise1R2 = viewModel.Exercise1R2.trim(),
//                                    Exercise1R3 = viewModel.Exercise1R3.trim(),
//                                    Exercise2 = viewModel.Exercise2.trim(),
//                                    Exercise2R2 = viewModel.Exercise2R2.trim(),
//                                    Exercise2R3 = viewModel.Exercise2R3.trim(),
//                                    Exercise3 = viewModel.Exercise3.trim(),
//                                    Exercise3R2 = viewModel.Exercise3R2.trim(),
//                                    Exercise3R3 = viewModel.Exercise3R3.trim(),
//                                    Exercise4 = viewModel.Exercise4.trim(),
//                                    Exercise4R2 = viewModel.Exercise4R2.trim(),
//                                    Exercise4R3 = viewModel.Exercise4R3.trim(),
//                                    Exercise5 = viewModel.Exercise5.trim(),
//                                    Exercise5r2 = viewModel.Exercise5R2.trim(),
//                                    Exercise5R3 = viewModel.Exercise5R3.trim(),
//                                    ExtraExercise = viewModel.ExtraExercise.trim()
//                                )
//                                if (id != 0L) {
//                                    viewModel.updateAnInfo(gym)
//                                } else {
//                                    viewModel.addAnInfo(gym)
//                                }
//                                scope.launch {
//                                    delay(1000L)
//                                    navController.navigate(Screen.HomeScreen.route) // Navigate to previous screen
//                                }
//                            } else {
//                                coroutineScope.launch {
//                                    snackbarHostState.showSnackbar("Please fill the fields.")
//                                }
//                            }
//                        },
//                    contentAlignment = Alignment.Center
//                ) {
//                    IconButton(
//                        onClick = {
//                            isClicked = true
//                            // Check if required fields are empty and update error states
//                            isGymDayError = viewModel.gymDayState.isEmpty()
//                            isGymWorkoutError = viewModel.gymWorkoutState.isEmpty()
//                            isExercise1Error = viewModel.Exercise1.isEmpty()
//
//                            if (!isGymDayError && !isGymWorkoutError && !isExercise1Error) {
//                                val gym = Gym(
//                                    id = id,
//                                    Day = viewModel.gymDayState.trim(),
//                                    Workout = viewModel.gymWorkoutState.trim(),
//                                    Exercise1 = viewModel.Exercise1.trim(),
//                                    Exercise1R2 = viewModel.Exercise1R2.trim(),
//                                    Exercise1R3 = viewModel.Exercise1R3.trim(),
//                                    Exercise2 = viewModel.Exercise2.trim(),
//                                    Exercise2R2 = viewModel.Exercise2R2.trim(),
//                                    Exercise2R3 = viewModel.Exercise2R3.trim(),
//                                    Exercise3 = viewModel.Exercise3.trim(),
//                                    Exercise3R2 = viewModel.Exercise3R2.trim(),
//                                    Exercise3R3 = viewModel.Exercise3R3.trim(),
//                                    Exercise4 = viewModel.Exercise4.trim(),
//                                    Exercise4R2 = viewModel.Exercise4R2.trim(),
//                                    Exercise4R3 = viewModel.Exercise4R3.trim(),
//                                    Exercise5 = viewModel.Exercise5.trim(),
//                                    Exercise5r2 = viewModel.Exercise5R2.trim(),
//                                    Exercise5R3 = viewModel.Exercise5R3.trim(),
//                                    ExtraExercise = viewModel.ExtraExercise.trim()
//                                )
//                                if (id != 0L) {
//                                    viewModel.updateAnInfo(gym)
//                                } else {
//                                    viewModel.addAnInfo(gym)
//                                }
//                                scope.launch {
//                                    delay(1000L)
//                                    navController.navigate(Screen.HomeScreen.route) // Navigate to previous screen
//                                }
//                            } else {
//                                coroutineScope.launch {
//                                    snackbarHostState.showSnackbar("Please fill the fields.")
//                                }
//                            }
//                        }
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.baseline_save_24), // Replace with your save icon
//                            contentDescription = "Save",
//                            tint = Color.Black
//                        )
//                    }
//                }
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
//                                if (currentRoute != Screen.HomeScreen.route) {
//                                    navController.navigate(Screen.HomeScreen.route)
//                                }
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
//                                    contentDescription = "Offers",
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
//                   backgroundColor =  colorResource(id = R.color.floating_button_green),
//                    shape = CircleShape
//                ) {
//                    androidx.compose.material3.Icon(
//                        Icons.Default.Add,
//                        contentDescription = "Scan & Pay"
//                    )
//                }
//            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) // Add SnackbarHost to the Scaffold
        },
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(colorResource(id = R.color.home_app_bar)),
        ) {
            item {
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),  // SpacedBy for controlled spacing
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        backgroundColor = colorResource(id = R.color.home_card)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp), // Added spacing between the elements
                            modifier = Modifier.padding(16.dp)
                        ) {
                            gymHeader(
                                label = "Day",
                                value = viewModel.gymDayState,
                                onValueChanged = { viewModel.onGymDayChanged(it) },
                                isError = isGymDayError

                            )

                            gymHeader(
                                label = "Workout",
                                value = viewModel.gymWorkoutState,
                                onValueChanged = { viewModel.onGymWorkoutChanged(it) },
                                isError = isGymWorkoutError
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 70.dp, end = 70.dp)
                ) {
                    Text(
                        text = "Week 1",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Week 2",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Week 3",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    backgroundColor = colorResource(id = R.color.home_card)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        //modifier = Modifier.padding(8.dp)
                    ) {
                        for (index in 1..5) {
                            gymTextField(
                                label = "Variations",
                                value = when (index) {
                                    1 -> viewModel.Exercise1
                                    2 -> viewModel.Exercise2
                                    3 -> viewModel.Exercise3
                                    4 -> viewModel.Exercise4
                                    5 -> viewModel.Exercise5
                                    else -> ""
                                },
                                value2 = when (index) {
                                    1 -> viewModel.Exercise1R2
                                    2 -> viewModel.Exercise2R2
                                    3 -> viewModel.Exercise3R2
                                    4 -> viewModel.Exercise4R2
                                    5 -> viewModel.Exercise5R2
                                    else -> ""
                                },
                                value3 = when (index) {
                                    1 -> viewModel.Exercise1R3
                                    2 -> viewModel.Exercise2R3
                                    3 -> viewModel.Exercise3R3
                                    4 -> viewModel.Exercise4R3
                                    5 -> viewModel.Exercise5R3
                                    else -> ""
                                },
                                onValueChanged = {
                                    when (index) {
                                        1 -> viewModel.onExercise1Changed(it)
                                        2 -> viewModel.onExercise2Changed(it)
                                        3 -> viewModel.onExercise3Changed(it)
                                        4 -> viewModel.onExercise4Changed(it)
                                        5 -> viewModel.onExercise5Changed(it)
                                    }
                                },
                                onValueChanged2 = {
                                    when (index) {
                                        1 -> viewModel.onExercise1R2Changed(it)
                                        2 -> viewModel.onExercise2R2Changed(it)
                                        3 -> viewModel.onExercise3R2Changed(it)
                                        4 -> viewModel.onExercise4R2Changed(it)
                                        5 -> viewModel.onExercise5R2Changed(it)
                                    }
                                },
                                onValueChanged3 = {
                                    when (index) {
                                        1 -> viewModel.onExercise1R3Changed(it)
                                        2 -> viewModel.onExercise2R3Changed(it)
                                        3 -> viewModel.onExercise3R3Changed(it)
                                        4 -> viewModel.onExercise4R3Changed(it)
                                        5 -> viewModel.onExercise5R3Changed(it)
                                    }
                                },
                                exerciseNum = index
                            )
                        }
                    }
                }
            }


            item {

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),  // SpacedBy for controlled spacing
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {


                    extraExercise(
                        label = "Extra Exercises",
                        value = viewModel.ExtraExercise,
                        onValueChanged = { viewModel.onExtraExerciseChanged(it) }
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                }
            }

//            item {
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    var isClicked by remember { mutableStateOf(false) }
//                    var scheduleCount by mutableStateOf(0)
//
//                    Button(
//                        onClick = {
//                            isClicked = true
//                            // Check if required fields are empty and update error states
//                            isGymDayError = viewModel.gymDayState.isEmpty()
//                            isGymWorkoutError = viewModel.gymWorkoutState.isEmpty()
//                            isExercise1Error = viewModel.Exercise1.isEmpty()
//
//                            if (!isGymDayError && !isGymWorkoutError && !isExercise1Error) {
//                                val gym = Gym(
//                                    id = id,
//                                    Day = viewModel.gymDayState.trim(),
//                                    Workout = viewModel.gymWorkoutState.trim(),
//                                    Exercise1 = viewModel.Exercise1.trim(),
//                                    Exercise1R2 = viewModel.Exercise1R2.trim(),
//                                    Exercise1R3 = viewModel.Exercise1R3.trim(),
//                                    Exercise2 = viewModel.Exercise2.trim(),
//                                    Exercise2R2 = viewModel.Exercise2R2.trim(),
//                                    Exercise2R3 = viewModel.Exercise2R3.trim(),
//                                    Exercise3 = viewModel.Exercise3.trim(),
//                                    Exercise3R2 = viewModel.Exercise3R2.trim(),
//                                    Exercise3R3 = viewModel.Exercise3R3.trim(),
//                                    Exercise4 = viewModel.Exercise4.trim(),
//                                    Exercise4R2 = viewModel.Exercise4R2.trim(),
//                                    Exercise4R3 = viewModel.Exercise4R3.trim(),
//                                    Exercise5 = viewModel.Exercise5.trim(),
//                                    Exercise5r2 = viewModel.Exercise5R2.trim(),
//                                    Exercise5R3 = viewModel.Exercise5R3.trim(),
//                                    ExtraExercise = viewModel.ExtraExercise.trim()
//                                )
//                                if (id != 0L) {
//                                    viewModel.updateAnInfo(gym)
//                                } else {
//                                    viewModel.addAnInfo(gym)
//                                    scheduleCount++
//                                }
//                                scope.launch {
//                                    delay(1000L)
//                                    navController.navigate(Screen.HomeScreen.route) // Navigate to previous screen
//                                }
//                            } else {
//                                coroutineScope.launch {
//                                    snackbarHostState.showSnackbar("Please fill the fields.")
//                                }
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
//                        modifier = Modifier.padding(16.dp)
//                    ) {
//
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center,
//                        ) {
//                            Text(
//                                text = if (id != 0L) stringResource(id = R.string.update_workouts)
//                                else stringResource(id = R.string.add_workouts),
//                                style = TextStyle(fontSize = 18.sp)
//                            )
//                            if (viewModel.gymDayState.isNotEmpty() &&
//                                viewModel.gymWorkoutState.isNotEmpty() &&
//                                viewModel.Exercise1.isNotEmpty()
//                            ) {
//                                Spacer(modifier = Modifier.width(5.dp))
//                                AnimatedVisibility(
//                                    visible = isClicked,
//                                    enter = scaleIn() + fadeIn(),
//                                    exit = scaleOut() + fadeOut()
//                                ) {
//                                    Icon(
//                                        Icons.Default.Check,
//                                        contentDescription = "Check",
//                                        tint = Color.Green,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }`
        }
    }

}



@Composable
fun gymTextField(
    label: String,
    value: String,
    value2: String,
    value3: String,
    exerciseNum: Int,
    onValueChanged: (String) -> Unit,
    onValueChanged2: (String) -> Unit,
    onValueChanged3: (String) -> Unit,
) {


    Column(
        verticalArrangement = Arrangement.Top, // Arrange items from top
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp) // Optional: Add some padding to the whole column
    ) {


        // Exercise label at the top
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Text(
                text = "Exercise ${exerciseNum}",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
        }


        // The row for text fields
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            OutlinedTextField(
                value = value2,
                onValueChange = onValueChanged2,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            OutlinedTextField(
                value = value3,
                onValueChange = onValueChanged3,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            Spacer(modifier = Modifier.height((-15).dp))
        }
    }
}


@Composable
fun extraExercise(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(start = 8.dp, end = 8.dp), backgroundColor = colorResource(id = R.color.home_card)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, 
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {

            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = label, color = colorResource(id = R.color.Text))
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),  // Standard keyboard type
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.Text),
                        fontSize = 16.sp  // Adjust text size for readability
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = colorResource(id = R.color.Text),
                        focusedBorderColor = colorResource(id = R.color.Text),
                        unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                        cursorColor = colorResource(id = R.color.Text),  // Same color for cursor
                        focusedLabelColor = colorResource(id = R.color.Text),
                        unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                    )
                )
            }
        }
    }
}

@Composable
fun gymHeader(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean  // New parameter to control error state
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label, color = if (isError) Color.Red else Color.White)
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        textStyle = TextStyle(
            color = colorResource(id = R.color.Text),
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.Text),
            focusedBorderColor = if (isError) Color.Red else colorResource(id = R.color.Text),
            unfocusedBorderColor = if (isError) Color.Red else Color.Black,
            cursorColor = colorResource(id = R.color.Text),
            focusedLabelColor = colorResource(id = R.color.Text),
            unfocusedLabelColor = Color.White
        )
    )
}
