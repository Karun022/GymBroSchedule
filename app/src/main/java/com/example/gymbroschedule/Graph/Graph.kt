package com.example.gymbroschedule.Graph

import android.content.Context
import androidx.room.Room
import com.example.gymbroschedule.data.Database.GymDatabase
import com.example.gymbroschedule.data.Database.MIGRATION_1_2
import com.example.gymbroschedule.data.Database.MIGRATION_2_3
import com.example.gymbroschedule.data.Database.MIGRATION_5_6
import com.example.gymbroschedule.data.Database.MIGRATION_6_7
import com.example.gymbroschedule.data.Database.MIGRATION_9_10
import com.example.gymbroschedule.data.GymRepository

object Graph {

    lateinit var gymRepository: GymRepository
        private set

    private lateinit var gymdatabase: GymDatabase

    private var initialized = false

    fun provide(context: Context) {
        if (!initialized) {
            gymdatabase = Room.databaseBuilder(context, GymDatabase::class.java, "gymlist.db")
                .addMigrations(
                    MIGRATION_1_2,
                    MIGRATION_2_3,
                    MIGRATION_5_6,
                    MIGRATION_6_7,
                    MIGRATION_9_10 // Add all your migration objects here
                )
                .build()

            gymRepository = GymRepository(context)
            initialized = true
        }
    }

    fun getDatabase(): GymDatabase = gymdatabase
}