package ssu.capstne.alrimi.api.controller.response

class ErrorResponse<out ApiException>(
    val error: ApiException
)