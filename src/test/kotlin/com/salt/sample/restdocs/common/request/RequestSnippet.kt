package com.salt.sample.restdocs.common.request

import org.springframework.restdocs.operation.Operation
import org.springframework.restdocs.request.AbstractParametersSnippet
import org.springframework.restdocs.request.ParameterDescriptor

class RequestSnippet(
    name: String,
    descriptors: MutableList<ParameterDescriptor>,
    attributes: Map<String, Any>,
    ignoreUndocumentedFields: Boolean
) : AbstractParametersSnippet(name, descriptors, attributes, ignoreUndocumentedFields) {

    override fun verificationFailed(
        undocumentedParameters: MutableSet<String>?,
        missingParameters: MutableSet<String>?
    ) {
    }

    override fun extractActualParameters(operation: Operation): MutableSet<String> {
        val actualParameters: MutableSet<String> = HashSet()
        operation.request.parameters.forEach { k, _ -> actualParameters.add(k) }
        return actualParameters
    }
}
