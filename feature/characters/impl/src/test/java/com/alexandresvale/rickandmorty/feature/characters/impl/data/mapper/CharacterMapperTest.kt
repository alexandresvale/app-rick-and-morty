package com.alexandresvale.rickandmorty.feature.characters.impl.data.mapper

import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterLocationResponse
import com.alexandresvale.rickandmorty.feature.characters.impl.data.response.CharacterResponse
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CharacterMapperTest {
    @Test
    fun `should map CharacterDto to CharacterModel correctly`() {
        // 1. Given / Arrange (Preparação)
        val fakeDto = CharacterResponse(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = CharacterLocationResponse("Earth", ""),
            location = CharacterLocationResponse("Earth", ""),
            image = "https://url.da.imagem.com",
            episode = emptyList(),
            url = "",
            created = ""
        )

        // 2. When / Act (Ação)
        val resultModel = fakeDto.toDomain()

        // 3. Then / Assert (Verificação)
        assertEquals(1, resultModel.id)
        assertEquals("Rick Sanchez", resultModel.name)
        assertEquals("Alive", resultModel.status)
        assertEquals("https://url.da.imagem.com", resultModel.imageUrl)
    }
}
