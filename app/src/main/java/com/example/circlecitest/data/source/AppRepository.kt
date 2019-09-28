package com.example.circlecitest.data.source

import com.example.circlecitest.data.GameApp

/**
 * This is an interface of main repository.
 * When you run unit tests, you can implement mock using this interface.
 */
interface AppRepository {

    suspend fun getAllGameApps(): List<GameApp>
    suspend fun getGameAppsByApplicationId(applicationId: String): List<GameApp>
    suspend fun insertGameAppIfNotExists(gameApp: GameApp): Long
    suspend fun deleteGameApp(gameApp: GameApp): Int
    fun getInstalledApplications(): List<GameApp>
}
