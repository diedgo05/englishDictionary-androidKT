package com.diego.demo.english.features.apidictionary.data.datasources.remote.model


data class WordDto(
    val word: String,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val meanings: List<MeaningDto>
)

data class PhoneticDto(
    val text: String?,
    val audio: String?
)

data class MeaningDto(
    val partOfSpeech: String,
    val definitions: List<DefinitionDto>
)

data class DefinitionDto(
    val definition: String,
    val example: String?
)
