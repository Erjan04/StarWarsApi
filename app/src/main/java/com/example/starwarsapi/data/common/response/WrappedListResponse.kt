package com.example.starwarsapi.data.common.response

data class WrappedListResponse<T>(
    var code: Int,
    var message: String,
    var errors: List<String>? = null,
    var data: List<T>? = null
)