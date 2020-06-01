package com.salt.sample.restdocs

import com.salt.sample.restdocs.controller.MemberController
import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDate

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@WebMvcTest(MemberController::class)
@AutoConfigureRestDocs
class MemberTestController(
        private val mockMvc: MockMvc
) {

    @MockBean
    lateinit var memberService: MemberService

    fun `rest docs ---------- createMember`() {

        // given
        val member = this.genMember()
        given(memberService.create(member)).willReturn(member)

        // when
        val resultAction = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/member")

        )
    }

    private fun genMember(): Member {
        return Member(MemberBody(
                "salt",
                LocalDate.now()
        ))
    }
}