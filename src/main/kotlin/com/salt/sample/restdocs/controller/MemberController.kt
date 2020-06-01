package com.salt.sample.restdocs.controller

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.ApiResponse
import com.salt.sample.restdocs.dto.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping
    fun createMember(@RequestBody memberBody: MemberBody): ApiResponse<Member> {
        return ApiResponse.success(memberService.create(Member(memberBody)))
    }

    @GetMapping("/{memberId}")
    fun retrievalMember(@PathVariable memberId: Long): ApiResponse<Member> {
        return ApiResponse.success(memberService.retrieval(memberId))
    }

    @PutMapping("/{memberId}")
    fun updateMember(
            @PathVariable memberId: Long,
            @RequestBody memberBody: MemberBody): ApiResponse<Member> {
        return ApiResponse.success(memberService.update(Member(memberBody)))
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable memberId: Long): ApiResponse<Long> {
        return ApiResponse.success(memberService.delete(memberId))
    }
}