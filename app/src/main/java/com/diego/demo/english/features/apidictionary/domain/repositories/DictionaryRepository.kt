package com.diego.demo.english.features.apidictionary.domain.repositories

import com.diego.demo.english.features.apidictionary.domain.entities.Word

interface DictionaryRepository {
    suspend fun getWord(word: String): List<Word>
}