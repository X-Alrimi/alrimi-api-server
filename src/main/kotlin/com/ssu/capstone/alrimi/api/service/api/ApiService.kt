package com.ssu.capstone.alrimi.api.service.api

import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsDto

interface ApiService {
    fun findKeyword(crawledData: NewsCrawlerDto): MutableList<NewsDto>
}