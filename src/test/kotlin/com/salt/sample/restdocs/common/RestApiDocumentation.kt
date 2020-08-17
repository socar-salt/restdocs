package com.salt.sample.restdocs.common

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris

object RestApiDocumentation {

  fun getDocumentRequest(): OperationRequestPreprocessor {
    return Preprocessors.preprocessRequest(
            modifyUris()
              .scheme("http")
              .host("salt.dev")
              .port(8085)
              .removePort(),
            Preprocessors.prettyPrint()
    )
  }

  fun getDocumentResponse(): OperationResponsePreprocessor {
    return Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
  }
}