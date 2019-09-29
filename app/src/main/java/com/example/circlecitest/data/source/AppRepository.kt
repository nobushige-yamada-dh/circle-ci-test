package com.example.circlecitest.data.source

import com.example.circlecitest.data.GameApp

/**
 * This is an interface of main repository.
 * When you run unit tests, you can implement mock using this interface.
 */
interface AppRepository {

    suspend fun getAllGameApps(): List<GameApp>
    suspend fun getGameAppsByApplicationIdAndClassName(
            applicationId: String,
            className: String
    ): GameApp

    suspend fun insertGameAppIfNotExists(gameApp: GameApp): Long
    suspend fun deleteGameApp(gameApp: GameApp): Int
    suspend fun getInstalledApplications(): List<GameApp>
}
