package com.salt.sample.restdocs.domain.member

import com.salt.sample.restdocs.domain.base.BaseEntity
import com.salt.sample.restdocs.dto.MemberBody
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = Member.TABLE_NAME)
class Member(memberBody: MemberBody) : BaseEntity() {

    val name: String? = memberBody.name
    val joinDate: LocalDate? = memberBody.joinDate

    companion object {
        const val TABLE_NAME = "member"
    }
}