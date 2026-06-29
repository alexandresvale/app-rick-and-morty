package com.alexandresvale.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alexandresvale.rickandmorty.core.designsystem.ui.theme.RickAndMortyTheme
import com.alexandresvale.rickandmorty.feature.characters.impl.navigation.charactersGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "characters_list") {
                    charactersGraph(
                        onNavigateToDetails = { id ->
                            navController.navigate("character_details/$id")
                        },
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}