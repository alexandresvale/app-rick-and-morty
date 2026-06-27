package com.alexandresvale.rickandmorty.domain

interface CharacterRepository {
    suspend fun getCharacter(id: Int): Result<CharacterModel>
}