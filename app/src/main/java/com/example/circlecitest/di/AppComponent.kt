package com.example.circlecitest.di

import com.example.circlecitest.MyApplication
import com.example.circlecitest.ui.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    /**
     *
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MyApplication): Builder

        fun build(): AppComponent

        fun appModule(appModule: AppModule): Builder
    }

    fun inject(viewModelFactory: ViewModelFactory)
}
