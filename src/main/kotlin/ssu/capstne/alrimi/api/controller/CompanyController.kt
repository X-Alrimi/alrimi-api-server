package ssu.capstne.alrimi.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ssu.capstne.alrimi.api.controller.dtos.company.DetailCompanyDto
import ssu.capstne.alrimi.api.controller.response.Response
import ssu.capstne.alrimi.api.service.CelebrityService
import ssu.capstne.alrimi.api.service.CompanyService


@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService,
    private val celebrityService: CelebrityService
) {

    @GetMapping
    fun getCompanyList(): Response {
        return Response(companyService.getSimpleCompanyList())
    }

    @GetMapping("/{id}")
    fun getCompany(@PathVariable id: Long): Response {
        return Response(
            DetailCompanyDto(
                companyService.getDetailCompany(id),
                celebrityService.getCelebritiesList(id)
            )
        )
    }
}