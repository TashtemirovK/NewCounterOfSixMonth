package com.example.YouTube.data.repo

import com.example.YouTube.di.AppModule

class RepoModules {
    val repoModules = AppModule {
        single {Repository (get())}
    }
}