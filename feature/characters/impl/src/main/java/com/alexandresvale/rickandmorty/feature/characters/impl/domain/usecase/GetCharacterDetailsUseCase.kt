@file:Suppress("TooGenericExceptionCaught")

package com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase

import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository

internal class GetCharacterDetailsUseCase(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(id: Int): Result<CharacterModel> {
        return try {
            val result = repository.getCharacter(id)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
