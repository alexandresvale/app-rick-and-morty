package com.alexandresvale.rickandmorty.feature.characters.impl.data

import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterResponse
import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharactersService {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterDataResponse

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse
}
