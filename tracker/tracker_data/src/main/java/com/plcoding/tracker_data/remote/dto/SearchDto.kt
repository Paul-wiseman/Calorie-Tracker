package com.plcoding.tracker_data.remote.dto

import com.plcoding.core.util.ApiResponsePayload

data class SearchDto(
    val products:List<Product>
): ApiResponsePayload
