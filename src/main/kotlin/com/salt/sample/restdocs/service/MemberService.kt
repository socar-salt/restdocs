package com.salt.sample.restdocs.service

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.member.request.MemberBody
import org.springframework.stereotype.Service

@Service
class MemberService {
    fun create(memberBody: MemberBody): Long = 0
    fun update(memberBody: MemberBody): Long = 0
    fun retrieval(MemberId: Long): Member? = null
    fun delete(MemberId: Long): Long = 0
}
