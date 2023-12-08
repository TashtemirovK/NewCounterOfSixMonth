package com.example.YouTube.ui.playlistitems

import androidx.lifecycle.ViewModel
import com.example.YouTube.data.repo.YouTubeApiRepo

class PlaylistItemsViewModel(
    private val repo: YouTubeApiRepo
): ViewModel() {

    fun getPlaylistItems(playlistId: String) = repo.getPlaylistsItems(playlistId)
}