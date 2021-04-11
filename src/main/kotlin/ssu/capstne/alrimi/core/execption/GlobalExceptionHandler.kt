package ssu.capstne.alrimi.core.execption

import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ssu.capstne.alrimi.api.service.company.exception.CompanyNotFoundException

@Order(1)
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException::class)
    fun classNotFoundHandler(e: ApiException) : ErrorResponse {
        return ErrorResponse(e,ExceptionCode.COMPANY_001)
    }
}