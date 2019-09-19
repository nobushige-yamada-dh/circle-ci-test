package com.example.circlecitest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.circlecitest.data.source.AppRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun testDoSomething() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        assertEquals("Did something", mainViewModel.doSomething())
    }

    @Test
    fun testOnClickFab() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        val observer = mock<Observer<MainViewModel.Message>>()
        mainViewModel.snackBarMessage.observeForever(observer)
        assertEquals(MainViewModel.Message.NO_MESSAGE, mainViewModel.snackBarMessage)
        mainViewModel.onClickFab()
        verify(observer).onChanged(MainViewModel.Message.FAB_PUSHED)
    }
}
