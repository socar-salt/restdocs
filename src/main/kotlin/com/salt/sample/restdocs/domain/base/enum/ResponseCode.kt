package com.salt.sample.restdocs.domain.base.enum

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ResponseCode(
        val code: Int,
        val message: String
) {
    OK(200, "OK"),
    INVALID_PARAMETER(400, "Parameter validation error"),
    UNAUTHORIZED_USER(401, "Unauthorized api key."),
    INTERNAL_SERVER_ERROR(500, "Internal server error")
}