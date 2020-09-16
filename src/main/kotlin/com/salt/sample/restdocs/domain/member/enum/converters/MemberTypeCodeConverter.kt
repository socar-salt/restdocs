package com.salt.sample.restdocs.domain.member.enum.converters

import com.salt.sample.restdocs.domain.base.enum.BaseCodeEnumConverter
import com.salt.sample.restdocs.domain.member.enum.MemberTypeCode
import javax.persistence.Converter

@Converter(autoApply = true)
class MemberTypeCodeConverter : BaseCodeEnumConverter<MemberTypeCode, String>(MemberTypeCode::class)
