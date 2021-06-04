package com.ssu.capstone.alrimi.api.service.crawler

import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto

interface CrawlerService {
    fun findKeyword(crawledData: NewsCrawlerDto): List<AlarmNewsDto>
    fun findCritical(news: List<AlarmNewsDto>): List<CriticalNewsDto>?
}