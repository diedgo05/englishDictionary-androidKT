package com.diego.demo.english.core.di

import android.content.Context
import com.diego.demo.english.core.network.ApiDictionary
import com.diego.demo.english.features.apidictionary.data.repositories.WordRepositoryImp
import com.diego.demo.english.features.apidictionary.domain.repositories.DictionaryRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.diego.demo.english.BuildConfig


class AppContainer(context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiDictionary: ApiDictionary by lazy {
        retrofit.create(ApiDictionary::class.java)
    }

    val dictionaryRepository: DictionaryRepository by lazy {
        WordRepositoryImp(apiDictionary)
    }
}