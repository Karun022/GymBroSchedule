package com.example.gymbroschedule

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    fun saveSelectedSchedule(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getSelectedSchedule(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}