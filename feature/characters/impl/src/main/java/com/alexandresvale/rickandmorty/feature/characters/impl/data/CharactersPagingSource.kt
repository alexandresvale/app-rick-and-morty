package com.alexandresvale.rickandmorty.feature.characters.impl.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alexandresvale.rickandmorty.feature.characters.impl.data.mapper.toDomain
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel

internal class CharactersPagingSource(
    private val api: CharactersService
) : PagingSource<Int, CharacterModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            // Se o params.key for null, significa que é o primeiro carregamento (página 1)
            val currentPage = params.key ?: 1
            val response = api.getCharacters(page = currentPage)
            val characters = response.results.map { it.toDomain() }
            
            // Dizemos ao Paging3 qual é a página atual e qual será a próxima!
            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.info.next == null) null else currentPage + 1
            )
        } catch (e: Exception) {
            // Em caso de falta de internet ou erro no servidor
            LoadResult.Error(e)
        }
    }

    // Função obrigatória do Paging3 para saber recarregar os dados caso a lista seja invalidada
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
