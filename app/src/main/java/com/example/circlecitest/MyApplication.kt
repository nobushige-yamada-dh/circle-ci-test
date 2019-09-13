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
}
