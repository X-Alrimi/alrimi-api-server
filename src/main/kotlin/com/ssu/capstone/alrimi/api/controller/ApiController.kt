package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsDto
import com.ssu.capstone.alrimi.api.service.api.ApiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(private val apiService: ApiService) {

    @PostMapping("/crawler")
    fun getCrawledNews(@RequestBody crawledData: NewsCrawlerDto): MutableList<NewsDto> {
        var start = System.currentTimeMillis()
        val data = apiService.findKeyword(crawledData)
        println(System.currentTimeMillis() - start)
        return data
    }
}