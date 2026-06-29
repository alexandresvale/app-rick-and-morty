package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel

sealed class CharactersUiState {
    object Loading : CharactersUiState()
    data class Success(val characterModels: List<CharacterModel>) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}