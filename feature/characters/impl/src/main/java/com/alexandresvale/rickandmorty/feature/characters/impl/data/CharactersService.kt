package com.alexandresvale.rickandmorty.feature.characters.impl.data

import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterDto
import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharactersService {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponseDto

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto
}
