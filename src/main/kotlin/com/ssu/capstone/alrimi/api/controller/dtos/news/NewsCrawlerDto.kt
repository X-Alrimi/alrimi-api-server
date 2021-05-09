package com.ssu.capstone.alrimi.api.controller.dtos.news

import javax.validation.Valid

data class NewsCrawlerDto(

    @field:Valid
    val news: List<DetailNewsDto>
)