package com.ssu.capstone.alrimi.api.controller.response

import com.ssu.capstone.alrimi.core.execption.ErrorResponse
import org.springframework.core.MethodParameter
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@Order(2)
@RestControllerAdvice
class GlobalResponseHandler : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any {
        return if (body!! is ErrorResponse)
            CommonErrorResponse(body)
        else if (request.uri.path.contains("swagger") || request.uri.path.contains("api-docs"))
            body
        else
            CommonResponse(body)
    }
}