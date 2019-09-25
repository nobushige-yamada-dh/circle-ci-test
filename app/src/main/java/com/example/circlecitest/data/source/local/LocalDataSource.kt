package com.example.circlecitest.data.source.local

import com.example.circlecitest.data.GameApp

interface LocalDataSource {

    fun getAllGameApps(): List<GameApp>
    fun isInstalled(applicationId: String): Boolean
    fun getInstalledApplications(): List<GameApp>
}
