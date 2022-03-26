package com.sarfa.listdetailsexample.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}