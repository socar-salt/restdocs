package com.salt.sample.restdocs.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.salt.sample.restdocs.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(controllers = [
    MemberController::class
])
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
open class BaseControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var memberService: MemberService
}
