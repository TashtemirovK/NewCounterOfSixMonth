package com.example.YouTube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.repo.YouTubeApiRepo
import com.example.YouTube.data.utils.Resource



class PlaylistsViewModel (
    private val youTubeApiRepo: YouTubeApiRepo
) : ViewModel() {

    fun getPlaylists(): LiveData<PagingData<Item>> {
        return YouTubeApiRepo.getPlaylists().cachedIn(viewModelScope)
    }
}
