package com.example.gymbroschedule.Screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.data.Database.GymDatabase
import com.example.gymbroschedule.data.WeightRecord
import com.example.gymbroschedule.ui.theme.BottomNavigationBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeightScale(navController: NavController, gymDatabase: GymDatabase) {
    var weight by remember { mutableStateOf(TextFieldValue("")) }
    val calendar = Calendar.getInstance()
    var selectedDate by remember {
        mutableStateOf(
            "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                calendar.get(Calendar.YEAR)
            }"
        )
    }
    var weightRecords by remember { mutableStateOf(emptyList<WeightRecord>()) }
    val context = LocalContext.current

    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope() // Initialize coroutine scope
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(3) }
    var isFieldEmpty by remember { mutableStateOf(false) }


    // Fetch initial records
    LaunchedEffect(Unit) {
        fetchWeightRecords(gymDatabase) { records ->
            weightRecords = records
        }
    }

    Scaffold(
        topBar = { AppBarView(title = "Weight Record") { navController.navigateUp() } },
        backgroundColor = colorResource(id = R.color.background),
        snackbarHost = {
            SnackbarHost(snackbarHostState) // Add SnackbarHost to the Scaffold
        },
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
                                IconButton(
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
                                IconButton(
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
//                        contentDescription = "Scan & Pay"
//                    )
//                }
//            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp, end = 18.dp, top = 50.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding()
                        .clip(RoundedCornerShape(15.dp)),
                    backgroundColor = colorResource(id = R.color.home_card)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = weight,
                            onValueChange = {
                                weight = it
                                if (it.text.isNotEmpty()) {
                                    isFieldEmpty = false // Reset if the field is filled
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text(text = "WEIGHT (in kg)") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Green,
                                unfocusedBorderColor = if (isFieldEmpty) Color.Red else Color.Gray.copy(alpha = ContentAlpha.disabled)
                            )
                        )



                        OutlinedTextField(
                            value = selectedDate,
                            onValueChange = { },
                            label = { Text(text = "Select Date") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    val datePickerDialog = DatePickerDialog(
                                        context,
                                        { _, year, month, dayOfMonth ->
                                            selectedDate = "$dayOfMonth/${month + 1}/$year"
                                        },
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                                    )

                                    // Restrict the DatePicker to exclude tomorrow
                                    val today = Calendar.getInstance()
                                    datePickerDialog.datePicker.maxDate = today.timeInMillis // Set max date to today

                                    // Show the date picker
                                    datePickerDialog.show()
                                }){
                                    Icon(
                                        painterResource(id = R.drawable.baseline_calendar_month_24),
                                        tint = Color.White,
                                        contentDescription = "Select Date"
                                    )
                                }
                            }
                        )

                        Button(onClick = {
                            if (weight.text.isEmpty()) {
                                isFieldEmpty = true // Set to true to make the border red if field is empty
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Please enter a weight.")
                                }
                            } else {
                                isFieldEmpty = false // Reset if the field has content
                                val weightValue = weight.text.toFloatOrNull()
                                if (weightValue != null) {
                                    // Save the weight record as before
                                    val dateParts = selectedDate.split("/")
                                    val day = dateParts[0].toInt()
                                    val month = dateParts[1].toInt() - 1
                                    val year = dateParts[2].toInt()

                                    val calendar = Calendar.getInstance()
                                    calendar.set(year, month, day)
                                    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

                                    val timestamp = System.currentTimeMillis()
                                    val weightRecord = WeightRecord(
                                        scheduleId = 1L,
                                        weight = weightValue,
                                        date = selectedDate,
                                        dayOfWeek = dayOfWeek ?: "Unknown",
                                        timestamp = timestamp
                                    )

                                    saveWeightRecord(weightRecord, gymDatabase) {
                                        fetchWeightRecords(gymDatabase) { records ->
                                            weightRecords = records
                                        }

                                        // Clear the weight input field
                                        weight = TextFieldValue("")

                                        // Show Snackbar message
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Weight record saved!")
                                        }
                                    }
                                }
                            }
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                            Text(text = "Save")
                        }
                    }
                }

                // LazyColumn for displaying records
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
                ) {
                    val sortedRecords = weightRecords.sortedBy{ it.date }

                    items(sortedRecords) { record ->
                        RecordCard(
                            record,
                            onEdit = {
                                weight = TextFieldValue(record.weight.toString())
                                selectedDate = record.date
                            },
                            onDelete = {
                                deleteWeightRecord(record, gymDatabase) {
                                    fetchWeightRecords(gymDatabase) { records ->
                                        weightRecords = records
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun RecordCard(record: WeightRecord, onEdit: () -> Unit, onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(15.dp)),
        backgroundColor = colorResource(id = R.color.weight_card)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                Text(text = "Date: ${record.date}", fontWeight = FontWeight.Bold)
                Text(text = "Day: ${record.dayOfWeek}", fontWeight = FontWeight.Bold)

                }




                Row(
                    horizontalArrangement = Arrangement.End
                ) {

                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }


                    // Dropdown menu for options
                    DropdownMenu(
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.inner_card))
                            .clip(RoundedCornerShape(12.dp)),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {

                        DropdownMenuItem(onClick = {
                            expanded = false
                            showDialog = true
                        }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    modifier = Modifier.padding(bottom = 5.dp),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    )
                                Text("Delete")
                            }
                        }
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
                                        "Are you sure you want to delete this Record?",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            onDelete()
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


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, end = 2.dp, bottom = 10.dp)
                    .height(2.dp)
                    .background(Color.Black)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Weight: ${record.weight} kg", fontWeight = FontWeight.Bold)
            }

        }
    }
}


private fun deleteWeightRecord(
    record: WeightRecord,
    gymDatabase: GymDatabase,
    onComplete: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        gymDatabase.weightDao().delete(record) // Ensure you have a delete method
        onComplete()
    }
}


// Function to fetch weight records
private fun fetchWeightRecords(gymDatabase: GymDatabase, onResult: (List<WeightRecord>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val records = gymDatabase.weightDao()
            .getWeightRecordsByScheduleId(1L) // Replace with actual schedule ID
        onResult(records)
    }
}

private fun saveWeightRecord(
    weightRecord: WeightRecord,
    gymDatabase: GymDatabase,
    onComplete: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        gymDatabase.weightDao().insert(weightRecord)
        onComplete() // Call the onComplete callback after saving
    }
}




