package com.diego.demo.english.features.apidictionary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.demo.english.features.apidictionary.domain.usecases.GetWordDefinitionUseCase
import com.diego.demo.english.features.apidictionary.presentation.screens.WordDefinitionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDefinitionViewModel @Inject constructor(
    private val getWordDefinitionUseCase: GetWordDefinitionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WordDefinitionUiState())
    val uiState = _uiState.asStateFlow()

    fun loadWordDefinition(word: String) {
        _uiState.update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {
            val result = getWordDefinitionUseCase(word)

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        currentState.copy(
                            isLoading = false,
                            definitions = list
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                )
            }
        }
    }
}
