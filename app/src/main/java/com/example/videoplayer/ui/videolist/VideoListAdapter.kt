package com.example.videoplayer.ui.videolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.videoplayer.R
import com.example.videoplayer.data.model.VideoItem

class VideoListAdapter(private val onClick: (VideoItem) -> Unit) :
    ListAdapter<VideoItem, VideoListAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoViewHolder(itemView: View, private val onClick: (VideoItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(video: VideoItem) {
            android.util.Log.d("VideoListAdapter", "Привязка видео: ${video.title}")
            itemView.findViewById<TextView>(R.id.videoTitle).text = video.title
            itemView.findViewById<TextView>(R.id.videoDescription).text = video.description
            itemView.findViewById<ImageView>(R.id.videoThumbnail).load(video.thumbnailUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error)
            }
            itemView.setOnClickListener { onClick(video) }
        }
    }
}

class VideoDiffCallback : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem) = oldItem == newItem
}