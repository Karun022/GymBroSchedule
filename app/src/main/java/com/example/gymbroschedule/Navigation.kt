package com.example.gymbroschedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gymbroschedule.Screens.AddEditDetailView
import com.example.gymbroschedule.Screens.BMICalculator
import com.example.gymbroschedule.Screens.ExerciseView
import com.example.gymbroschedule.Screens.HomeView
import com.example.gymbroschedule.Screens.MyExerciseView
import com.example.gymbroschedule.Screens.Screen
import com.example.gymbroschedule.Screens.WeightScale
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Database.GymDatabase

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(gymViewModel: GymViewModel = viewModel(),
               navController: NavHostController = rememberNavController(),
               gymDatabase: GymDatabase = GymDatabase.getDatabase(context = LocalContext.current)
){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route,
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController, gymViewModel, gymDatabase)
        }

        composable(Screen.WeightRecord.route){
            WeightScale( gymDatabase = gymDatabase ,navController = navController)
        }

//        composable(Screen.BMIScreen.route){
//            BMICalculator(navController =  navController)
//        }

        composable(Screen.ExerciseListView.route) {
            ExerciseView(navController = navController, viewModel = gymViewModel)
        }

        composable(Screen.MySchedule.route){
            MyExerciseView(navController = navController, viewModel = gymViewModel)
        }

        composable(Screen.AddScreen.route + "/{id}", arguments = listOf(navArgument("id"){
            type = NavType.LongType
            defaultValue = 0L
            nullable = false
        })){entry->
            val id = if(entry.arguments !=null) entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = gymViewModel , navController = navController)
        }

    }
}