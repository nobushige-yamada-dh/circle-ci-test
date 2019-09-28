package com.example.circlecitest.data.source.local

import com.example.circlecitest.data.GameApp

interface LocalDataSource {

    data class AppInfo(val applicationId: String, val name: String)

    fun getAllGameApps(): List<GameApp>
    fun getGameAppsByApplicationId(applicationId: String): List<GameApp>
    fun insertGameAppIfNotExists(gameApp: GameApp): Long
    fun deleteGameApp(gameApp: GameApp): Int
    fun isInstalled(applicationId: String): Boolean
    fun getInstalledApplications(): List<AppInfo>
}
