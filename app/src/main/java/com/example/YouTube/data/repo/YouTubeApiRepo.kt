package com.example.YouTube.data.repo

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.YouTube.data.base.BaseRepository
import com.example.YouTube.data.pagingSource.YouTubePagingSource
import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.data.utils.Constants
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.CoroutineScope


class YouTubeApiRepo(
    private val service: YouTubeApiService
    private val coroutineScope: CoroutineScope
) : BaseRepository() {
    fun getPlaylists(): LiveData<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                YouTubePagingSource(service, CoroutineScope)
            }
        ).liveData
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

