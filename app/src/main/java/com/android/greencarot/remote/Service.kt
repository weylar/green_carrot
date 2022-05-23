package com.android.greencarot.remote

import com.android.greencarot.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("name")
    suspend fun getName(
        @Query("index") index: Long
    ): User
}