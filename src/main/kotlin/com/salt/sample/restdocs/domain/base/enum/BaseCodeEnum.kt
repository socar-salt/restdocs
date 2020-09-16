package com.salt.sample.restdocs.domain.base.enum

/**
 * Table 에 단순 코드값 (ex: 1,2 or A,B) 등으로 등록되어 있는 컬럼을 enum 화 할때 구현한다.
 * [base.BaseCodeEnumConverter] 컨버터 처리를 해줘야하므로 구현이 꼭 필요하다.
 * @param CT code 데이터 타입
 * @author salt
 * @since 2020.03.04
 */
interface BaseCodeEnum<CT> {
    val code: CT
    val description: String
}

fun <T, CT> T?.toCode(): BaseCodeEnumDesc where T : Enum<*>, T : BaseCodeEnum<CT> {
    return this
        ?.let { BaseCodeEnumDesc(it.name, it.description) }
        ?: BaseCodeEnumDesc("", "")
}
