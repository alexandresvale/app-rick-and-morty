package com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase

import androidx.paging.PagingData
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

internal class GetCharactersUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> = repository.getCharacters()
}
