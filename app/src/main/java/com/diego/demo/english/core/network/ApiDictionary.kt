package com.diego.demo.english.core.network

import com.diego.demo.english.features.apidictionary.data.datasources.remote.model.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDictionary {

    @GET("entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordDto>
}
