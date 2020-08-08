package com.salt.sample.restdocs.dto.member.request

import java.time.LocalDate

data class MemberBody (
        val id: Long,
        val name: String,
        val joinDate: LocalDate
)