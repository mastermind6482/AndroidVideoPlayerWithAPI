package com.example.videoplayer.ui.videolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.data.model.VideoItem
import com.example.videoplayer.data.repository.VideoRepository
import kotlinx.coroutines.launch

class VideoListViewModel(private val repository: VideoRepository) : ViewModel() {
    private val _videos = MutableLiveData<List<VideoItem>>()
    val videos: LiveData<List<VideoItem>> get() = _videos

    init {
        loadVideos()
    }

    fun loadVideos() {
        viewModelScope.launch {
            _videos.value = repository.getVideos()
        }
    }
}