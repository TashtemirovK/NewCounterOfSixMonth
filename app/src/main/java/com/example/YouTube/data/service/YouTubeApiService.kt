package com.example.YouTube.data.service

import com.example.YouTube.data.model.BaseYouTubeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("playlist")
    suspend fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<BaseYouTubeResponse>
}