package com.alexandresvale.rickandmorty.feature.characters.impl.data.mapper

import com.alexandresvale.rickandmorty.feature.characters.api.CharacterModel
import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterDto

internal fun CharacterDto.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        imageUrl = image
    )
}