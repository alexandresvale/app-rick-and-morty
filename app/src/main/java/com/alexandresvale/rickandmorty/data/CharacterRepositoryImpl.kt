package com.alexandresvale.rickandmorty.data

import com.alexandresvale.rickandmorty.domain.CharacterModel
import com.alexandresvale.rickandmorty.domain.CharacterRepository
import kotlinx.coroutines.delay

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun getCharacter(id: Int): Result<CharacterModel> {
        delay(2000)
        return Result.success(
            CharacterModel(
                id = 1,
                name = "Rick",
                species = "Human"
            )
        )
    }
}