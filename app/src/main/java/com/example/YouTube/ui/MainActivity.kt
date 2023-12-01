package com.example.YouTube.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.YouTube.data.utils.Resource
import com.example.counterofsixmonth.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var adapter: FragmentStateAdapter

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getPlaylists().observe(this) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, "state.message", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    // show progress bar
                }

                is Resource.Success -> {
                    // adapter submitList(state.data)
                    Log.e("Kad", "error: ${state.data}", )
                }
            }
        }

    }
}