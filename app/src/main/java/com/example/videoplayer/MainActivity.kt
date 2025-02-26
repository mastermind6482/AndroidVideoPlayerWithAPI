package com.example.videoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoplayer.ui.videolist.VideoListFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.videoplayer.data.repository.VideoRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем NavHostFragment и инициализируем навигацию
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (savedInstanceState == null) {
            // Устанавливаем начальный фрагмент вручную
            navController.setGraph(R.navigation.nav_graph)
            navController.navigate(R.id.videoListFragment)
        }
    }

    // Доступ к VideoRepository (например, для передачи в фрагменты)
    val videoRepository: VideoRepository
        get() = (application as VideoPlayerApp).videoRepository
}