package com.plcoding.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

 abstract class RepositorySource<UiPayload : UiState, EventPayload : ApiResponsePayload> :
    Source<UiPayload> {

    abstract suspend fun getFlow(): Flow<EventPayload>
    abstract suspend fun map(eventPayload: EventPayload): UiPayload
    abstract fun trace(eventPayload: EventPayload)

    override suspend fun produce(): Flow<UiPayload> = getFlow().onEach(::trace)
        .map(::map)
}