package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.alexandresvale.rickandmorty.feature.characters.api.CharacterModel

@Composable
internal fun CharactersScreen(
    viewModel: CharactersViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val charactersPagingItems = viewModel.charactersPagingFlow.collectAsLazyPagingItems()
    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }
    when (val state = charactersPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            // Tela cheia de carregamento no primeiro acesso
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            // Erro inicial (ex: sem internet)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Erro: ${state.error.message}")
            }
            Column {
                Text("Sem internet: ${state.error.message}")
                Button(onClick = { charactersPagingItems.retry() }) { Text("Tentar Novamente") }
            }
        }

        is LoadState.NotLoading -> {
            // Os dados chegaram!
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // O .itemCount vem do Paging3
                items(count = charactersPagingItems.itemCount) { index ->
                    val character = charactersPagingItems[index]
                    if (character != null) {
                        // Use o mesmo Card bonitão com Coil que você fez antes aqui!
                        // Exemplo: CharacterCard(character)
                        RickCardItem(character = character)
                    }
                }
                // 3. O Carregamento do Rodapé (Infinite Scroll)
                if (charactersPagingItems.loadState.append is LoadState.Loading) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersContent(uiState: CharactersUiState) {
    when (uiState) {
        is CharactersUiState.Loading -> CircularProgressIndicator()
        is CharactersUiState.Error -> Text("Erro ao carregar!")
        is CharactersUiState.Success -> {
            LazyColumn {
                items(uiState.characterModels) { character ->
                    RickCardItem(character)
                }
            }
        }
    }
}

@Composable
private fun RickCardItem(character: CharacterModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // A Mágica Acontece Aqui:
            AsyncImage(
                model = character.imageUrl,
                contentDescription = "Foto de ${character.name}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape) // Corta a imagem em formato de círculo!
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}