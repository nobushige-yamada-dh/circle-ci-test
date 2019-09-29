package com.example.circlecitest.data.source

import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.local.LocalDataSource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Test

class AppRepositoryImplTest {

    @After
    fun tearDown() {
        AppRepositoryImpl.destroyInstance()
    }

    @Test
    fun testGetAllGameApps() = runBlocking {
        val gameApps = listOf(GameApp(1, "app1", "app1"))
        val appRepositoryImpl =
                AppRepositoryImpl.getInstance(object : LocalDataSource by mock<LocalDataSource>() {
                    override fun getAllGameApps() = gameApps
                    override fun isInstalled(applicationId: String) = true
                })
        val result = appRepositoryImpl.getAllGameApps()
        assertEquals(1, result.size)
        assertEquals("app1", result[0].className)
    }
}
