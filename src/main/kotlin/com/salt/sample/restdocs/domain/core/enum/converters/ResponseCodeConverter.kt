package com.salt.sample.restdocs.domain.core.enum.converters

import com.salt.sample.restdocs.domain.base.enum.BaseCodeEnumConverter
import com.salt.sample.restdocs.domain.core.enum.ResponseCode
import com.salt.sample.restdocs.domain.member.enum.MemberTypeCode
import javax.persistence.Converter

@Converter(autoApply = true)
class ResponseCodeConverter : BaseCodeEnumConverter<ResponseCode, String>(ResponseCode::class)
