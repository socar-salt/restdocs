package com.salt.sample.restdocs.dto.member.request

import java.time.LocalDate

data class MemberCreateBody (
        val name: String,
        val joinDate: LocalDate? = LocalDate.now()
)
