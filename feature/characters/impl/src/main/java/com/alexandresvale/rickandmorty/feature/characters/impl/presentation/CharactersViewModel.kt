package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val charactersPagingFlow = getCharactersUseCase().cachedIn(viewModelScope)

    fun fetchCharacters() = viewModelScope.launch {
        /*_uiState.update { CharactersUiState.Loading }
        getCharactersUseCase()
            .onFailure { throwable ->
                _uiState.update { CharactersUiState.Error(message = throwable.message.orEmpty()) }
            }
            .onSuccess { model ->
                _uiState.update { CharactersUiState.Success(characterModels = model) }
            }*/
    }
}