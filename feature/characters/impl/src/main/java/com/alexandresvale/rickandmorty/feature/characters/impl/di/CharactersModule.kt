package com.alexandresvale.rickandmorty.feature.characters.impl.di

import com.alexandresvale.rickandmorty.feature.characters.impl.data.CharactersService
import com.alexandresvale.rickandmorty.feature.characters.impl.data.repository.CharactersRepositoryImpl
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase.GetCharacterDetailsUseCase
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase.GetCharactersUseCase
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharacterDetailsViewModel
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val charactersModule = module {
    single {
        get<Retrofit>().create(CharactersService::class.java)
    }
    factory<CharactersRepository> {
        CharactersRepositoryImpl(apiService = get())
    }
    factory {
        GetCharactersUseCase(repository = get())
    }
    factory {
        GetCharacterDetailsUseCase(repository = get())
    }
    viewModel {
        CharactersViewModel(getCharactersUseCase = get())
    }
    viewModel {
        CharacterDetailsViewModel(getCharacterDetailsUseCase = get(), savedStateHandle = get())
    }
}