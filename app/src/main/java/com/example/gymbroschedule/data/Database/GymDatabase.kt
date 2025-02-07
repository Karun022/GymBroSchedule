package com.example.gymbroschedule.data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymbroschedule.data.DAO.GymDao
import com.example.gymbroschedule.data.DAO.WeightRecordDao
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.data.WeightRecord


@Database(
    entities = [Gym::class, WeightRecord::class],
    version = 10,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun gymDao(): GymDao
    abstract fun weightDao(): WeightRecordDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gymlist.db"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_5_6, MIGRATION_6_7,
                        MIGRATION_9_10)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

