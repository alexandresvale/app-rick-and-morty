package com.alexandresvale.rickandmorty.feature.characters.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharacterDetailsScreen
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharacterDetailsViewModel
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharactersScreen
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharactersViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.charactersGraph(
    onNavigateToDetails: (Int) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable("characters_list") {
        val viewModel: CharactersViewModel = koinViewModel()
        CharactersScreen(
            viewModel = viewModel,
            onCharacterClick = onNavigateToDetails
        )
    }

    composable(
        route = "character_details/{characterId}",
        arguments = listOf(navArgument("characterId") { type = NavType.StringType })
    ) {
        val viewModel: CharacterDetailsViewModel = koinViewModel()
        CharacterDetailsScreen(
            viewModel = viewModel,
            onBackClick = onNavigateBack
        )
    }
}
