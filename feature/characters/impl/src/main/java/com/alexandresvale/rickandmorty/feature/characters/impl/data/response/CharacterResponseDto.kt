package com.alexandresvale.rickandmorty.feature.characters.impl.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterResponseDto(
    @SerialName("info") val info: InfoDto,
    @SerialName("results") val results: List<CharacterDto>
)

@Serializable
internal data class InfoDto(
    @SerialName("next") val next: String?
)

@Serializable
internal data class CharacterDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: String,
    @SerialName("image") val image: String
)
