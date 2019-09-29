package com.example.circlecitest.data.source

import androidx.annotation.VisibleForTesting
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.local.LocalDataSource
import com.example.circlecitest.data.source.os.OsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is an implementation of business logic.
 * You MUST NOT write code for I/O, database or API.
 * These code MUST write in 'data.source.local' or 'data.source.remote', and inject here.
 * This class You MUST NOT depends on Android.
 * This class (business logic) can test by Unit Test with mock easily, without Android and I/O.
 * You MUST keep it.
 */
class AppRepositoryImpl private constructor(
        private val localDataSource: LocalDataSource,
        private val osDataSource: OsDataSource
) : AppRepository {

    override suspend fun getAllGameApps(): List<GameApp> {
        return withContext(Dispatchers.IO) {
            localDataSource.getAllGameApps()
                    .filter { osDataSource.isAvailable(it.applicationId, it.className) }
                    .toList()
        }
    }

    override suspend fun getGameAppsByApplicationIdAndClassName(
            applicationId: String,
            className: String
    ): GameApp? {
        return withContext(Dispatchers.IO) {
            localDataSource.getGameAppsByApplicationIdAndClassName(applicationId, className)
        }
    }

    override suspend fun insertGameAppIfNotExists(gameApp: GameApp): Long {
        return withContext(Dispatchers.IO) {
            localDataSource.insertGameAppIfNotExists(gameApp)
        }
    }

    override suspend fun deleteGameApp(gameApp: GameApp): Int {
        return withContext(Dispatchers.IO) {
            localDataSource.deleteGameApp(gameApp)
        }
    }

    override suspend fun getInstalledApplications(): List<GameApp> {
        return withContext(Dispatchers.IO) {
            osDataSource.getInstalledApplications()
                    .map {
                        localDataSource.getGameAppsByApplicationIdAndClassName(
                                it.applicationId,
                                it.className
                        )?.also {
                            return@map it
                        }
                        GameApp(0, it.applicationId, it.className)
                    }
                    .toList()
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AppRepositoryImpl? = null

        fun getInstance(localDataSource: LocalDataSource, osDataSource: OsDataSource) =
                INSTANCE ?: synchronized(AppRepositoryImpl::class.java) {
                    INSTANCE ?: AppRepositoryImpl(localDataSource, osDataSource).also {
                        INSTANCE = it
                    }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
