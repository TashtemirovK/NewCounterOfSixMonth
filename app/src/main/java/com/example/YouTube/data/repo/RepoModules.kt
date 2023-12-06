package com.example.YouTube.data.repo

class RepoModules {
    val repoModules = AppModule {
        single {Repository (get())}
    }
}