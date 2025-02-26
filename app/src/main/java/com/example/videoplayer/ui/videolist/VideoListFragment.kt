package com.example.videoplayer.ui.videolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.R
import com.example.videoplayer.data.model.VideoItem
import com.example.videoplayer.VideoPlayerApp
import com.example.videoplayer.databinding.FragmentVideoListBinding

class VideoListFragment : Fragment() {
    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VideoListViewModel by lazy {
        VideoListViewModel((requireActivity().application as VideoPlayerApp).videoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = VideoListAdapter { video ->
            findNavController().navigate(
                R.id.action_videoListFragment_to_videoPlaybackFragment,
                Bundle().apply { putString("videoUrl", video.videoUrl) }
            )
        }
        binding.recyclerView.adapter = adapter

        viewModel.videos.observe(viewLifecycleOwner) { videos ->
            android.util.Log.d("VideoListFragment", "Получено видео: ${videos.size}")
            adapter.submitList(videos)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadVideos()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}