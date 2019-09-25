package com.example.circlecitest.data.source

import com.example.circlecitest.data.GameApp

/**
 * This is an interface of main repository.
 * When you run unit tests, you can implement mock using this interface.
 */
interface AppRepository {

    fun getAllGameApps(callback: (List<GameApp>) -> Unit)
    fun getInstalledApplications(): List<GameApp>
}
