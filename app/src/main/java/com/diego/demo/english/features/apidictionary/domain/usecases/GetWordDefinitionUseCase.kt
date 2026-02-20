package com.diego.demo.english.features.apidictionary.domain.usecases

import com.diego.demo.english.features.apidictionary.domain.entities.Word
import com.diego.demo.english.features.apidictionary.domain.repositories.DictionaryRepository
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(word: String): Result<List<Word>> {
        return try {
            val result = repository.getWord(word)
            val filtered = result.filter { it.word.isNotBlank() }

            if (filtered.isEmpty()) {
                Result.failure(Exception("No se encontró la palabra"))
            } else {
                Result.success(filtered)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

