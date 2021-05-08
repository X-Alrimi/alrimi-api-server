package com.ssu.capstone.alrimi.api.controller.dtos.token

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class TokenDto(
    @NotEmpty
    val token: String
)