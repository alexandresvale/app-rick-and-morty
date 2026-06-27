package com.alexandresvale.rickandmorty.feature.characters.impl.data

import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharactersService {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponseDto
}