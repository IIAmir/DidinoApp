package com.example.didinoapp.view.activities.videoplayer

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.didinoapp.databinding.ActivityMovieAndSeriesPlayerBinding
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModel
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MovieAndSeriesPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieAndSeriesPlayerBinding
    private lateinit var exoPlayer: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieAndSeriesPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val previewLink = intent.getStringExtra("PREVIEW_VIDEO_KEY").toString()
        setUpExoPlayer(previewLink)
    }

    private fun setUpExoPlayer(uri: String) {
        val renderersFactory = DefaultRenderersFactory(this)
        exoPlayer = ExoPlayer.Builder(this,renderersFactory).setHandleAudioBecomingNoisy(true).build()
        val mediaItem = MediaItem.fromUri(Uri.parse(uri))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        binding.movieAndSeriesPlayer.player = exoPlayer
        exoPlayer.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }
}