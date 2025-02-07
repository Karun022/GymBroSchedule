package com.example.gymbroschedule.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gymbroschedule.data.Gym
import kotlinx.coroutines.flow.Flow


@Dao
abstract class GymDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAnInfo(gymEntity: Gym)

    //Loads all info from gym-schedule
    @Query("Select * from `gym-schedule`")
    abstract fun getAllInfo(): Flow<List<Gym>>

    @Update
    abstract suspend fun updateAnInfo(gymEntity: Gym)

    @Delete
    abstract suspend fun deleteAnInfo(gymEntity: Gym)

    @Query("Select * from `gym-schedule` where id=:id")
    abstract fun getInfoById(id:Long): Flow<Gym>
}