package com.example.circlecitest.di

import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.source.MainRepository
import com.example.circlecitest.data.source.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 */
@Module
open class AppModule {

    /**
     *
     */
    @Singleton
    @Provides
    open fun provideRepository(app: MyApplication): MainRepository = MainRepositoryImpl()
}
