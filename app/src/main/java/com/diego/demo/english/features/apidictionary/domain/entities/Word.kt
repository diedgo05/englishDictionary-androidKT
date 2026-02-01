package com.diego.demo.english.features.apidictionary.domain.entities

data class Word (
    val word: String,
    val phonetic: String,
    val audioUrl: String?,
    val partOfSpeech: String,
    val definition: String,
    val example: String?
)