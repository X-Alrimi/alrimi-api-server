package ssu.capstne.alrimi.core.util

import ssu.capstne.alrimi.api.controller.response.ErrorResponse
import ssu.capstne.alrimi.api.controller.response.Response
import ssu.capstne.alrimi.api.execption.ApiException
import ssu.capstne.alrimi.api.execption.ExceptionCode

object ResponseUtil {
    fun<T> succeed(data: T): Response {
        return Response(data)
    }

    fun failed(error: ApiException, code: ExceptionCode): ErrorResponse {
        return ErrorResponse(error, code)
    }
}