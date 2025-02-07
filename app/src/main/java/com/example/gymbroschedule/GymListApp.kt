package com.example.gymbroschedule

import android.app.Application
import com.example.gymbroschedule.Graph.Graph

class GymListApp:Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(applicationContext)
    }
}