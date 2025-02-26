package com.example.videoplayer.data.model

import com.google.gson.annotations.SerializedName

data class VideoItem(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String
)