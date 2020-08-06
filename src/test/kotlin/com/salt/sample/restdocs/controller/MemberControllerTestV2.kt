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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.context.TestConstructor

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@WebMvcTest(MemberController::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberTestControllerV2 {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var memberService: MemberService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `****************************** CreateMember V2`() {

        val requestBody = MemberBody(1L, "salt")
        val dummy = arrayOf(
            TestProperty("id", requestBody.id.toString()),
            TestProperty("name", requestBody.name)
        )
        val tester = ControllerTester(mockMvc, dummy)

        // given
        given(memberService.create(Member(requestBody))).willReturn(1L)

        // when
        tester.post("/member", objectMapper.writeValueAsString(requestBody))

        // then
        tester.makeDocument("member-create", JsonFieldType.NUMBER)
    }

    @Test
    fun `****************************** retrievalMember V2`() {

        val memberId = 1L
        val tester = ControllerTester(mockMvc, arrayOf())

        // given
        given(memberService.retrieval(memberId)).willReturn(Member(MemberBody(memberId, "salt")))

        // when
        tester.get("/member/$memberId")

        // then
        tester.makeDocument("member-retrieval", JsonFieldType.OBJECT)
    }

    @Test
    fun `****************************** updateMember V2`() {

        val memberId = 1L
        val requestBody = MemberBody(memberId, "sugar")
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
    fun `****************************** deleteMember V2`() {
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