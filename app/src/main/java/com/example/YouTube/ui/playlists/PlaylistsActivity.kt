package com.example.YouTube.ui.playlists

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YouTube.ui.adapters.PlaylistsAdapter
import com.example.counterofsixmonth.databinding.ActivityPlaylistsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaylistsActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlaylistsBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<PlaylistsViewModel>()

    private val playlistsAdapter by lazy { PlaylistsAdapter() }


    private fun getPlaylists() {
        lifecycleScope.launch {
            viewModel.getPlaylists().observe(this@PlaylistsActivity) { data ->
                data?.let {
                    playlistsAdapter.submitData(
                        lifecycle, it
                    )
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
