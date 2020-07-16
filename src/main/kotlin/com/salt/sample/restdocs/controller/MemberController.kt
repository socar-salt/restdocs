package com.salt.sample.restdocs.controller

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.ApiResponse
import com.salt.sample.restdocs.dto.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping
    fun createMember(@RequestBody memberBody: MemberBody): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.create(Member(memberBody)))
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/{memberId}")
    fun retrievalMember(@PathVariable memberId: Long): ResponseEntity<ApiResponse<Member>> {
        val response = ApiResponse.success(memberService.retrieval(memberId))
        return ResponseEntity.ok().body(response)
    }

    @PutMapping("/{memberId}")
    fun updateMember(
            @PathVariable memberId: Long,
            @RequestBody memberBody: MemberBody): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.update(Member(memberBody)))
        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.delete(memberId))
        return ResponseEntity.ok().body(response)
    }
}