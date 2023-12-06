package com.example.YouTube.di

import com.example.YouTube.data.repo.YouTubeApiRepo
import com.example.YouTube.data.service.YouTubeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val youTubeModules = listOf(youTubeNetworkModule, youTubeRepository, viewModelModule)


