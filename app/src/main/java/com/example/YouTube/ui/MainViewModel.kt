package com.example.YouTube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.repo.YouTubeApiRepo
import com.example.YouTube.data.utils.Resource



class MainViewModel (
    private val youTubeApiRepo: YouTubeApiRepo
) : ViewModel() {

    private val _playlistState = MutableLiveData<Resource<List<Item>>>()
    val playlistsState: LiveData<Resource<List<Item>>> = _playlistState

//    fun getPlaylist() {
//        _playlistState.value = youTubeApiRepo.getPlaylists().value
        /*viewModelScope.launch(Dispatchers.IO) {
            youTubeApiRepo.getPlaylists().asFlow().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _playlistState.value = Resource.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _playlistState.value = Resource.Loading()
                    }

                    is Resource.Success -> {
                        _playlistState.value = Resource.Success(res.data!!)
                    }
                }
            }*/
//    }
    fun getPlaylists(): LiveData<Resource<List<Item>>> = youTubeApiRepo.getPlaylists()
}
