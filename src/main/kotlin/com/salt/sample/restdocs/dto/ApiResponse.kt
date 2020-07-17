package com.salt.sample.restdocs.dto

data class ApiResponse<T>(
        val code: Int,
        val message: String,
        val data: T?
) {
    companion object {
        fun success(): ApiResponse<Unit> = success(null)
        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(200, "OK", data)
    }
}