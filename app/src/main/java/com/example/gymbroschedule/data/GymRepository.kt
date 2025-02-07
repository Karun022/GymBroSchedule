package com.example.gymbroschedule.data

import android.content.Context
import com.example.gymbroschedule.data.DAO.GymDao
import com.example.gymbroschedule.data.Database.GymDatabase
import kotlinx.coroutines.flow.Flow


class GymRepository(context: Context) {
    private val gymDao: GymDao = GymDatabase.getDatabase(context).gymDao()

   suspend fun addAnInfo(gym: Gym){
        gymDao.addAnInfo(gym)
    }

    fun getAnInfo(): Flow<List<Gym>> = gymDao.getAllInfo()

    fun getAnInfoById(id:Long):Flow<Gym>{
        return gymDao.getInfoById(id)
    }

    suspend fun updateANInfo(gym: Gym){
        gymDao.updateAnInfo(gym)
    }

    suspend fun deleteAnInfo(gym: Gym){
        gymDao.deleteAnInfo(gym)
    }
}