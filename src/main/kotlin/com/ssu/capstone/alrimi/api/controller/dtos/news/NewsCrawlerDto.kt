package com.ssu.capstone.alrimi.api.controller.dtos.news

import javax.validation.constraints.NotEmpty

data class NewsCrawlerDto(
    @NotEmpty
    val news: List<DetailNewsDto>
)