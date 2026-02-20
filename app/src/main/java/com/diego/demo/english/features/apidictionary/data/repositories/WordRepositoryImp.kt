package com.diego.demo.english.features.apidictionary.data.repositories

import com.diego.demo.english.core.network.ApiDictionary
import com.diego.demo.english.features.apidictionary.domain.entities.Word
import com.diego.demo.english.features.apidictionary.domain.repositories.DictionaryRepository
import com.diego.demo.english.features.apidictionary.data.datasources.remote.mapper.toDomain
import javax.inject.Inject

class WordRepositoryImp @Inject constructor(private val api: ApiDictionary): DictionaryRepository{

    override suspend fun getWord(word: String): List<Word> {
        val response = api.getWord(word)
        return response.map { it.toDomain() }
    }
}