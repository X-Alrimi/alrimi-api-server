package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.service.api.ApiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ApiController(private val apiService: ApiService) {

    @PostMapping("/crawler")
    fun getCrawledNews(@RequestBody @Valid crawledData: NewsCrawlerDto): MutableList<DetailNewsDto> {
        return apiService.findKeyword(crawledData)
    }
}