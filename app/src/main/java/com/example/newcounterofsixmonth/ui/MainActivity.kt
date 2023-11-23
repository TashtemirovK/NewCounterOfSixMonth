package com.example.newcounterofsixmonth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.counterofsixmonth.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnInc.setOnClickListener {
            viewModel.counterLV.value?.minus(1)
            viewModel.inc()
        }

        binding.btnInc.setOnClickListener {
            viewModel.dec()
        }

        viewModel.counterLV.observe(this) { result ->
            binding.tvResult.text = result.toString()
        }
    }
}