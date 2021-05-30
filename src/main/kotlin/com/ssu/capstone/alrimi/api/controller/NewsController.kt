package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.PagingNewsDto
import com.ssu.capstone.alrimi.api.service.news.NewsService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/news")
class NewsController(private val newsService: NewsService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("연관된 회사 관련 뉴스 가져오기")
    fun getNews(@RequestParam companyId: Long, @RequestParam page: Int): PagingNewsDto {
        return newsService.getNewsFromCompany(companyId, page)
    }

    @GetMapping("/critial")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("연관된 회사 관련 크리티컬 뉴스 가져오기")
    fun getCriticalNews(@RequestParam companyId: Long, @RequestParam page: Int): PagingNewsDto {
        return newsService.getCriticalNewsFromCompany(companyId, page)
    }
}