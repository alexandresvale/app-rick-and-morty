package com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository

import androidx.paging.PagingData
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

internal interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacter(id: Int): CharacterModel
}
