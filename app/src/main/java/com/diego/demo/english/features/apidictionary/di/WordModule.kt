package com.diego.demo.english.features.apidictionary.di

import com.diego.demo.english.core.di.AppContainer
import com.diego.demo.english.features.apidictionary.domain.usecases.GetWordDefinitionUseCase
import com.diego.demo.english.features.apidictionary.viewmodels.WordDefinitionViewModelFactory

class WordModule( private val appContainer: AppContainer) {
    private fun provideGetWordDefinitionUseCase(): GetWordDefinitionUseCase {
        return GetWordDefinitionUseCase(appContainer.dictionaryRepository)
    }

    fun provideWordDefinitionViewModelFactory(): WordDefinitionViewModelFactory {
        return WordDefinitionViewModelFactory( getWordDefinitionUseCase = provideGetWordDefinitionUseCase())
    }
}