package com.salt.sample.restdocs.dto

import com.salt.sample.restdocs.domain.core.enum.ResponseCode

data class ApiResponse<T>(
        val code: ResponseCode,
        val message: String,
        val data: T?
) {
    companion object {
        fun success(): ApiResponse<Unit> = success(null)
        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(ResponseCode.OK, "OK", data)
    }
}
