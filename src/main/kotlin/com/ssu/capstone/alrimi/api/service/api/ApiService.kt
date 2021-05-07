package com.ssu.capstone.alrimi.api.service.api

import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto

interface ApiService {
    fun findKeyword(crawledData: NewsCrawlerDto): MutableList<DetailNewsDto>
}