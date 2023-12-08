package com.example.YouTube.ui.playlistitems

import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.viewModels
import com.example.YouTube.data.utils.Resource
import com.example.counterofsixmonth.databinding.ActivityPlaylistItemsBinding

class BaselistItemsActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlaylistItemsBinding.inflate(layoutInflater)
    }

    private val viewModel: PlaylistItemsViewModel by viewModels()

    private var playlistId = ""
    private fun getArguments() {
        intent.getStringExtra("playlistIdKEY")?.let {
            playlistId = it
        }
    }

    private fun setupRequests() {
        getPlaylistItems()
    }

    private fun getPlaylistItems() {
        viewModel.getPlaylistItems(playlistId).observe(this) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, state.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    // You can handle loading state if needed
                }

                is Resource.Success -> {
   //                 playlistsAdapter.submitList(state.data)
                }
            }
        }
    }
}





