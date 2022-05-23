package com.android.greencarot.repository

import com.android.greencarot.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getName(index: Long): Flow<User?>
}