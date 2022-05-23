package com.android.greencarot.repository

import com.android.greencarot.model.User
import com.android.greencarot.remote.NetworkServiceFactory
import com.android.greencarot.remote.Service
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val networkServiceFactory: NetworkServiceFactory
) : Repository {

    override suspend fun getName(index: Long): Flow<User?> {
        val mainService = networkServiceFactory.makeService(Service::class.java)
        return withContext(IO) {
            flow {
                emit(
                    try {
                        mainService.getName(index)
                    } catch (throwable: Throwable) {
                        null
                    }
                )

            }
        }
    }
}