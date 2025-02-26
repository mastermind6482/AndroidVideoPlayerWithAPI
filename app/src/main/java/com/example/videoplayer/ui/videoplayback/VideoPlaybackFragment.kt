package com.example.videoplayer.ui.videoplayback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentVideoPlaybackBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class VideoPlaybackFragment : Fragment() {
    private var _binding: FragmentVideoPlaybackBinding? = null
    private val binding get() = _binding!!
    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlaybackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        val videoUrl = arguments?.getString("videoUrl")
        val mediaItem = MediaItem.fromUri(videoUrl ?: "")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
        _binding = null
    }
}