package com.ssu.capstone.alrimi.api.controller.dtos.token

import javax.validation.constraints.NotEmpty

class KeywordDto(
    @field:NotEmpty
    val token: String,
    @field:NotEmpty
    val keyword: String
) {
}