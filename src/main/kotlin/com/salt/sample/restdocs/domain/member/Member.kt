package com.salt.sample.restdocs.domain.member

import com.salt.sample.restdocs.domain.base.BaseEntity
import com.salt.sample.restdocs.dto.member.request.MemberCreateBody
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = Member.TABLE_NAME)
class Member(memberCreateBody: MemberCreateBody) : BaseEntity() {

    val name: String? = memberCreateBody.name
    val joinDate: LocalDate? = LocalDate.parse(memberCreateBody.joinDate, java.time.format.DateTimeFormatter.ISO_DATE)

    companion object {
        const val TABLE_NAME = "member"
    }
}