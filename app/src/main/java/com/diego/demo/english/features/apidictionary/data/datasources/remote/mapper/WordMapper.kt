package com.diego.demo.english.features.apidictionary.data.datasources.remote.mapper

import com.diego.demo.english.features.apidictionary.domain.entities.Word
import com.diego.demo.english.features.apidictionary.data.datasources.remote.model.WordDto

fun WordDto.toDomain(): Word {
    val firstMeaning = meanings.firstOrNull()
    val firstDefinition = firstMeaning?.definitions?.firstOrNull()

    val audio = phonetics.firstOrNull { !it.audio.isNullOrBlank() }?.audio

    return Word(
        word = word,
        phonetic = phonetic ?: phonetics.firstOrNull()?.text.orEmpty(),
        audioUrl = audio,
        partOfSpeech = firstMeaning?.partOfSpeech.orEmpty(),
        definition = firstDefinition?.definition.orEmpty(),
        example = firstDefinition?.example
    )
}
