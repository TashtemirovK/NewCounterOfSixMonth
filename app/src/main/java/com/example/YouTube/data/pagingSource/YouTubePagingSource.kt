package com.example.YouTube.data.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.service.YouTubeApiService
import com.example.YouTube.data.utils.Constants

private const val STARTING_PAGE_TOKEN = " "

class YouTubePagingSource(
    private val apiService: YouTubeApiService,
    private val coroutineScope: corouteneScope
) : PagingSource<String, Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        val pageToken = params.key ?: STARTING_PAGE_TOKEN
        return try {
            val response = apiService.getPlaylists(
                page = pageToken,
                apiKey = BuildConfig.API_KEY,
                part = Constants.YOUTUBE_RESPONSE_TYPE,
                playlistId = playlistId,
                maxResults = Constants.MAX_RESULT
            )

            val nextKey = if (response.body()?.items?.isEmpty() == true) null
            else response.body()?.nextPageToken
            val prevKey = if (pageToken == STARTING_PAGE_TOKEN) null
            else response.body().prevPageToken


            LoadResult.Page(
                data = response.body() ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Item>): String? {
        var current: String? = " "

        val anchorPosition = state.anchorPosition

        coroutineScope.launch {
            if (anchorPosition != null) {
                current = state.closestPageToPosition(anchorPosition)?.prevKey?.let {
                    apiService.getPlaylists(
                        page = it,
                        apiKey = BuildConfig.API_KEY,
                        part = Constants.YOUTUBE_RESPONSE_TYPE,
                        channelID = Constants.CHANNEL_ID,
                        maxResults = Constants.MAX_RESULT
                    ).body()?.nextPageToken
                }
            }
        }
        return current
    }
}