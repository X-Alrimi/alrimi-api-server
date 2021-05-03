package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.company.DetailCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService,
    private val celebrityService: CelebrityService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("회사리스트 가져오기")
    fun getCompanyList(): List<SimpleCompanyDto> {
        return companyService.getSimpleCompanyList()
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("회사 세부정보 가져오기")
    fun getCompany(@PathVariable id: Long): DetailCompanyDto {
        return DetailCompanyDto(
            companyService.getCompany(id),
            celebrityService.getCelebritiesList(id)
        )
    }
}