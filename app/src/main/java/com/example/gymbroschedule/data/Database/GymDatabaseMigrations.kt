package com.example.gymbroschedule.data.Database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym_Schedule` ADD COLUMN `gym_exercise1R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym_Schedule` ADD COLUMN `gym_exercise1R3` TEXT NOT NULL DEFAULT ''")
        // Add other columns as necessary...
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE weight_records (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                scheduleId INTEGER NOT NULL,
                weight REAL NOT NULL,
                date INTEGER NOT NULL,
                FOREIGN KEY(scheduleId) REFERENCES `Gym_Schedule`(id) ON DELETE CASCADE
            )
            """
        )
    }
}

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE weight_records ADD COLUMN dayOfWeek TEXT NOT NULL DEFAULT 'Unknown'")
    }
}

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE user_preferences (
                id INTEGER PRIMARY KEY NOT NULL,
                selectedSchedule TEXT NOT NULL
            )
            """
        )
    }
}

val MIGRATION_9_10 = object : Migration(9, 10) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra1 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra2 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra3 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra4 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra5 TEXT NOT NULL DEFAULT ''")
    }
}
