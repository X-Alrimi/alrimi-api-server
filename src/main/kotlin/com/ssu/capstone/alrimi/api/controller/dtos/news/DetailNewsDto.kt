package com.ssu.capstone.alrimi.api.controller.dtos.news

import org.hibernate.validator.constraints.URL
import org.springframework.format.annotation.DateTimeFormat
import javax.validation.constraints.NotBlank

data class DetailNewsDto(
    @field:NotBlank
    var title: String,
    @field:URL
    var link: String,
    @field:NotBlank
    var text: String,
    @field:DateTimeFormat(pattern = "yyyy-MM-dd-hh:mm")
    var createdAt: String
) {
}