package com.example.circlecitest

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.circlecitest.data.source.AppExecutors
import com.example.circlecitest.di.AppComponent
import com.example.circlecitest.di.DaggerAppComponent
import com.example.circlecitest.ui.MainThreadExecutor

/**
 *
 */
class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        @VisibleForTesting set

    lateinit var appExecutors: AppExecutors
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this)
                .build()
        appExecutors = AppExecutors(MainThreadExecutor())
    }

    /**
     *
     */
    fun forCyclomaticComplexity(a: Int): Int {
        if (a == 1) {
            return 1
        }
        if (a == 2) {
            return 2
        }
        if (a == 3) {
            return 3
        }
        if (a == 4) {
            return 4
        }
        if (a == 5) {
            return 5
        }
        if (a == 6) {
            return 6
        }
        if (a == 7) {
            return 7
        }
        if (a == 8) {
            return 8
        }
        if (a == 9) {
            return 9
        }
        if (a == 10) {
            return 10
        }
        if (a == 11) {
            return 11
        }
        if (a == 12) {
            return 12
        }
        if (a == 13) {
            return 13
        }
        if (a == 14) {
            return 14
        }
        if (a == 15) {
            return 15
        }
        return 0
    }
}
