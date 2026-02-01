package com.diego.demo.english.features.apidictionary.presentation.screens

import com.diego.demo.english.features.apidictionary.domain.entities.Word

data class WordDefinitionUiState (
    val isLoading: Boolean = false,
    val definitions: List<Word> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false
)