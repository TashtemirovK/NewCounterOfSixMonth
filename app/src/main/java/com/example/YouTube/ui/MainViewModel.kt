package com.example.YouTube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.repo.YouTubeApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val youTubeApiRepo: YouTubeApiRepo
) : ViewModel() {

    private val _playlistState = MutableLiveData<List<Item>>()
    val playlistsState: LiveData<List<Item>> = _playlistState

    fun getPlaylist(){
        _playlistState.value = youTubeApiRepo.getPlaylists()
    }

}