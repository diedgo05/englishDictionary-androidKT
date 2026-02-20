package com.diego.demo.english

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.diego.demo.english.core.di.AppContainer
import com.diego.demo.english.core.ui.theme.EnglishTheme
import com.diego.demo.english.features.apidictionary.di.WordModule
import com.diego.demo.english.features.apidictionary.presentation.screens.WordDefinitionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(this)

        val wordModule = WordModule(appContainer)
        enableEdgeToEdge()
        setContent{
            EnglishTheme {
                WordDefinitionScreen()
            }
        }
    }
}