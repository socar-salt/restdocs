package com.salt.sample.restdocs.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.salt.sample.restdocs.common.RestApiDocumentation.getDocumentRequest
import com.salt.sample.restdocs.common.RestApiDocumentation.getDocumentResponse
import com.salt.sample.restdocs.domain.member.enum.MemberTypeCode
import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.member.request.MemberBody
import com.salt.sample.restdocs.extension.maxLength
import com.salt.sample.restdocs.extension.remarks
import com.salt.sample.restdocs.service.MemberService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
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
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@WebMvcTest(MemberController::class)
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberControllerTest(
    private var mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {
    @MockBean
    lateinit var memberService: MemberService

    @Test
    fun `member-create`() {

        val memberId = 1L
        val memberBody = MemberBody(memberId, "salt", LocalDate.now(), MemberTypeCode.MEMBER)

        // given
        given(memberService.create(memberBody))
            .willReturn(memberId)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/member")
                .header("api-key", "salt12345aaa")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(memberBody))
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
                        headerWithName("api-key")
                            .description("API 키")
                    ),
                    responseFields(
                        fieldWithPath("code")
                            .type(JsonFieldType.STRING)
                            .description("응답 코드"),
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("응답 메세지"),
                        fieldWithPath("data")
                            .type(JsonFieldType.NUMBER)
                            .description("응답데이터").optional()
                    )
                )
            )
    }

    @Test
    fun `member-retrieval`() {

        val memberId = 1L
        val memberBody = MemberBody(memberId, "salt", LocalDate.now(), MemberTypeCode.MEMBER)

        // given
        given(memberService.retrieval(memberId))
            .willReturn(Member(memberBody))

        // when
        val resultAction = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/member/{memberId}", memberId)
                .header("api-key", "salt12345aaa")
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
                        headerWithName("api-key")
                            .description("API 키")
                    ),
                    pathParameters(
                        parameterWithName("memberId")
                            .description("회원번호")
                            .maxLength(11)
                            .remarks("회원번호를 찾을 수 없습니다.")
                    ),
                    responseFields(
                        fieldWithPath("code")
                            .type(JsonFieldType.NUMBER)
                            .description("응답 코드"),
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("응답 메세지"),
                        fieldWithPath("data")
                            .type(JsonFieldType.OBJECT)
                            .description("응답데이터").optional()
                    ).andWithPrefix("data.",
                        fieldWithPath("id")
                            .type(JsonFieldType.NUMBER)
                            .description("회원번호"),
                        fieldWithPath("name")
                            .type(JsonFieldType.STRING)
                            .description("이름"),
                        fieldWithPath("joinDate")
                            .type(JsonFieldType.STRING)
                            .description("가입일"),
                        fieldWithPath("createdAt")
                            .type(JsonFieldType.STRING)
                            .description("생성일"),
                        fieldWithPath("updatedAt")
                            .type(JsonFieldType.STRING)
                            .description("수정일")
                    )
                )
            )
    }

    @Test
    fun `member-update`() {

        val memberId = 1L
        val memberBody = MemberBody(memberId, "sugar", LocalDate.now(), MemberTypeCode.MEMBER)

        // given
        given(memberService.update(memberBody))
            .willReturn(memberId)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.put("/member/{memberId}", memberId)
                .header("api-key", "salt12345aaa")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberBody))
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
                        headerWithName("api-key")
                            .description("API 키")
                    ),
                    pathParameters(
                        parameterWithName("memberId")
                            .description("회원번호")
                            .maxLength(11)
                            .remarks("회원번호를 찾을 수 없습니다.")
                    ),
                    responseFields(
                        fieldWithPath("code")
                            .type(JsonFieldType.NUMBER)
                            .description("응답 코드"),
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("응답 메세지"),
                        fieldWithPath("data")
                            .type(JsonFieldType.NUMBER)
                            .description("응답데이터").optional()
                    )
                )
            )
    }

    @Test
    fun `member-delete`() {

        val memberId = 1L

        // given
        given(memberService.delete(memberId))
            .willReturn(memberId)

        // when
        val resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.delete("/member/{memberId}", memberId)
                .header("api-key", "salt12345aaa")
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
                        headerWithName("api-key")
                            .description("API 키")
                    ),
                    pathParameters(
                        parameterWithName("memberId")
                            .description("회원번호")
                            .maxLength(11)
                            .remarks("회원번호를 찾을 수 없습니다.")
                    ),
                    responseFields(
                        fieldWithPath("code")
                            .type(JsonFieldType.NUMBER)
                            .description("응답 코드"),
                        fieldWithPath("message")
                            .type(JsonFieldType.STRING)
                            .description("응답 메세지"),
                        fieldWithPath("data")
                            .type(JsonFieldType.NUMBER)
                            .description("응답데이터").optional()
                    )
                )
            )
    }
}