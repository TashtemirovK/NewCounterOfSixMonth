package com.example.YouTube.data.model


import com.google.gson.annotations.SerializedName

data class ContentDetails(
    @SerializedName("videoId")
    val videoId: String,
    @SerializedName("videoPublishedAt")
    val videoPublishedAt: String
)