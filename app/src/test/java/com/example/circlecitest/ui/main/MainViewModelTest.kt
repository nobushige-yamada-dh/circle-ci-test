package com.example.circlecitest.ui.main

import com.example.circlecitest.data.source.AppRepository
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {

    @Test
    fun testDoSomething() {
        val appRepository = mock(AppRepository::class.java)
        val mainViewModel = MainViewModel(appRepository)
        assertEquals("Did something", mainViewModel.doSomething())
    }
}
