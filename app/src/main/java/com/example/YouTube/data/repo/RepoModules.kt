package com.example.YouTube.data.repo

import org.koin.dsl.module

class RepoModules {
    val repoModules = module {
        single { Repository(get()) }
    }
}
