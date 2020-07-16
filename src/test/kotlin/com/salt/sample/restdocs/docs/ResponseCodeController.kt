package com.salt.sample.restdocs.docs

import com.salt.sample.restdocs.dto.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResponseCodeController {

  @GetMapping("/response-code")
  fun getResponseCode(): ApiResponse<Unit> {
    return ApiResponse.success()
  }
}