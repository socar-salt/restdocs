package com.salt.sample.restdocs

import com.fasterxml.jackson.databind.ObjectMapper
import com.salt.sample.restdocs.controller.MemberController
import com.salt.sample.restdocs.docs.RestApiDocumentUtils.getDocumentRequest
import com.salt.sample.restdocs.docs.RestApiDocumentUtils.getDocumentResponse
import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.member.request.MemberCreateBody
import com.salt.sample.restdocs.service.MemberService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@WebMvcTest(MemberController::class)
@AutoConfigureRestDocs
class MemberTestController {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var memberService: MemberService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `restdocs ---- member-create`() {
        // given
        val memberBody = this.genMemberBody()
        val member = Member(memberBody)
        given(memberService.create(member)).willReturn(1L)

        // when
        val resultAction = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/member")
                .header("x-api-key", "API-KEY")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberBody))
        ).andDo(MockMvcResultHandlers.print())

        // then
        resultAction
            .andExpect(status().isOk)
            .andDo(
                document(
                    "member-create",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                        HeaderDocumentation
                            .headerWithName("x-api-key")
                            .description("Api Key")
                    ),
                    responseFields(*common())
                        .andWithPrefix("data.",
                            PayloadDocumentation
                                .fieldWithPath("id")
                                .type(JsonFieldType.NUMBER)
                                .description("MEMBER ID")
                        )
                )
            )
    }

    private fun genMemberBody(): MemberCreateBody {
        return MemberCreateBody(
                "salt",
                "2020-06-15"
        )
    }

    private fun common(): Array<FieldDescriptor> {
        return arrayOf(
            PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
            PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
            PayloadDocumentation.subsectionWithPath("error").type(JsonFieldType.OBJECT).description("에러 Data").optional(),
            PayloadDocumentation.subsectionWithPath("data").type(JsonFieldType.OBJECT).description("응답 Data").optional()
        )
    }
}
