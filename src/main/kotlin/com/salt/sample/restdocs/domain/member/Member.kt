package com.salt.sample.restdocs.domain.member

import com.salt.sample.restdocs.domain.base.BaseEntity
import com.salt.sample.restdocs.domain.member.enum.MemberTypeCode
import com.salt.sample.restdocs.dto.member.request.MemberBody
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = Member.TABLE_NAME)
class Member(memberBody: MemberBody) : BaseEntity() {

    @Id
    var id: Long = memberBody.id
    val name: String? = memberBody.name
    val joinDate: LocalDate? = memberBody.joinDate
    val type: MemberTypeCode? = MemberTypeCode.MEMBER

    companion object {
        const val TABLE_NAME = "member_info"
    }
}