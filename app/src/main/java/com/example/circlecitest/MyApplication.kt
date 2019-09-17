package com.example.circlecitest

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.circlecitest.di.AppComponent
import com.example.circlecitest.di.DaggerAppComponent

/**
 *
 */
class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        @VisibleForTesting set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this)
            .build()
    }

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
        /*
        if (a == 15) {
            return 15
        }
        if (a == 16) {
            return 16
        }
        if (a == 17) {
            return 17
        }
        */
        return 0
    }
}
