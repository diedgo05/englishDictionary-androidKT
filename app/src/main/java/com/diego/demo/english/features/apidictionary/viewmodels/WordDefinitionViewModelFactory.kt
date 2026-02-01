package com.diego.demo.english.features.apidictionary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diego.demo.english.features.apidictionary.domain.usecases.GetWordDefinitionUseCase

class WordDefinitionViewModelFactory( private val getWordDefinitionUseCase: GetWordDefinitionUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordDefinitionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordDefinitionViewModel(getWordDefinitionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}