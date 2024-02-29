package com.example.a30days

import android.app.Application
import com.example.a30days.data.DayRepository

class ThirtyDaysApplication : Application() {
    lateinit var dayRepository: DayRepository

    override fun onCreate() {
        super.onCreate()
        dayRepository = DayRepository
    }
}