package com.example.YouTube.ui.playlists

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YouTube.data.utils.Resource
import com.example.YouTube.ui.adapters.PlaylistsAdapter
import com.example.counterofsixmonth.databinding.ActivityPlaylistsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistsActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlaylistsBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<PlaylistsViewModel>()

    private val playlistsAdapter by lazy {
        PlaylistsAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initPlaylistsRv()

        viewModel.getPlaylists().observe(this) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, "state.message", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    // show progress bar
                }

                is Resource.Success -> {
                     playlistsAdapter.submitList(state.data)
                }
            }
        }

    }

    private fun initPlaylistsRv() = with(binding.rvPlaylists) {
        layoutManager = LinearLayoutManager(
            this@PlaylistsActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = playlistsAdapter
    }
}