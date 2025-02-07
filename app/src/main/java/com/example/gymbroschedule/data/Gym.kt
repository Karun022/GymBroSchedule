package com.example.gymbroschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gym-Schedule")
data class  Gym(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name = "gym-day")
    val Day:String = "",
    @ColumnInfo(name = "gym-workout")
    val Workout:String = "",

    @ColumnInfo(name = "gym-exercise1")
    val Exercise1:String = "",
    @ColumnInfo(name = "gym-exercise1R2")
    val Exercise1R2:String = "",
    @ColumnInfo(name = "gym-exercise1R3")
    val Exercise1R3:String = "",

    @ColumnInfo(name = "gym-exercise2")
    val Exercise2:String = "",
    @ColumnInfo(name = "gym-exercise2R2")
    val Exercise2R2:String = "",
    @ColumnInfo(name = "gym-exercise2R3")
    val Exercise2R3:String = "",

    @ColumnInfo(name = "gym-exercise3")
    val Exercise3:String = "",
    @ColumnInfo(name = "gym-exercise3R2")
    val Exercise3R2:String = "",
    @ColumnInfo(name = "gym-exercise3R3")
    val Exercise3R3:String = "",

    @ColumnInfo(name = "gym-exercise4")
    val Exercise4:String = "",
    @ColumnInfo(name = "gym-exercise4R2")
    val Exercise4R2:String = "",
    @ColumnInfo(name = "gym-exercise4R3")
    val Exercise4R3:String = "",

    @ColumnInfo(name = "gym-exercise5")
    val Exercise5:String = "",
    val Exercise5r2:String = "",
    val Exercise5R3:String = "",

    @ColumnInfo(name = "extra-exercise")
    val ExtraExercise: String = "",
    val Extra1:String = "",
    val Extra2:String = "",
    val Extra3:String = "",
    val Extra4:String = "",
    val Extra5:String = "",


    )

