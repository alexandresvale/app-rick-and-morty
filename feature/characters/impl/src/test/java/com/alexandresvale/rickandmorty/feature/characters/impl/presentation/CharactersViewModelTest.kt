package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import androidx.paging.PagingData
import com.alexandresvale.rickandmorty.feature.characters.impl.domain.usecase.GetCharactersUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test


internal class CharactersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetCharactersUseCase = mockk()

    @Test
    fun `should initialize paging flow when created`() {
        // Given
        every { useCase() } returns flowOf(PagingData.empty())

        // Given
        val viewModel = CharactersViewModel(getCharactersUseCase = useCase)

        // Then
        verify(exactly = 1) { useCase() }
        assertNotNull(viewModel.charactersPagingFlow)
    }
}