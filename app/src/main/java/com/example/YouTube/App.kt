package com.example.YouTube

import android.app.Application
import com.example.YouTube.di.youTubeModule
import com.example.YouTube.di.youTubeModules
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(youTubeModules)
        }
    }
}