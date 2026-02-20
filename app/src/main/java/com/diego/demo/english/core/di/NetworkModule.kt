package com.diego.demo.english.core.di

import com.diego.demo.english.BuildConfig
import com.diego.demo.english.core.network.ApiDictionary
import com.diego.demo.english.features.apidictionary.data.repositories.WordRepositoryImp
import com.diego.demo.english.features.apidictionary.domain.repositories.DictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    @Singleton
    fun provideApiDictionary(retrofit: Retrofit): ApiDictionary =
        retrofit.create(ApiDictionary::class.java)

    @Provides
    @Singleton
    fun provideDictionaryRepository(apiDictionary: ApiDictionary): DictionaryRepository =
        WordRepositoryImp(apiDictionary)

}