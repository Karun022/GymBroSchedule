package com.example.gymbroschedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_records")
data class WeightRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val scheduleId: Long, // Foreign key to link to Gym-Schedule
    val weight: Float,
    val date: String, // This can be a String for the formatted date
    val dayOfWeek: String,
    val timestamp: Long // Store the timestamp as a Long
)