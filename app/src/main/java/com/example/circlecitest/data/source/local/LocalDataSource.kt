package com.example.circlecitest.data.source.local

import com.example.circlecitest.data.GameApp

interface LocalDataSource {

    fun getAllGameApps(): List<GameApp>
    fun getGameAppsByApplicationIdAndClassName(applicationId: String, className: String): GameApp
    fun insertGameAppIfNotExists(gameApp: GameApp): Long
    fun deleteGameApp(gameApp: GameApp): Int
}
