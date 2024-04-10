package com.example.trivia

import android.app.Application
import com.example.trivia.data.AppContainer

class TriviaApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer.DefaultAppContainer()
    }
}