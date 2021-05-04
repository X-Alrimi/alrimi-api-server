package com.ssu.capstone.alrimi.core.execption

import com.ssu.capstone.alrimi.api.service.celebrity.exception.CelebrityNotFoundException
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(1)
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException::class)
    fun companyNotFoundHandler(e: ApiException): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.COMPANY_001)
    }

    @ExceptionHandler(CelebrityNotFoundException::class)
    fun celebrityNotFoundHandler(e: ApiException): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.CELEBRITY_001)
    }
}