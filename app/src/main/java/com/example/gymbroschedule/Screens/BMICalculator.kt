package com.example.gymbroschedule.Screens


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BMICalculator(navController: NavController) {
    var weight by remember { mutableStateOf(TextFieldValue("")) }
    var feet by remember { mutableStateOf(TextFieldValue("")) }
    var inches by remember { mutableStateOf(TextFieldValue("")) }
    var isMetric by remember { mutableStateOf(true) }
    var bmi by remember { mutableStateOf<Double?>(null) }
    var bmiLabel by remember { mutableStateOf("") }
    var bmiDescription by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(2) }

    Scaffold(
        topBar = { AppBarView(title = "Calculate BMI") { navController.navigateUp() } },
        backgroundColor = colorResource(id = R.color.background),
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Black)
                    .padding(bottom = 28.dp) // Lower the bottom bar for the curve effect
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top // Align to the top for better visibility
                ) {

                    // First item
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedIndex = 0
                                navController.navigate(Screen.HomeScreen.route)
                            },
                        contentAlignment = Alignment.TopCenter // Align the content to the top center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween // Space between icon and text
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        color = if (selectedIndex == 0) Color.Green else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home",
                                    tint = if (selectedIndex == 0) Color.Black else Color.White
                                )
                            }
                            Text("Home", color = if (selectedIndex == 0) Color.Green else Color.White, fontSize = 12.sp) // Set font size to avoid overflow
                        }
                    }

                    // Second item
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedIndex = 1
                                navController.navigate(Screen.ExerciseListView.route)
                            },
                        contentAlignment = Alignment.TopCenter // Align the content to the top center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        color = if (selectedIndex == 1) Color.Green else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Icon(
                                    painter = painterResource(id = R.drawable.baseline_list_alt_24),
                                    contentDescription = "Schedule",
                                    tint = if (selectedIndex == 1) Color.Black else Color.White
                                )
                            }
                            Text("Schedule", color = if (selectedIndex == 1) Color.Green else Color.White, fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f)) // Space for the central button

                    // Third item
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedIndex = 2
                               // navController.navigate(Screen.BMIScreen.route)
                            },
                        contentAlignment = Alignment.TopCenter // Align the content to the top center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        color = if (selectedIndex == 2) Color.Green else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Icon(
                                    painter = painterResource(id = R.drawable.baseline_speed_24),
                                    contentDescription = "BMI",
                                    tint = if (selectedIndex == 2) Color.Black else Color.White
                                )
                            }
                            Text("BMI", color = if (selectedIndex == 2) Color.Green else Color.White, fontSize = 12.sp)
                        }
                    }

                    // Fourth item
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedIndex = 3
                                navController.navigate(Screen.WeightRecord.route)
                            },
                        contentAlignment = Alignment.TopCenter // Align the content to the top center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        color = if (selectedIndex == 3) Color.Green else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.material3.Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.weightscale),
                                    contentDescription = "Weight",
                                    tint = if (selectedIndex == 3) Color.Black else Color.White
                                )
                            }
                            Text("Weight", color = if (selectedIndex == 3) Color.Green else Color.White, fontSize = 12.sp)
                        }
                    }
                }

                // Central button positioned above the bottom bar
                FloatingActionButton(
                    onClick = {
                        selectedIndex = -1
                        navController.navigate(Screen.AddScreen.route + "/0L")
                        /* Handle Scan & Pay action */ },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = -26.dp), // Adjust vertical offset to create overlap
                    contentColor = Color.White,
                    backgroundColor = colorResource(id = R.color.floating_button_green),
                    shape = CircleShape
                ) {
                    androidx.compose.material3.Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Schedule"
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 18.dp, end = 18.dp, top = 60.dp, bottom = 200.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding()
                        .clip(shape = RoundedCornerShape(18.dp)),
                    backgroundColor = colorResource(id = R.color.outter_card)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var isMetric by remember { mutableStateOf(true) }

// Update transition for animating the green indicator between buttons
                        val transition = updateTransition(targetState = isMetric, label = "buttonTransition")
                        val offsetX by transition.animateDp(
                            transitionSpec = { tween(durationMillis = 300) },
                            label = "offsetX"
                        ) { if (it) 0.dp else 210.dp } // Adjust to button width

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            // Animated sliding indicator background behind the text
                            Box(
                                modifier = Modifier
                                    .offset(x = offsetX)
                                    .fillMaxHeight()
                                    .fillMaxWidth(fraction = 0.5f)
                                    .background(color = Color(0xFF00FF00), shape = RoundedCornerShape(50.dp))
                            )

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = { isMetric = true },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent, // Make the button background transparent
                                        contentColor = if (isMetric) Color.White else Color.Gray // Change text color based on selection
                                    ),
                                    shape = RoundedCornerShape(50.dp), // Rounded corners
                                    elevation = null, // Remove elevation
                                    border = BorderStroke(0.dp, Color.Transparent), // Remove border
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(text = "METRIC UNITS")
                                }

                                Button(
                                    onClick = { isMetric = false },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent, // Make the button background transparent
                                        contentColor = if (!isMetric) Color.White else Color.Gray // Change text color based on selection
                                    ),
                                    shape = RoundedCornerShape(50.dp), // Rounded corners
                                    elevation = null, // Remove elevation
                                    border = BorderStroke(0.dp, Color.Transparent), // Remove border
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(text = "US UNITS")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Input for weight
                        OutlinedTextField(
                            value = weight,
                            onValueChange = { weight = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text(text = if (isMetric) "WEIGHT (in kg)" else "WEIGHT (in lbs)") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = colorResource(id = R.color.Text),
                                focusedBorderColor = colorResource(id = R.color.Text),
                                unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                                cursorColor = colorResource(id = R.color.Text),
                                focusedLabelColor = colorResource(id = R.color.Text),
                                unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Input for height in feet and inches
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Feet Input
                            OutlinedTextField(
                                value = feet,
                                onValueChange = { feet = it },
                                label = { Text(text = "Feet") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = colorResource(id = R.color.Text),
                                    focusedBorderColor = colorResource(id = R.color.Text),
                                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                                    cursorColor = colorResource(id = R.color.Text),
                                    focusedLabelColor = colorResource(id = R.color.Text),
                                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                                )
                            )

                            // Inches Input
                            OutlinedTextField(
                                value = inches,
                                onValueChange = { inches = it },
                                label = { Text(text = "Inches") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = colorResource(id = R.color.Text),
                                    focusedBorderColor = colorResource(id = R.color.Text),
                                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                                    cursorColor = colorResource(id = R.color.Text),
                                    focusedLabelColor = colorResource(id = R.color.Text),
                                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Button to calculate BMI
                        Button(
                            onClick = {
                                val weightValue = weight.text.toDoubleOrNull()
                                val feetValue = feet.text.toDoubleOrNull()
                                val inchesValue = inches.text.toDoubleOrNull()

                                if (weightValue != null && feetValue != null && inchesValue != null) {
                                    // Calculate total height in inches
                                    val totalHeightInInches = (feetValue * 12) + inchesValue

                                    // Calculate BMI based on unit system
                                    bmi = if (isMetric) {
                                        // Convert height from inches to meters
                                        val heightInMeters = totalHeightInInches * 0.0254
                                        calculateMetricBMI(weightValue, heightInMeters)
                                    } else {
                                        // Calculate BMI with Imperial units (height in inches)
                                        calculateImperialBMI(weightValue, totalHeightInInches)
                                    }

                                    // Display BMI result
                                    displayBMIResult(bmi!!.toFloat(), onLabelChanged = { label -> bmiLabel = label }, onDescriptionChanged = { description -> bmiDescription = description })
                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00FF00))
                        ) {
                            Text(text = "CALCULATE")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(30.dp))

                // Display BMI result
                bmi?.let {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(10.dp)),
                        backgroundColor = Color.Blue,
                    ) {
                        Column {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(modifier = Modifier.padding(top = 8.dp), text = "Your BMI is %.2f".format(it), fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                        }

                        if (bmiLabel.isNotEmpty() && bmiDescription.isNotEmpty()) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                backgroundColor = Color.Yellow
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = bmiLabel,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                    Text(text = bmiDescription)
                                }
                            }
                        }
                        }
                    }
                }

            }
        }
    )
}


private fun displayBMIResult(bmi: Float, onLabelChanged: (String) -> Unit, onDescriptionChanged: (String) -> Unit) {
    val (bmiLabel, bmiDescription) = when {
        bmi <= 15f -> "Very severely underweight" to "Oops! You really need to take better care of yourself! Eat more."
        bmi > 15f && bmi <= 16f -> "Severely underweight" to "Oops! You really need to take better care of yourself! Eat more."
        bmi > 16f && bmi <= 18.5f -> "Underweight" to "You need to gain some weight. Try to eat more nutritious food."
        bmi > 18.5f && bmi <= 25f -> "Normal" to "Congratulations! You are in a healthy weight range."
        bmi > 25f && bmi <= 30f -> "Overweight" to "You need to be cautious. Try exercising and watching your diet."
        bmi > 30f && bmi <= 35f -> "Moderately obese" to "You should work on reducing weight with a proper diet and exercise."
        bmi > 35f && bmi <= 40f -> "Severely obese" to "You need to consult a doctor. Health risks are high."
        else -> "Very severely obese" to "You are at a very high health risk. Please consult a doctor immediately."
    }

    onLabelChanged(bmiLabel)
    onDescriptionChanged(bmiDescription)
}



// Function to calculate BMI using Metric units
fun calculateMetricBMI(weight: Double, height: Double): Double {
    return weight / (height * height)
}

// Function to calculate BMI using Imperial units
fun calculateImperialBMI(weight: Double, height: Double): Double {
    return (weight / (height * height)) * 703
}