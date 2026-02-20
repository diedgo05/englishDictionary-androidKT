package com.diego.demo.english.features.apidictionary.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.demo.english.features.apidictionary.presentation.components.DefinitionCard
import com.diego.demo.english.features.apidictionary.presentation.components.WordCard
import com.diego.demo.english.features.apidictionary.viewmodels.WordDefinitionViewModel
import com.diego.demo.english.features.apidictionary.viewmodels.WordDefinitionViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDefinitionScreen(
    viewModel: WordDefinitionViewModel = hiltViewModel()  // Hilt lo inyecta solo
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "English Dictionary",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {

            // BUSCADOR
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search word") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (searchText.isNotBlank()) {
                        viewModel.loadWordDefinition(searchText.trim())
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RESULTADOS
            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                uiState.error != null -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                uiState.definitions.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(uiState.definitions) { word ->
                            WordCard(
                                word = word.word,
                                phonetic = word.phonetic,
                                audioUrl = word.audioUrl
                            )
                            DefinitionCard(
                                partOfSpeech = word.partOfSpeech,
                                definition = word.definition,
                                example = word.example,
                            )
                        }
                    }
                }
            }
        }
    }
}
