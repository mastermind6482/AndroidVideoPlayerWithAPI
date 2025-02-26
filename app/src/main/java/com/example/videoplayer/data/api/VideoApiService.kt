package com.example.videoplayer.data.api

import com.example.videoplayer.data.model.VideoItem
import retrofit2.http.GET

interface VideoApiService {
    @GET("videos")
    suspend fun getVideos(): List<VideoItem>
}