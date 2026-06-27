package com.alexandresvale.rickandmorty.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RickScreen(viewModel: RickViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    RickCard(
        currentDimension = uiState.dimension,
        onDimensionChangeClick = { viewModel.changeDimension() }
    )
}

@Composable
fun RickCard(
    currentDimension: String,
    onDimensionChangeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentDimension,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onDimensionChangeClick
        ) {
            Text(text = "Mudar dimensão")
        }
    }

}

@Preview
@Composable
fun RickCardPreview() {
    RickCard(
        currentDimension = "C-137",
        onDimensionChangeClick = {}
    )
}