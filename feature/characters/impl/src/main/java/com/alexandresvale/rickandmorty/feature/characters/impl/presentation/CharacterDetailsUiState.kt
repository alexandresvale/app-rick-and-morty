package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel

internal sealed interface CharacterDetailsUiState {
    data object Loading : CharacterDetailsUiState
    data class Success(val character: CharacterModel) : CharacterDetailsUiState
    data class Error(val message: String) : CharacterDetailsUiState
}
