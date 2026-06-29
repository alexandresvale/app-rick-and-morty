package com.alexandresvale.rickandmorty

import android.app.Application
import com.alexandresvale.core.network.di.networkModule
import com.alexandresvale.rickandmorty.feature.characters.impl.di.charactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApplication)
            modules(
                networkModule,
                charactersModule
            )
        }
    }
}