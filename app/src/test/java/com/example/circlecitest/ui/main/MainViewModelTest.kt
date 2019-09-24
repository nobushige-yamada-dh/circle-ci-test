package com.example.circlecitest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.circlecitest.data.GameApp
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
    fun testOnClickGameApp() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        val observer = mock<Observer<GameApp>>()
        val gameApp = GameApp(1, "game name")
        mainViewModel.launchGameApp.observeForever(observer)
        assertEquals(null, mainViewModel.launchGameApp.value)
        clearInvocations(observer)

        mainViewModel.onClickGameApp(gameApp)

        verify(observer).onChanged(check {
            assertEquals(gameApp.id, it.id)
            assertEquals(gameApp.name, it.name)
        })
    }

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

    @Test
    fun testOnClickFab() {
        val appRepository = mock<AppRepository>()
        val mainViewModel = MainViewModel(appRepository)
        val observer = mock<Observer<MainViewModel.Message>>()
        mainViewModel.snackBarMessage.observeForever(observer)
        assertEquals(null, mainViewModel.snackBarMessage.value)
        clearInvocations(observer)

        mainViewModel.onClickFab()

        verify(observer).onChanged(MainViewModel.Message.FAB_PUSHED)
    }
}
