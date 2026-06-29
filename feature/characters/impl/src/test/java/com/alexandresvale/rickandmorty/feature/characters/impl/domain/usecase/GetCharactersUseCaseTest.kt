package com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase

import androidx.paging.PagingData
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.repository.CharactersRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

internal class GetCharactersUseCaseTest {

    private val repository: CharactersRepository = mockk()
    private val useCase = GetCharactersUseCase(repository)

    @Test
    fun `getCharactersUseCase should call repository and return paging flow when invoked`() {
        // Given
        every { repository.getCharacters() } returns flowOf(PagingData.empty())

        // When
        useCase()

        // Then
        verify(exactly = 1) {
            repository.getCharacters()
        }
    }
}
