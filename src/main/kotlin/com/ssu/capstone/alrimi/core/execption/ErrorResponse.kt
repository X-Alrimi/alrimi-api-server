package com.ssu.capstone.alrimi.core.execption

import java.util.*

class ErrorResponse(exception: Exception, code: ExceptionCode) {
    val message = exception.message
    val code = code.name
    val timestamp = Date()
}