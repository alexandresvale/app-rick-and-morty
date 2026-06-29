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
                pageSize = 20, // O número de itens que a API retorna por vez
                prefetchDistance = 2 // Quantos itens antes de acabar a tela o Paging deve pedir a próxima página (para esconder o carregamento do usuário)
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
