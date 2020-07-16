package com.salt.sample.restdocs.dto

import java.time.LocalDate

data class MemberBody (
        val id: Long,
        val name: String,
        val joinDate: LocalDate? = LocalDate.now()
)