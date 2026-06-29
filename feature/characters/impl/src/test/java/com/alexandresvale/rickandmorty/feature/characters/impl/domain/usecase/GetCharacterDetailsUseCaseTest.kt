package com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase

import com.alexandresvale.rickandmorty.feature.characters.impl.domain.model.CharacterModel
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCharacterDetailsUseCaseTest {

    private val repository = mockk<CharactersRepository>()
    private val useCase = GetCharacterDetailsUseCase(repository)

    @Test
    fun `when repository returns character, use case should return success`() = runTest {
        // Arrange
        val expectedCharacter = CharacterModel(
            id = 1,
            name = "Rick",
            status = "Alive",
            imageUrl = "url"
        )
        coEvery { repository.getCharacter(1) } returns expectedCharacter

        // Act
        val result = useCase(1)

        // Assert
        assert(result.isSuccess)
        assertEquals(expectedCharacter, result.getOrNull())
    }

    @Test
    fun `when repository throws exception, use case should return failure`() = runTest {
        // Arrange
        val exception = RuntimeException("API Error")
        coEvery { repository.getCharacter(1) } throws exception

        // Act
        val result = useCase(1)

        // Assert
        assert(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
