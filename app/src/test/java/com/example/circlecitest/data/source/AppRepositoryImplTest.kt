package com.example.circlecitest.data.source

import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.local.LocalDataSource
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class AppRepositoryImplTest {

    @After
    fun tearDown() {
        AppRepositoryImpl.destroyInstance()
    }

    @Test
    fun testGetAllGameApps() {
        val lock = ReentrantLock()
        val condition = lock.newCondition()
        val gameApps = listOf(GameApp(1, "app1", "app1"))
        val appRepositoryImpl =
                AppRepositoryImpl.getInstance(object : LocalDataSource by mock<LocalDataSource>() {
                    override fun getAllGameApps() = gameApps
                    override fun isInstalled(applicationId: String) = true
                })
        lock.withLock {
            var result: List<GameApp>? = null
            appRepositoryImpl.getAllGameApps {
                result = it
                lock.withLock {
                    condition.signalAll()
                }
            }
            assertTrue(condition.await(1, TimeUnit.SECONDS))
            assertEquals(1, result?.size)
            assertEquals("app1", result?.get(0)?.name)
        }
    }
}
