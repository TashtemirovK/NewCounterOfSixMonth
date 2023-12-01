package com.example.YouTube.di

import com.example.YouTube.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val youTubeRepository = module {

    single {
        AppModule.provideYoutubeApiService(get())
    }

    single {
        AppModule.provideRetrofitClient(get())
    }

    single {
        AppModule.provideOkhttpClient(get())
    }

    single {
        AppModule.provideLoggingInterceptor()
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