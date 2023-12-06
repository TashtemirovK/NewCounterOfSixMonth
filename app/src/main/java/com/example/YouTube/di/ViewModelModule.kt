package com.example.YouTube.di

import com.example.YouTube.ui.playlists.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        MainViewModel(get())
    }
}