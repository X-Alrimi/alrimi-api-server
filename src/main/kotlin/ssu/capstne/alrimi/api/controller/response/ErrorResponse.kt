package ssu.capstne.alrimi.api.controller.response

import ssu.capstne.alrimi.api.execption.ApiException
import ssu.capstne.alrimi.api.execption.ExceptionCode
import java.time.LocalDate

class ErrorResponse(exception: ApiException,code: ExceptionCode) {
    val message = exception.message
    val code = code.name
    val timestamp = LocalDate.now()
}