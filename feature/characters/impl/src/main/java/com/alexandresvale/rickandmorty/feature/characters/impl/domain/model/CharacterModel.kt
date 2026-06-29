package com.alexandresvale.rickandmorty.feature.characters.impl.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String // Alive, Dead, Unknown
)
