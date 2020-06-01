package com.salt.sample.restdocs.domain.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now()

}