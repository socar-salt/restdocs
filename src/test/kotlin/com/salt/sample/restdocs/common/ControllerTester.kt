package com.salt.sample.restdocs.common

import com.salt.sample.restdocs.common.CommonDocumentation.customRequestParameters
import com.salt.sample.restdocs.common.RestApiDocumentation.getDocumentRequest
import com.salt.sample.restdocs.common.RestApiDocumentation.getDocumentResponse
import com.salt.sample.restdocs.extension.remarks
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.snippet.Attributes
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ControllerTester(
    private val mockMvc: MockMvc,
    private val properties: Array<TestProperty> = arrayOf()
) {
    private var resultActions: ResultActions? = null
    private val apiKey = "salt1234aa"

    fun get(path: String) {
        val request = RestDocumentationRequestBuilders
            .get(path)
            .header("api-key", apiKey)
            .accept(MediaType.APPLICATION_JSON)

        properties.forEach {
            request.queryParam(it.code, it.dummy)
        }

        resultActions = mockMvc
            .perform(request)
            .andDo(MockMvcResultHandlers.print())
    }

    fun post(path: String, postParameters: String) {
        resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(path)
                .header("api-key", apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postParameters)
        ).andDo(MockMvcResultHandlers.print())
    }

    fun put(path: String, pathVariable: Long, postParameters: String) {
        resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.put(path, pathVariable)
                        .header("api-key", apiKey)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postParameters)
        ).andDo(MockMvcResultHandlers.print())
    }

    fun delete(path: String, pathVariable: Long) {
        resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.delete(path, pathVariable)
                        .header("api-key", apiKey)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
    }

    fun makeDocument(snippetTitle: String, expectFieldType: JsonFieldType) {
        resultActions
            ?.andExpect(MockMvcResultMatchers.status().isOk)
            ?.andDo(
                MockMvcRestDocumentation.document(
                    snippetTitle,
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(*CommonDocumentation.header()),
                    customRequestParameters(
                        "name-parameters", // {snippetName}-fields.snippet 이라는 파일명으로 생성
                        Attributes.attributes(Attributes.key("title").value("요청 파라미터")),
                        *this.getParametersWithName()
                    ),
                    responseFields(*CommonDocumentation.common(expectFieldType))
                )
            )
    }

    private fun getParametersWithName(): Array<ParameterDescriptor> {
        val documents = ArrayList<ParameterDescriptor>()

        properties.forEach {
            documents.add(
                RequestDocumentation
                    .parameterWithName(it.code)
                    .description(it.description)
                    .remarks("${it.description} 값을 찾을 수 없습니다.")
                    .optional()
            )
        }

        return documents.toArray(
            arrayOfNulls<ParameterDescriptor>(properties.size)
        )
    }
}
