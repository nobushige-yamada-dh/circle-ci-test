package com.example.circlecitest.data.source.local

import android.content.pm.PackageManager
import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.GameApp

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is an implementation of database access.
 * This class MUST be kept simple, it SHOULD NOT include logic.
 * The logic SHOULD be written in AppRepositoryImpl.
 */
class LocalDataSourceImpl private constructor(
        private val app: MyApplication
) : LocalDataSource {

    private val appDb = AppDatabase.getInstance(app)

    override fun getAllGameApps() = appDb.gameAppsDao().getAll()

    override fun getGameAppsByApplicationId(applicationId: String) =
            appDb.gameAppsDao().getByApplicationId(applicationId)

    override fun insertGameAppIfNotExists(gameApp: GameApp) =
            appDb.gameAppsDao().insertIfNotExists(gameApp)

    override fun deleteGameApp(gameApp: GameApp) = appDb.gameAppsDao().delete(gameApp)

    override fun isInstalled(applicationId: String): Boolean {
        try {
            app.packageManager.getApplicationInfo(applicationId, 0)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    override fun getInstalledApplications(): List<LocalDataSource.AppInfo> {
        val pm = app.packageManager
        return pm.getInstalledApplications(0)
                .map { LocalDataSource.AppInfo(it.packageName, it.loadLabel(pm).toString()) }
                .toList()
    }

    companion object {

        @Volatile
        private var INSTANCE: LocalDataSourceImpl? = null

        fun getInstance(app: MyApplication) =
                INSTANCE ?: synchronized(LocalDataSourceImpl::class.java) {
                    INSTANCE ?: LocalDataSourceImpl(app).also {
                        INSTANCE = it
                    }
                }
    }
}
