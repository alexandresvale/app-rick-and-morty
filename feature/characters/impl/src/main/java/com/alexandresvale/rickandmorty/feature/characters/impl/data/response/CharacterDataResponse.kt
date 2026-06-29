package com.alexandresvale.rickandmorty.feature.characters.impl.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterDataResponse(
    @SerialName("info") val info: InfoResponse,
    @SerialName("results") val results: List<CharacterResponse>
)

@Serializable
internal data class InfoResponse(
    @SerialName("next") val next: String?
)

@Serializable
internal data class CharacterResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: String,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: String,
    @SerialName("origin") val origin: CharacterLocationResponse,
    @SerialName("location") val location: CharacterLocationResponse,
    @SerialName("image") val image: String,
    @SerialName("episode") val episode: List<String>,
    @SerialName("url") val url: String,
    @SerialName("created") val created: String
)

@Serializable
internal data class CharacterLocationResponse(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)
