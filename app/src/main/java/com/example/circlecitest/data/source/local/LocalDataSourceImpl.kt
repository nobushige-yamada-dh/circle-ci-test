package com.example.circlecitest.data.source.local

import com.example.circlecitest.MyApplication

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
