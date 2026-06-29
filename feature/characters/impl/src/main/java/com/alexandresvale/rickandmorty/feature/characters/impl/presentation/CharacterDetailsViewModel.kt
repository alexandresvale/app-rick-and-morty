package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CharacterDetailsViewModel(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: String = checkNotNull(savedStateHandle["characterId"])

    private val _uiState = MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Loading)
    val uiState: StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        viewModelScope.launch {
            _uiState.value = CharacterDetailsUiState.Loading
            getCharacterDetailsUseCase(characterId.toInt())
                .onSuccess { character ->
                    _uiState.value = CharacterDetailsUiState.Success(character)
                }
                .onFailure { error ->
                    _uiState.value = CharacterDetailsUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
}
