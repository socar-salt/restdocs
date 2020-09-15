package com.salt.sample.restdocs.controller


import com.salt.sample.restdocs.common.ControllerTester
import com.salt.sample.restdocs.common.TestProperty
import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.member.request.MemberBody
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.restdocs.payload.JsonFieldType
import java.time.LocalDate

class MemberControllerTestV2: BaseControllerTest() {

    @Test
    fun `member-create-V2`() {

        val memberId = 1L
        val memberBody = MemberBody(memberId, "salt", LocalDate.now())

        // given
        given(memberService.create(memberBody)).willReturn(memberId)

        // when
        val dummy = arrayOf(
            TestProperty("id", "회원번호", memberBody.id.toString()),
            TestProperty("name", "이름", memberBody.name),
            TestProperty("joinDate", "가입일", memberBody.joinDate.toString())
        )
        val tester = ControllerTester(mockMvc, dummy)
        tester.post("/member", objectMapper.writeValueAsString(memberBody))

        // then
        tester.makeDocument("member-create", JsonFieldType.NUMBER)
    }

    @Test
    fun `member-retrieval-V2`() {

        val memberId = 1L
        val tester = ControllerTester(mockMvc, arrayOf())

        // given
        given(memberService.retrieval(memberId)).willReturn(Member(MemberBody(memberId, "salt", LocalDate.now())))

        // when
        tester.get("/member/$memberId")

        // then
        tester.makeDocument("member-retrieval", JsonFieldType.OBJECT)
    }

    @Test
    fun `member-update-V2`() {

        val memberId = 1L
        val memberBody = MemberBody(memberId, "sugar", LocalDate.now())
        val dummy = arrayOf(
            TestProperty("id", memberBody.id.toString()),
            TestProperty("name", memberBody.name)
        )
        val tester = ControllerTester(mockMvc, dummy)

        // given
        given(memberService.update(memberBody)).willReturn(memberId)

        // when
        tester.put("/member/$memberId", memberId, objectMapper.writeValueAsString(memberBody))

        // then
        tester.makeDocument("member-update", JsonFieldType.NUMBER)

    }

    @Test
    fun `member-delete-V2`() {
        val memberId = 1L
        val tester = ControllerTester(mockMvc)

        // given
        given(memberService.delete(memberId)).willReturn(memberId)

        // when
        tester.delete("/member/$memberId", memberId)

        // then
        tester.makeDocument("member-delete", JsonFieldType.NUMBER)
    }
}