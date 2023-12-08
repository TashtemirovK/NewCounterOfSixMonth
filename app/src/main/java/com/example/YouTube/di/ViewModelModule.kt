package com.example.YouTube.di

import com.example.YouTube.ui.playlistitems.PlaylistItemsViewModel
import com.example.YouTube.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        PlaylistsViewModel(get())
    }

    viewModel{
        PlaylistItemsViewModel(get())
    }
}