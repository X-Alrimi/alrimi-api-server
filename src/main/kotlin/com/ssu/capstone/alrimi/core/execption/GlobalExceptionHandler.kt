package com.ssu.capstone.alrimi.core.execption

import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(1)
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException::class)
    fun classNotFoundHandler(e: ApiException) : ErrorResponse {
        return ErrorResponse(e,ExceptionCode.COMPANY_001)
    }
}