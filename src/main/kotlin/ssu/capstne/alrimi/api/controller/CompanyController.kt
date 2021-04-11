package ssu.capstne.alrimi.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ssu.capstne.alrimi.api.controller.dtos.company.DetailCompanyDto
import ssu.capstne.alrimi.api.controller.dtos.company.SimpleCompanyDto
import ssu.capstne.alrimi.api.service.celebrity.CelebrityService
import ssu.capstne.alrimi.api.service.company.CompanyService


@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService,
    private val celebrityService: CelebrityService
) {

    @GetMapping
    fun getCompanyList(): List<SimpleCompanyDto> {
        return companyService.getSimpleCompanyList()
    }

    @GetMapping("/{id}")
    fun getCompany(@PathVariable id: Long): DetailCompanyDto {
        return DetailCompanyDto(
            companyService.getDetailCompany(id),
            celebrityService.getCelebritiesList(id)
        )

    }
}