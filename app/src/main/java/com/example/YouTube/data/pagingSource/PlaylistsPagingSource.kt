package com.example.youtube_api_hm_6mounth.data.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.utils.Constants
import com.example.youtube_api_hm_6mounth.BuildConfig
import com.example.youtube_api_hm_6mounth.data.service.YoutubeApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PlaylistPagingSource(
    private val service: YoutubeApiService,
    private val coroutineScope: CoroutineScope,
) : PagingSource<String, Item>() {

    private val STARTING_PAGE_TOKEN = " "
    private val playlistId = ""

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        val pageToken = params.key ?: STARTING_PAGE_TOKEN
        return try {
            val response = service.getPlaylistItems(
                apiKey = BuildConfig.API_KEY,
                part = Constants.PART,
                playlistId = playlistId,
                maxResults = Constants.MAX_RESULT,
            )

            val nextKey = response.body()?.nextPageToken
            val prevKey = if (pageToken == STARTING_PAGE_TOKEN) null else response.body()?.prevPageToken

            Log.d("PlaylistPagingSource", "Load Success - Data Size: ${response.body()?.items?.size}")

            LoadResult.Page(
                data = response.body()?.items ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.e("PlaylistPagingSource", "Load Error: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override  fun getRefreshKey(state: PagingState<String, Item>): String? {
        var current: String? = " "
        val anchorPosition = state.anchorPosition

        coroutineScope.launch {
            if (anchorPosition != null) {
                current = state.closestPageToPosition(anchorPosition)?.prevKey?.let {
                    service.getPlaylistItems(
                        apiKey = BuildConfig.API_KEY,
                        part = Constants.PART,
                        playlistId = playlistId,
                        maxResults = Constants.MAX_RESULT
                    ).body()?.nextPageToken
                }
            }
        }
        return current
    }
}