package com.alexandresvale.rickandmorty.feature.characters.impl.data.mapper

import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterResponse
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel

internal fun CharacterResponse.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        imageUrl = image
    )
}
