package com.example.videoplayer.data.repository

import com.example.videoplayer.data.api.VideoApiService
import com.example.videoplayer.data.local.VideoDao
import com.example.videoplayer.data.local.VideoEntity
import com.example.videoplayer.data.model.VideoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(
    private val apiService: VideoApiService,
    private val videoDao: VideoDao
) {
    suspend fun getVideos(): List<VideoItem> = withContext(Dispatchers.IO) {
        try {
            val videos = apiService.getVideos()
            videoDao.insertVideos(videos.map { it.toEntity() })
            videos
        } catch (e: Exception) {
            videoDao.getVideos().map { it.toItem() }
        }
    }
}

fun VideoItem.toEntity() = VideoEntity(id, title, description, thumbnailUrl, videoUrl)
fun VideoEntity.toItem() = VideoItem(id, title, description, thumbnailUrl, videoUrl)