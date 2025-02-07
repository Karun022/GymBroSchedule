package com.example.gymbroschedule.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.calculateZoom
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymbroschedule.HomeAppBarView
import com.example.gymbroschedule.PreferenceHelper
import com.example.gymbroschedule.R
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.data.Database.GymDatabase
import com.example.gymbroschedule.data.WeightRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.ui.theme.BottomNavigationBar

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeView(
    navController: NavController,
    gymViewModel: GymViewModel,
    gymDatabase: GymDatabase
) {

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    var weightRecords by remember { mutableStateOf(emptyList<WeightRecord>()) }
    val gymList by remember { mutableStateOf(emptyList<Gym>()) }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }


    // Fetch the weight records when the composable is launched
    LaunchedEffect(Unit) {
        fetchWeightRecords(gymDatabase) { records ->
            weightRecords = records
        }
    }

    Scaffold(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.background),
        modifier = Modifier.wrapContentSize(),
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
//                                Icon(
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
//                                Icon(
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
//                                Icon(
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
//                                Icon(
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
//                        //expanded = !expanded
//                        navController.navigate(Screen.AddScreen.route + "/0L")
//                    },
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter) // Position centrally at the bottom
//                        .offset(y = -26.dp),
//                    contentColor = Color.White,
//                    backgroundColor = colorResource(id = R.color.floating_button_green),
//                    shape = CircleShape
//                ) {
//                    Icon(Icons.Default.Add, contentDescription = "Add Schedule")
//                }
//            }
        },
        topBar = {
            HomeAppBarView(
                title = "User!"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .offset(y = 70.dp)  // Adjust to overlap with the AppBar
                    .zIndex(1f)  // Ensure the Card is drawn on top
                    .padding(start = 8.dp, end = 8.dp)
                    .clip(shape = RoundedCornerShape(25.dp)),
                backgroundColor = colorResource(id = R.color.home_text_card)
            ) {

                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val context = LocalContext.current


                    // Initialize SharedPreferences
                    val sharedPreferences: SharedPreferences =
                        context.getSharedPreferences("quotes_prefs", Context.MODE_PRIVATE)

                    // List of quotes
                    val quotes = listOf(
                        "The only bad workout is the one that didn’t happen.",
                        "Strength doesn’t come from what you can do.",
                        "Your body can stand almost anything.",
                        "Don’t limit your challenges. Challenge your limits.",
                        "Success isn’t always about greatness.",
                        "Push yourself because no one else is going to do it for you.",
                        "Fitness is not about being better than someone else.",
                        "What seems impossible today will one day become your warm-up.",
                        "You don’t have to be extreme, just consistent.",
                        "The only way to achieve the impossible is to believe it is possible.",
                        "Your only limit is you.",
                        "Every workout is progress, no matter how small.",
                        "Strive for progress, not perfection.",
                        "The pain you feel today will be the strength you feel tomorrow.",
                        "It’s not about having time; it’s about making time.",
                        "Wake up with determination. Go to bed with satisfaction.",
                        "You don’t get what you wish for; you get what you work for.",
                        "Success is what happens after you have survived all of your mistakes.",
                        "If it doesn’t challenge you, it doesn’t change you.",
                        "Take care of your body. It’s the only place you have to live.",
                        "Don’t count the days; make the days count."
                    )

                    // Get the last displayed index from SharedPreferences
                    var currentQuoteIndex by remember {
                        mutableStateOf(
                            sharedPreferences.getInt(
                                "last_quote_index",
                                0
                            )
                        )
                    }

                    // Update the index and save it in SharedPreferences only when the Composable is first launched
                    LaunchedEffect(Unit) {
                        val newIndex = (currentQuoteIndex + 1) % quotes.size
                        sharedPreferences.edit().putInt("last_quote_index", newIndex).apply()
                        currentQuoteIndex = newIndex
                    }

                    // Display the quote
                    val currentQuote = quotes[currentQuoteIndex]


                    WelcomingText(text = currentQuote)
                }
            }

        },
        content = {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                LazyColumn(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {


                    item {
                        val context = LocalContext.current
                        ScheduleScreen(
                            viewModel = gymViewModel,
                            navController = navController
                        )//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 8.dp, end = 8.dp)
//                            .clip(
//                                shape = RoundedCornerShape(30.dp)
//                            ), backgroundColor = colorResource(id = R.color.card)
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 5.dp, start = 8.dp, end = 8.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Button(onClick = {  }) {
//                                Text(text = "My Schedule")
//                            }
//
//                            Button(onClick = { /*TODO*/ }) {
//                                Text(text = "Gymbro Schedule")
//                            }
//                        }
//                        GymList(viewModel = viewModel, navController = navController)

                        //}

                    }


                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 25.dp, start = 8.dp, end = 8.dp)
                                .clip(
                                    shape = RoundedCornerShape(30.dp)
                                ), backgroundColor = colorResource(id = R.color.home_card)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                if (weightRecords.isNotEmpty()) {
                                    LazyRow(

                                        modifier = Modifier
                                            .padding(16.dp)
                                            .weight(1f),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        val sortedRecords = weightRecords.sortedBy { it.date }

                                        items(sortedRecords) { record ->
                                            WeightRecordCard(record)
                                        }
                                    }
                                } else {
                                    // Fallback text if no records are available
                                    Text(
                                        text = "No weight records available",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .weight(1f),
                                        color = Color.White
                                    )
                                }

//                            IconButton(onClick = { navController.navigate(Screen.WeightRecord.route)}) {
//                            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "View all", tint = Color.White)
//                            }
                            }

                        }

                    }

                }

            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(viewModel: GymViewModel, navController: NavController) {
    val context = LocalContext.current
    val preferenceHelper = PreferenceHelper(context)

    // Add logs to debug schedule fetching
    val selectedSchedule = remember { mutableStateOf("Gymbro Schedule") }

    LaunchedEffect(Unit) {
        try {
            val savedSchedule = preferenceHelper.getSelectedSchedule("selected_schedule")
            if (!savedSchedule.isNullOrEmpty()) {
                selectedSchedule.value = savedSchedule
                Log.d("ScheduleScreen", "Fetched schedule: $savedSchedule")
            } else {
                Log.d("ScheduleScreen", "No saved schedule found, using default: Gymbro Schedule")
            }
        } catch (e: Exception) {
            Log.e("ScheduleScreen", "Error fetching schedule", e)
        }
    }

    // UI logic remains the same
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(30.dp)),
        backgroundColor = colorResource(id = R.color.home_card)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        selectedSchedule.value = "My Schedule" // Update the value properly
                        try {
                            preferenceHelper.saveSelectedSchedule(
                                "selected_schedule",
                                "My Schedule"
                            )
                            Log.d("ScheduleScreen", "Saved schedule: My Schedule")
                        } catch (e: Exception) {
                            Log.e("ScheduleScreen", "Error saving schedule", e)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedSchedule.value == "My Schedule") Color.Black else Color.Transparent
                    )
                ) {
                    Text(
                        text = "My Schedule",
                        color = if (selectedSchedule.value == "My Schedule") Color.White else Color.Gray
                    )
                }

                Button(
                    onClick = {
                        selectedSchedule.value = "Gymbro Schedule" // Update the value properly
                        try {
                            preferenceHelper.saveSelectedSchedule(
                                "selected_schedule",
                                "Gymbro Schedule"
                            )
                            Log.d("ScheduleScreen", "Saved schedule: Gymbro Schedule")
                        } catch (e: Exception) {
                            Log.e("ScheduleScreen", "Error saving schedule", e)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedSchedule.value == "Gymbro Schedule") Color.Black else Color.Transparent
                    )
                ) {
                    Text(
                        text = "Gymbro Schedule",
                        color = if (selectedSchedule.value == "Gymbro Schedule") Color.White else Color.Gray
                    )
                }
            }

            if (selectedSchedule.value == "My Schedule") {
                GymList(viewModel = viewModel, navController = navController)
            } else {
                GymbroSchedule()
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GymbroSchedule() {
    // Get today's day name (e.g., "Monday")
    val today = LocalDate.now().dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Today's Workout",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Use a `when` block to display the correct composable based on the day
        when (today) {
            "Sunday" -> MyGymItem1(isExpandable = true)
            "Monday" -> MyGymItem2()
            "Tuesday" -> MyGymItem3()
            "Wednesday" -> MyGymItem4()
            "Thursday" -> MyGymItem5()
            "Friday" -> MyGymItem6()
            "Saturday" -> MyGymItem7()
            else -> {
                // Fallback if no matching day is found
                Text(
                    text = "No Workout Scheduled for Today!",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun WeightRecordCard(record: WeightRecord) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Changed to fillMaxWidth to give Column more space
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = colorResource(id = R.color.weight_card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side: Texts (Date and Weight)
            Column(
                modifier = Modifier.fillMaxWidth(0.8f), // Give Column 80% of the Row's width
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Date: ${record.date}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Day: ${record.dayOfWeek}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )


                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Weight: ${record.weight} kg",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GymList(viewModel: GymViewModel, navController: NavController) {

    val gymList by viewModel.getAllInfo.collectAsState(initial = emptyList())

    val currentDayOfWeek =
        LocalDate.now().dayOfWeek.name // Get the current day in uppercase (e.g., MONDAY)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center // Center it if only one item
    ) {
        // Filter gyms where the day matches the current day (case-insensitive)
        val matchingGyms = gymList.filter { it.Day.equals(currentDayOfWeek, ignoreCase = true) }

        // If gyms are available for the current day, display them
        if (matchingGyms.isNotEmpty()) {
            items(matchingGyms) { gym ->
                Column {


                    Text(
                        text = "Today's Workout",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .fillParentMaxWidth() // Fill width of the parent
                            .wrapContentHeight() // Set desired height for the card
                            .clip(RoundedCornerShape(20.dp))
                            .padding(top = 8.dp),
                        backgroundColor = Color.Transparent
                    ) {
                        todayWorkout(
                            gym = gym,
                            onClick = { /* Handle click event */ },
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun WelcomingText(text: String, typingSpeed: Long = 100) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        // Loop forever
        displayedText = ""
        for (char in text) {
            displayedText += char
            delay(typingSpeed)
        }
        // Optionally, add a delay before restarting the animation
        delay(1000) // Adjust the pause duration before restarting

    }

    Text(text = displayedText, fontSize = 18.sp)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun todayWorkout(
    gym: Gym,
    onClick: () -> Unit,
    navController: NavController,
    viewModel: GymViewModel
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),

        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.outter_card),
        onClick = {
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

                    Column() {


                        Text(
                            text = gym.Day,
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row(

                        ) {
                            Text(
                                text = "${gym.Workout} - Workout",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
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

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
            }

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
            ) {


                Card(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    backgroundColor = colorResource(id = R.color.inner_card)
                ) {


                    Column(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 5.dp,
                            bottom = 8.dp
                        )
                    ) {


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
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
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
                                textDecoration = TextDecoration.Underline,
                                color = Color.Black

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
                                    textDecoration = TextDecoration.Underline,
                                    color = Color.Black
                                )
                            }
                            if (gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()) {
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
                                    textDecoration = TextDecoration.Underline,
                                    color = Color.Black

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

                            if (gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()) {
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

                            if (gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()) {
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

                            if (gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()) {
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

                            if (gym.Exercise1R3.isNotEmpty() || gym.Exercise2R3.isNotEmpty() || gym.Exercise3R3.isNotEmpty()) {
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

                            if (gym.Exercise5.isNotEmpty()) {

                                Text(
                                    text = "5. ${gym.Exercise5}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )

                            }




                            if (gym.Exercise1R2.isNotEmpty() || gym.Exercise2R2.isNotEmpty() || gym.Exercise3R2.isNotEmpty()) {
                                Divider(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(20.dp)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(22.dp))
                                Text(
                                    text = "5. ${gym.Exercise5r2}",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )

                            }


                            if (gym.Exercise5R3.isNotEmpty()) {
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
                        if (gym.ExtraExercise.isNotEmpty()) {
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
                            Text(
                                text = "The End!",
                                fontFamily = diplomaFont,
                                fontSize = 25.sp,
                                color = Color.Black
                            )
                        }


                    }
                }
            }
        }
    }
}


private fun fetchWeightRecords(gymDatabase: GymDatabase, onResult: (List<WeightRecord>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val records = gymDatabase.weightDao().getWeightRecordsByScheduleId(1L)
        onResult(records)
    }
}








