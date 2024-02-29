package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.BusDatabase

class BusScheduleApplication: Application() {
    val database: BusDatabase by lazy { BusDatabase.getDatabase(this) }
}