package com.example.circlecitest.data.source.local

import com.example.circlecitest.data.GameApp
import com.example.circlecitest.util.AppExecutors

class LocalDataSourceImpl private constructor(
        private val appExecutors: AppExecutors,
        private val mainDb: MainDatabase
): LocalDataSource {

    override fun getAllGameApps(callback: (List<GameApp>) -> Unit) {
        appExecutors.diskIO.execute {
            mainDb.gameAppsDao().getAll().also {
                appExecutors.mainThread.execute {
                    callback(it)
                }
            }
        }
    }

    companion object {

        @Volatile private var INSTANCE: LocalDataSourceImpl? = null

        fun getInstance(appExecutors: AppExecutors, db: MainDatabase) =
            INSTANCE ?: synchronized(LocalDataSourceImpl::class.java) {
                INSTANCE ?:
                    LocalDataSourceImpl(appExecutors, db).also {
                        INSTANCE = it
                    }
            }
    }
}
