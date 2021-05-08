package com.ssu.capstone.alrimi.api.controller.dtos.news

import org.hibernate.validator.constraints.URL
import org.springframework.format.annotation.DateTimeFormat
import javax.validation.constraints.NotBlank

data class DetailNewsDto(
    @NotBlank
    var title: String,
    @URL
    var link: String,
    @NotBlank
    var text: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd-hh:mm")
    var createdAt: String
) {
}