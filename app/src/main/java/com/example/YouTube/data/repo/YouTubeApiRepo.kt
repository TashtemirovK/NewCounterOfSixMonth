package com.example.YouTube.data.repo

import com.example.YouTube.data.model.BaseYouTubeResponse
import com.example.YouTube.data.model.Item
import com.example.YouTube.data.service.YouTubeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class YouTubeApiRepo @Inject constructor(
    private val service: YouTubeApiService
) {
    fun getPlaylists(): List<Item>? {
        var mResponse: List<Item>? = null

        service.getPlaylists("AIzaSyBQxS_0xp53642D_nS9qsgLwhoXyrU8tyE",
            "snippet, contentDetails",
            "UCKNTZMRHPLXfqlbd0I7mCkg",
            "25"
        ).enqueue(
            object : Callback<BaseYouTubeResponse> {
                override fun onResponse(
                    call: Call<BaseYouTubeResponse>,
                    response: Response<BaseYouTubeResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        mResponse = response.body()!!.items
                    }
                }
                override fun onFailure(call: Call<BaseYouTubeResponse>, t: Throwable) {
                }
            }
        )
        return mResponse
    }

}