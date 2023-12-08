package com.example.YouTube.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArguments(intent)
        initViews()
        setupRequests()
        initClickListeners()
    }

    protected open fun getArguments(intent: Intent) {}
    protected open fun initViews() {}
    protected open fun setupRequests() {}
    protected open fun initClickListeners() {}
}