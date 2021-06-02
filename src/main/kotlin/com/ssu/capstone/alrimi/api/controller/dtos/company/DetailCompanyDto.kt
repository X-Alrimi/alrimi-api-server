package com.ssu.capstone.alrimi.api.controller.dtos.company

import com.ssu.capstone.alrimi.api.controller.dtos.news.SimpleNewsDto


class DetailCompanyDto(
    val company: SimpleCompanyDto,
    val news : List<SimpleNewsDto>
) {

}