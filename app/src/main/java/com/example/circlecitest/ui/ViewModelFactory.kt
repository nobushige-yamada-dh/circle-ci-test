package com.example.circlecitest.ui

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.source.AppRepository
import com.example.circlecitest.ui.main.MainViewModel
import com.example.circlecitest.ui.targetappsettings.TargetAppSettingsViewModel
import javax.inject.Inject

/**
 *
 */
class ViewModelFactory private constructor(
        private val app: MyApplication
) : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var appRepository: AppRepository

    init {
        app.appComponent.inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(appRepository)
                    isAssignableFrom(TargetAppSettingsViewModel::class.java) ->
                        TargetAppSettingsViewModel(appRepository, app.appExecutors)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(app: MyApplication) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(app).also {
                        INSTANCE = it
                    }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
