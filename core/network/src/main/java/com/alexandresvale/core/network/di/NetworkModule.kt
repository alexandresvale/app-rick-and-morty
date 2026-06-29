package com.alexandresvale.core.network.di

import com.alexandresvale.core.network.NetworkClient
import org.koin.dsl.module

val networkModule = module {
    single {
        NetworkClient.createRetrofit("https://rickandmortyapi.com/")
    }
}
