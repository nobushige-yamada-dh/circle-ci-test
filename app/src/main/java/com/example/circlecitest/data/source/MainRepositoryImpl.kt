package com.example.circlecitest.data.source

import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.source.local.LocalDataSource
import javax.inject.Inject

/**
 * This is an implementation of business logic.
 * You MUST NOT write code for I/O, database or API.
 * These code MUST write in 'data.source.local' or 'data.source.remote', and inject here.
 * This class (business logic) can test by Unit Test with mock easily, without Android and I/O.
 * You MUST keep it.
 */
class MainRepositoryImpl private constructor(
        private val app: MyApplication
): MainRepository {

    @Inject lateinit var localDataSource: LocalDataSource

    init {
        app.appComponent.inject(this)
    }

    var name = "me"

    companion object {

        @Volatile private var INSTANCE: MainRepositoryImpl? = null

        fun getInstance(app: MyApplication) =
            INSTANCE ?: synchronized(MainRepositoryImpl::class.java) {
                INSTANCE ?:
                    MainRepositoryImpl(app).also {
                        INSTANCE = it
                    }
            }
    }
}
