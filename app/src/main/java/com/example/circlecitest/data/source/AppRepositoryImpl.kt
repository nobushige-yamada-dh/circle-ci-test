package com.example.circlecitest.data.source

import androidx.annotation.VisibleForTesting
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.local.LocalDataSource
import java.util.concurrent.Executors

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
        private val localDataSource: LocalDataSource
) : AppRepository {

    private val diskAccessExecutor = Executors.newSingleThreadExecutor()
    private val resultExecutor = Executors.newSingleThreadExecutor()

    override fun getAllGameApps(callback: (List<GameApp>) -> Unit) {
        diskAccessExecutor.execute {
            localDataSource.getAllGameApps().also {
                resultExecutor.execute {
                    callback(it)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AppRepositoryImpl? = null

        fun getInstance(localDataSource: LocalDataSource) =
                INSTANCE ?: synchronized(AppRepositoryImpl::class.java) {
                    INSTANCE ?: AppRepositoryImpl(localDataSource).also {
                        INSTANCE = it
                    }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
