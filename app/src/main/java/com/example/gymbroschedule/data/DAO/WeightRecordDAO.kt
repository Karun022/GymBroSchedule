package com.example.gymbroschedule.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymbroschedule.data.WeightRecord

@Dao
interface WeightRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Replace the record if it exists
    suspend fun insert(weightRecord: WeightRecord)

    @Query("SELECT * FROM weight_records WHERE scheduleId = :scheduleId")
    suspend fun getWeightRecordsByScheduleId(scheduleId: Long): List<WeightRecord>

    @Query("SELECT * FROM weight_records WHERE id = :id LIMIT 1")
    suspend fun getWeightRecordById(id: Long): WeightRecord?

    @Query("DELETE FROM weight_records WHERE id = :id")
    suspend fun deleteWeightRecordById(id: Long)

    @Query("DELETE FROM weight_records WHERE scheduleId = :scheduleId")
    suspend fun deleteWeightRecordsByScheduleId(scheduleId: Long)

    @Delete
    suspend fun delete(weightRecord: WeightRecord)

    @Query("SELECT * FROM weight_records ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestWeightRecord(): WeightRecord?
}