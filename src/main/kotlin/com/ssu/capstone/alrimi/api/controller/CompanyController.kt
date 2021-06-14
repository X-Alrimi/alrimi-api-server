package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.company.DetailCompanyDto
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.news.NewsService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService,
    private val newsService: NewsService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("회사리스트 가져오기")
    fun getCompanyList(): List<DetailCompanyDto> {
        return companyService.getSimpleCompanyList()
            .map { DetailCompanyDto(it, newsService.getLast3CriticalNews(it.id)) }
    }

}