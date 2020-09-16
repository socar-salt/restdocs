package com.salt.sample.restdocs.domain.member.enum

import com.salt.sample.restdocs.domain.base.enum.BaseCodeEnum

enum class MemberTypeCode(
    override val code: String,
    override val description: String
) : BaseCodeEnum<String> {
    MEMBER("MEMBER", "일반회원"),
    ADMIN("ADMIN", "관리자"),
}
