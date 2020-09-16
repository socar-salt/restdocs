package com.salt.sample.restdocs.domain.base.enum

import java.util.EnumSet
import javax.persistence.AttributeConverter
import kotlin.reflect.KClass

open class BaseCodeEnumConverter<E, CT>(
    private val enumKClass: KClass<E>
) : AttributeConverter<E, CT> where E : Enum<E>, E : BaseCodeEnum<CT> {

    override fun convertToDatabaseColumn(attribute: E?): CT? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(dbData: CT?): E? {
        return ofCode(enumKClass.java, dbData)
    }

    companion object {
        private fun <E, CT> ofCode(enumClass: Class<E>, code: CT?): E? where E : Enum<E>, E : BaseCodeEnum<CT> {
            return code?.let {
                EnumSet.allOf(enumClass).find {
                    it.code == code
                }
            }
        }
    }
}
