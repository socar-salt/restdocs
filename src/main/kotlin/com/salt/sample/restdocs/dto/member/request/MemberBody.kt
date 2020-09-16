package com.salt.sample.restdocs.dto.member.request

import com.salt.sample.restdocs.domain.member.enum.MemberTypeCode
import java.time.LocalDate

data class MemberBody (
        val id: Long,
        val name: String,
        val joinDate: LocalDate,
        val type: MemberTypeCode
)
