package com.example.YouTube.di

import com.example.YouTube.data.repo.YouTubeApiRepo
import com.example.YouTube.data.service.YouTubeApiService
import org.koin.dsl.module

val youTubeRepository = module {

    // Repositories
    single {
        provideYoutubeRepository(get())
    }
}

fun provideYouTubeRepository(
    service: YouTubeApiService
) = YouTubeApiRepo(service)