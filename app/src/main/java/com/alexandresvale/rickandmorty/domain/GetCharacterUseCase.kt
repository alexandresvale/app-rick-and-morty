package com.alexandresvale.rickandmorty.domain

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<CharacterModel> = repository.getCharacter(id)
}