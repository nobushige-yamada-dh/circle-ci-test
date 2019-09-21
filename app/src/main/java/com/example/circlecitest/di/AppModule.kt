package com.example.circlecitest.di

import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.source.AppRepository
import com.example.circlecitest.data.source.AppRepositoryImpl
import com.example.circlecitest.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 */
@Module
open class AppModule {

    @Singleton
    @Provides
    open fun provideRepository(app: MyApplication): AppRepository =
            AppRepositoryImpl.getInstance(LocalDataSourceImpl.getInstance(app))
}
