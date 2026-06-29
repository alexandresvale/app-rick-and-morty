package com.alexandresvale.rickandmorty.feature.characters.impl.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    // Roda milissegundos antes do @Test começar
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher) // Substitui a Main Thread
    }

    // Roda milissegundos depois do @Test acabar
    override fun finished(description: Description) {
        Dispatchers.resetMain() // Limpa a sujeira
    }
}
