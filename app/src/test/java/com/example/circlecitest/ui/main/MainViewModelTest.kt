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
    fun testOnClickAdd() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        val observer = mock<Observer<MainViewModel.LaunchScreen>>()
        mainViewModel.launchScreen.observeForever(observer)
        assertEquals(null, mainViewModel.launchScreen.value)
        clearInvocations(observer)

        mainViewModel.onClickAdd()

        verify(observer).onChanged(MainViewModel.LaunchScreen.REGISTER)
    }

    @Test
    fun testOnClickHelp() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        val observer = mock<Observer<MainViewModel.LaunchScreen>>()
        mainViewModel.launchScreen.observeForever(observer)
        assertEquals(null, mainViewModel.launchScreen.value)
        clearInvocations(observer)

        mainViewModel.onClickHelp()

        verify(observer).onChanged(MainViewModel.LaunchScreen.HELP)
    }
}
