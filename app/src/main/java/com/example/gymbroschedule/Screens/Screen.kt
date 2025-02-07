package com.example.gymbroschedule.Screens

import androidx.annotation.DrawableRes
import com.example.gymbroschedule.R


sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
        object HomeScreen : Screen("home", "Home", R.drawable.home)
        object AddScreen : Screen("AddScreen", "Add", R.drawable.baseline_add_24) // Removed trailing comma
        object WeightRecord : Screen("weight_record", "Weight", R.drawable.weight)
        object ExerciseListView : Screen("exercise_list", "Exercises", R.drawable.baseline_list_alt_24)
        object MySchedule : Screen("my_schedule", "Schedule", R.drawable.baseline_checklist_24)
}


