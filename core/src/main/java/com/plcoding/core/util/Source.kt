package com.plcoding.core.util

import kotlinx.coroutines.flow.Flow

interface Source<T: UiState> {
    suspend fun produce():Flow<T>
}