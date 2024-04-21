package com.git.gitsearch.network

import com.git.gitsearch.utility.Routes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Routes.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}