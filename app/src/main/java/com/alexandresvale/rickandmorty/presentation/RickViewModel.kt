package com.alexandresvale.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandresvale.rickandmorty.domain.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RickViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RickUiState())
    val uiState: StateFlow<RickUiState> = _uiState.asStateFlow()

    fun changeDimension() {
        _uiState.update { it.copy(dimension = "Dimensão Cronenberg") }
    }

    fun loadRick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getCharacterUseCase(id = 1)
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message
                        )
                    }
                }
                .onSuccess { model ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            dimension = model.name
                        )
                    }
                }
        }
    }
}

data class RickUiState(
    val dimension: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
