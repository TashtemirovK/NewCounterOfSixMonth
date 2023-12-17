package com.example.YouTube.data.repo

import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.di.provideYoutubeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val repoModules = module {
    single {
        provideYoutubeRepository(get())
    }

    single {
        provideCoroutineScope()
    }

    single {
        provideYouTubeRepository(get(), get())
    }
}

fun provideYouTubeRepository(
    service: YouTubeApiService,
    coroutineScope: CoroutineScope
) = com.example.YouTube.data.repo.YouTubeApiRepo(service)

fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
