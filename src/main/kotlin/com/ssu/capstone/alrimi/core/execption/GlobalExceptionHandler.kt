package com.ssu.capstone.alrimi.core.execption

import com.ssu.capstone.alrimi.api.service.celebrity.exception.CelebrityNotFoundException
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.api.service.device.exception.TokenNotExistException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@Order(1)
@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompanyNotFoundException::class)
    fun companyNotFoundHandler(e: ApiException): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.COMPANY_001)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CelebrityNotFoundException::class)
    fun celebrityNotFoundHandler(e: ApiException): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.CELEBRITY_001)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [MethodArgumentNotValidException::class, MethodArgumentTypeMismatchException::class]
    )
    fun methodArgumentNotValidExceptionHandler(e: Exception): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.SYSTEM_001)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPageException::class)
    fun invalidPageRequestHandler(e: ApiException): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.SYSTEM_002)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenNotExistException::class)
    fun tokenNotExistExceptionHandler(e: Exception): ErrorResponse {
        return ErrorResponse(e, ExceptionCode.DEVICE_001)
    }
}