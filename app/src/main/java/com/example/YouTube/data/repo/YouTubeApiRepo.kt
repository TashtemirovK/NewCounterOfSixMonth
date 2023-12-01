package com.example.YouTube.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.YouTube.data.model.BaseYouTubeResponse
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class YouTubeApiRepo (
    private val service: YouTubeApiService
) {
    fun getPlaylists(): LiveData<Resource<List<Item>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = service.getPlaylists(
                "AIzaSyBQxS_0xp53642D_nS9qsgLwhoXyrU8tyE",
                "snippet, contentDetails",
                "UCKNTZMRHPLXfqlbd0I7mCkg",
                "25"
            )
            if (response.body() != null && response.isSuccessful) {
                emit(Resource.Success(response.body()!!.items)) 
            } else {
                emit(Resource.Error("Unknown error"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}