package com.example.YouTube.data.service

import com.example.YouTube.data.model.BaseYouTubeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("playlist")
    fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: String,
    ): Call<BaseYouTubeResponse>
}