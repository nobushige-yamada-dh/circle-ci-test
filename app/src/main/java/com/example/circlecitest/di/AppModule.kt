package com.example.circlecitest.di

import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.source.MainRepository
import com.example.circlecitest.data.source.MainRepositoryImpl
import com.example.circlecitest.data.source.local.LocalDataSource
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
    open fun provideRepository(app: MyApplication): MainRepository = MainRepositoryImpl.getInstance(app)

    @Singleton
    @Provides
    open fun provideLocalDataSource(app: MyApplication): LocalDataSource = LocalDataSourceImpl.getInstance(app)
}
