package com.example.gymbroschedule.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.data.GymRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.gymbroschedule.Graph.Graph

class GymViewModel(
    private val gymRepository: GymRepository = Graph.gymRepository
) : ViewModel() {




    var gymDayState by mutableStateOf("")
    var gymWorkoutState by mutableStateOf("")

    var Exercise1 by mutableStateOf("")
    var Exercise1R2 by mutableStateOf("")
    var Exercise1R3 by mutableStateOf("")

    var Exercise2 by mutableStateOf("")
    var Exercise2R2 by mutableStateOf("")
    var Exercise2R3 by mutableStateOf("")

    var Exercise3 by mutableStateOf("")
    var Exercise3R2 by mutableStateOf("")
    var Exercise3R3 by mutableStateOf("")

    var Exercise4 by mutableStateOf("")
    var Exercise4R2 by mutableStateOf("")
    var Exercise4R3 by mutableStateOf("")

    var Exercise5 by mutableStateOf("")
    var Exercise5R2 by mutableStateOf("")
    var Exercise5R3 by mutableStateOf("")

    var ExtraExercise by mutableStateOf("")

    lateinit var getAllInfo: Flow<List<Gym>>

    init {
        viewModelScope.launch {
            getAllInfo = gymRepository.getAnInfo()
        }
    }



    fun onGymDayChanged(newString: String) {
        gymDayState = newString
    }

    fun onGymWorkoutChanged(newString: String) {
        gymWorkoutState = newString
    }

    fun onExercise1Changed(newString: String) {
        Exercise1 = newString
    }

    fun onExercise1R2Changed(newString: String) {
        Exercise1R2 = newString
    }

    fun onExercise1R3Changed(newString: String) {
        Exercise1R3 = newString
    }

    fun onExercise2Changed(newString: String) {
        Exercise2 = newString
    }

    fun onExercise2R2Changed(newString: String) {
        Exercise2R2 = newString
    }

    fun onExercise2R3Changed(newString: String) {
        Exercise2R3 = newString
    }

    fun onExercise3Changed(newString: String) {
        Exercise3 = newString
    }

    fun onExercise3R2Changed(newString: String) {
        Exercise3R2 = newString
    }

    fun onExercise3R3Changed(newString: String) {
        Exercise3R3 = newString
    }

    fun onExercise4Changed(newString: String) {
        Exercise4 = newString
    }

    fun onExercise4R2Changed(newString: String) {
        Exercise4R2 = newString
    }

    fun onExercise4R3Changed(newString: String) {
        Exercise4R3 = newString
    }

    fun onExercise5Changed(newString: String) {
        Exercise5 = newString
    }

    fun onExercise5R2Changed(newString: String) {
        Exercise5R2 = newString
    }

    fun onExercise5R3Changed(newString: String) {
        Exercise5R3 = newString
    }

    fun onExtraExerciseChanged(newString: String) {
        ExtraExercise = newString
    }

    fun addAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.addAnInfo(gym = gym)
        }
    }

    fun updateAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.updateANInfo(gym = gym)
        }
    }

    fun getAnInfoById(id: Long): Flow<Gym> {
        return gymRepository.getAnInfoById(id)
    }

    fun deleteAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.deleteAnInfo(gym = gym)
        }
    }


}