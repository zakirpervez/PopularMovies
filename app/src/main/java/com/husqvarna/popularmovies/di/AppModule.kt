package com.husqvarna.popularmovies.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Application module for providing the application level injection.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Provide the application context.
     * @param application: [Application].
     * @return [Context]
     */
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext!!
    }
}
