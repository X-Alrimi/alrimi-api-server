package com.ssu.capstone.alrimi.api.controller.dtos.token

import javax.validation.constraints.NotEmpty

class TokenDto(
    @field:NotEmpty
    val token: String
)