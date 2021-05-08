package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.PagingNewsDto
import com.ssu.capstone.alrimi.api.service.news.NewsService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/news")
class NewsController(private val newsService: NewsService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getNews(@RequestParam companyId: Long, @RequestParam page: Int): PagingNewsDto {
        return newsService.getNewsFromCompany(companyId, page)
    }
}