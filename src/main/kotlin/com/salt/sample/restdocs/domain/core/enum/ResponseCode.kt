package com.salt.sample.restdocs.domain.core.enum

import com.salt.sample.restdocs.domain.base.enum.BaseCodeEnum

enum class ResponseCode(
    override val code: String,
    override val description: String
) : BaseCodeEnum<String> {
    OK("200", "OK"),
    INVALID_PARAMETER("400", "Parameter validation error"),
    UNAUTHORIZED_USER("401", "Unauthorized api key."),
    INTERNAL_SERVER_ERROR("500", "Internal server error")
}
