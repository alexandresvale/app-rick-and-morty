package com.alexandresvale.rickandmorty.feature.characters.impl.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alexandresvale.rickandmorty.feature.characters.impl.data.CharactersPagingSource
import com.alexandresvale.rickandmorty.feature.characters.impl.data.CharactersService
import com.alexandresvale.rickandmorty.feature.characters.impl.data.mapper.toDomain
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

internal class CharactersRepositoryImpl(
    private val apiService: CharactersService
) : CharactersRepository {
    override fun getCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                // O número de itens que a API retorna por vez
                pageSize = 20,
                // Quantos itens antes de acabar a tela o Paging deve pedir a próxima página
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                CharactersPagingSource(api = apiService)
            }
        ).flow
    }

    override suspend fun getCharacter(id: Int): CharacterModel {
        return apiService.getCharacter(id).toDomain()
    }
}
