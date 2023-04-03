package com.san.news.di

import android.content.Context
import com.san.news.data.repo.NewsRepoImplementation
import com.san.news.domain.repo.NewsRepository
import com.san.news.domain.usecase.NewsUseCase
import com.san.news.utils.NewsUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDI {

    @Singleton
    @Provides

    fun provideBaseApi(@Named("NewsApi") retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Named("NewsApi")
    fun provideNewsRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NewsUtils.BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsRepo(@ApplicationContext context: Context, api: NewsApi): NewsRepository {
        return NewsRepoImplementation(context, api)
    }

    @Provides
    @Singleton
    fun providesNewsUseCases(repository: NewsRepository): NewsUseCase {
        return NewsUseCase(repository)
    }
}



