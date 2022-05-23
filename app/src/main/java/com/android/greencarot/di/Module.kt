package com.android.greencarot.di

import android.content.Context
import com.android.greencarot.remote.NetworkServiceFactory
import com.android.greencarot.repository.Repository
import com.android.greencarot.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Provides
    fun provideRepository(
        networkServiceFactory: NetworkServiceFactory,
    ): Repository = RepositoryImpl(networkServiceFactory)

    @Provides
    @Singleton
    fun provideNetworkServiceFactory() = NetworkServiceFactory()

}