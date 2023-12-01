package com.example.YouTube.di

import com.example.YouTube.data.repo.YouTubeApiRepo
import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.di.AppModule.provideLoggingInterceptor
import com.example.YouTube.di.AppModule.provideOkhttpClient
import com.example.YouTube.di.AppModule.provideRetrofitClient
import com.example.YouTube.di.AppModule.provideYoutubeApiService
import com.example.YouTube.ui.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val youTubeModule = module {

    //NetWork
    single {
        provideYoutubeApiService(get())
    }

    single {
        provideRetrofitClient(get())
    }

    single {
        provideOkhttpClient(get())
    }

    single {
        provideLoggingInterceptor()
    }

    // Repositories
    single {
        provideYoutubeRepository(get())
    }

    // ViewModel
    viewModel {
        MainViewModel(get())
    }

}

fun provideRetrofitClient(
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://rickandmortyapi.com/api")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()


fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


fun provideYoutubeApiService(
    retrofit: Retrofit
): YouTubeApiService = retrofit.create(YouTubeApiService::class.java)

fun provideYoutubeRepository(
    service: YouTubeApiService
) = YouTubeApiRepo(service)
