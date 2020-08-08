package com.salt.sample.restdocs.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.salt.sample.restdocs.common.ControllerTester
import com.salt.sample.restdocs.common.TestProperty
import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.member.request.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.context.TestConstructor
import java.time.LocalDate

@WebMvcTest(MemberController::class)
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberControllerTestV2(
    private var mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    lateinit var memberService: MemberService

    @Test
    fun `member-create-V2`() {

        val requestBody = MemberBody(1L, "salt", LocalDate.now())


        // given
        given(memberService.create(Member(requestBody))).willReturn(1L)

        // when
        val dummy = arrayOf(
            TestProperty("id", "회원번호", requestBody.id.toString()),
            TestProperty("name", "이름", requestBody.name),
            TestProperty("joinDate", "가입일", requestBody.joinDate.toString())
        )
        val tester = ControllerTester(mockMvc, dummy)
        tester.post("/member", objectMapper.writeValueAsString(requestBody))

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
        val requestBody = MemberBody(memberId, "sugar", LocalDate.now())
        val dummy = arrayOf(
            TestProperty("id", requestBody.id.toString()),
            TestProperty("name", requestBody.name)
        )
        val tester = ControllerTester(mockMvc, dummy)

        // given
        given(memberService.update(Member(requestBody))).willReturn(memberId)

        // when
        tester.put("/member/$memberId", memberId, objectMapper.writeValueAsString(requestBody))

        // then
        tester.makeDocument("member-update", JsonFieldType.NUMBER)

    }

    @Test
    fun `member-delete-V2`() {
        val memberId = 1L
        val tester = ControllerTester(mockMvc)

        // given
        given(memberService.delete(memberId)).willReturn(1L)

        // when
        tester.delete("/member/$memberId", memberId)

        // then
        tester.makeDocument("member-delete", JsonFieldType.NUMBER)
    }
}