package com.ssu.capstone.alrimi.core.execption

import java.util.*

class ErrorResponse(exception: ApiException, code: ExceptionCode) {
    val message = exception.message
    val code = code.name
    val timestamp = Date()
}