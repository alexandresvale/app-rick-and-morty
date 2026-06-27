package com.alexandresvale.rickandmorty.feature.characters.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharactersScreen
import com.alexandresvale.rickandmorty.feature.characters.impl.presentation.CharactersViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.charactersScreen() {
    composable("characters_list") {
        val viewModel: CharactersViewModel = koinViewModel()
        CharactersScreen(viewModel = viewModel)
    }
}