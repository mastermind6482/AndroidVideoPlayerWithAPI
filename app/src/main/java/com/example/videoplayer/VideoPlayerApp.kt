package com.example.videoplayer

import android.app.Application
import com.example.videoplayer.data.api.VideoApiService
import com.example.videoplayer.data.local.AppDatabase
import com.example.videoplayer.data.repository.VideoRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideoPlayerApp : Application() {
    lateinit var videoRepository: VideoRepository

    override fun onCreate() {
        super.onCreate()
        // Инициализация API и базы данных вручную
        val apiService = Retrofit.Builder()
            .baseUrl("https://d36fd52e-d7bd-4ae9-9c92-521c1b0f3a3a.mock.pstmn.io") // Твой Mock Server URL из Postman
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY // Логировать тело запроса и ответа
                })
                .build())
            .build()
            .create(VideoApiService::class.java)

        val database = AppDatabase.getInstance(this)
        videoRepository = VideoRepository(apiService, database.videoDao())
    }
}