package com.salt.sample.restdocs

import com.fasterxml.jackson.databind.ObjectMapper
import com.salt.sample.restdocs.controller.MemberController
import com.salt.sample.restdocs.docs.RestApiDocumentUtils.getDocumentRequest
import com.salt.sample.restdocs.docs.RestApiDocumentUtils.getDocumentResponse
import com.salt.sample.restdocs.docs.maxLength
import com.salt.sample.restdocs.docs.remarks
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
import org.springframework.test.web.servlet.MockMvc
import org.mockito.ArgumentMatchers.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    fun `회원등록 ****************************** CreateMember`() {

        val requestBody = mapOf(
            "id" to 1L,
            "name" to "salt"
        )

        // given
        given(memberService.create(Member(MemberBody(1L, "salt"))))
            .willReturn(1L)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/member")
                .header("x-api-key", "salt12345aaa")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        ).andDo(MockMvcResultHandlers.print())

        // then
        resultActions
            .andExpect(status().isOk)
            .andDo(
                document(
                    "member-create",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                        headerWithName("x-api-key").description("salt12345aaa")
                    ),
                    responseFields(
                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("응답데이터").optional(),
                        fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러데이터").optional()
                    )
                )
            )
    }

    @Test
    fun `회원검색 ****************************** retrievalMember`() {

        // given
        given(memberService.retrieval((eq(1L))))
            .willReturn(Member(MemberBody(1L, "salt")))

        // when
        val resultAction = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/member/{memberId}", 1L)
                .header("x-api-key", "salt12345aaa")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())

        // then
        resultAction
            .andExpect(status().isOk)
            .andDo(
                document(
                    "member-retrieval",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                        headerWithName("x-api-key").description("salt12345aaa")
                    ),
                    pathParameters(
                        parameterWithName("memberId").description("회원번호").maxLength(11).remarks("회원번호를 찾을 수 없습니다.")
                    ),
                    responseFields(
                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답데이터").optional(),
                        fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러데이터").optional()
                    ).andWithPrefix("data.",
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("joinDate").type(JsonFieldType.STRING).description("가입일"),
                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성일"),
                        fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("수정일")
                    )
                )
            )
    }

    @Test
    fun `회원수정 ****************************** updateMember`() {

        val requestBody = mapOf(
            "id" to 1L,
            "name" to "sugar"
        )

        // given
        given(memberService.update(Member(MemberBody(1L, "sugar"))))
            .willReturn(1L)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.put("/member/{memberId}", 1)
                .header("x-api-key", "API_KEY")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        ).andDo(MockMvcResultHandlers.print())

        // then
        resultActions
            .andExpect(status().isOk)
            .andDo(
                document(
                    "member-update",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                        headerWithName("x-api-key").description("salt12345aaa")
                    ),
                    pathParameters(
                        parameterWithName("memberId").description("회원번호").maxLength(11).remarks("회원번호")
                    ),
                    responseFields(
                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("응답데이터").optional(),
                        fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러데이터").optional()
                    )
                )
            )
    }

    @Test
    fun `회원삭제 ****************************** deleteMember`() {

        // given
        given(memberService.delete(1L))
            .willReturn(1L)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.delete("/member/{memberId}", 1L)
                .header("x-api-key", "salt12345aaa")
                .accept(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())

        // then
        resultActions
            .andExpect(status().isOk)
            .andDo(
                document(
                    "member-delete",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                        headerWithName("x-api-key").description("salt12345aaa")
                    ),
                    pathParameters(
                        parameterWithName("memberId").description("회원번호").maxLength(11).remarks("회원번호를 찾을 수 없습니다.")
                    ),
                    responseFields(
                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("응답데이터").optional(),
                        fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러데이터").optional()
                    )
                )
            )
    }
}