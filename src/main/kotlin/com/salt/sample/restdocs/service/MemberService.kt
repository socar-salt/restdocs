package com.salt.sample.restdocs.service

import com.salt.sample.restdocs.domain.member.Member
import org.springframework.stereotype.Service

@Service
class MemberService {
    fun create(Member: Member): Member? = null
    fun update(Member: Member): Member? = null
    fun retrieval(MemberId: Long): Member? = null
    fun delete(MemberId: Long): Long? = null
}