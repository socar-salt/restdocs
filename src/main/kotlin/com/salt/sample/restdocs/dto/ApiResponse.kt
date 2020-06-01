package com.salt.sample.restdocs.dto

data class ApiResponse<T>(
        val code: Int,
        val message: String,
        val data: T?,
        val error: T?
) {
    companion object {
        fun success(): ApiResponse<Unit> = success(null)
        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(200, "OK", data, null)
        fun <T> error(error: T?): ApiResponse<T> = ApiResponse(500, "Server Error", null, error)
    }
}