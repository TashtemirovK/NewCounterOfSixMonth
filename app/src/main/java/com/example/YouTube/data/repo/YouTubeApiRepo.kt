package com.example.YouTube.data.repo

import com.example.YouTube.data.base.BaseRepository
import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.data.utils.Constants


class YouTubeApiRepo(
    private val service: YouTubeApiService
) : BaseRepository() {

    fun getPlaylists() = doRequest {
        service.getPlaylists(
            BuildConfig.API_KEY,
            Constants.YOUTUBE_RESPONSE_TYPE,
            Constants.CHANNEL_ID,
            Constants.MAX_RESULT
        )
    }

    fun getPlaylistsItems(playlistId: String) = doRequest {
        service.getPlaylistItems(
            apiKey = BuildConfig.API_KEY,
            part = Constants.YOUTUBE_RESPONSE_TYPE,
            playlistId = playlistId,
            maxResults = Constants.MAX_RESULT
        )
    }
}

