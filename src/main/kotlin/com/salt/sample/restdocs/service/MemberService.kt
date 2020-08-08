package com.salt.sample.restdocs.service

import com.salt.sample.restdocs.domain.member.Member
import org.springframework.stereotype.Service

@Service
class MemberService {
    fun create(Member: Member): Long? = 1L
    fun update(Member: Member): Long? = 1L
    fun retrieval(MemberId: Long): Member? = null
    fun delete(MemberId: Long): Long? = 1L
}