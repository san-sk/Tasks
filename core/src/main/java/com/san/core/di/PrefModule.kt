package com.san.core.di

import android.content.Context
import android.content.SharedPreferences
import com.san.core.config.Config
import com.san.core.preferences.AppPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Config.APP_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSessionManager(sharedPreferences: SharedPreferences) =
        AppPrefManager(sharedPreferences)

}