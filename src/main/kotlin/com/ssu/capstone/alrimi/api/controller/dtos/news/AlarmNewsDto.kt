package com.ssu.capstone.alrimi.api.controller.dtos.news

class AlarmNewsDto(val company: String, val title: String, val link: String, val text: String, val createdAt: String) {

    constructor(company: String, dto: DetailNewsDto) :
            this(company, dto.title, dto.link, dto.text, dto.createdAt)
}