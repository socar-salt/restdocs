package com.salt.sample.restdocs.common

import com.salt.sample.restdocs.common.request.RequestSnippet
import com.salt.sample.restdocs.extension.maxLength
import com.salt.sample.restdocs.extension.remarks
import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation

object CommonDocumentation {
    fun header(): Array<HeaderDescriptor> {
        return arrayOf(HeaderDocumentation.headerWithName("x-api-key").description("API 키"))
    }

    fun customRequestParameters(
        name: String,
        attributes: Map<String, Any>,
        vararg descriptors: ParameterDescriptor
    ): RequestSnippet {
        return RequestSnippet(name, mutableListOf(*descriptors), attributes, true)
    }

    fun getParametersWithName(name: String, description: String, maxlength: Int, remark: String): ParameterDescriptor {
        return RequestDocumentation.parameterWithName(name)
            .description(description)
            .maxLength(maxlength)
            .remarks(remark)
    }

    fun common(jsonFieldType: JsonFieldType): Array<FieldDescriptor> {
        return arrayOf(
            PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
            PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
            PayloadDocumentation.subsectionWithPath("data").type(jsonFieldType).description("응답 Data").optional()
        )
    }
}
