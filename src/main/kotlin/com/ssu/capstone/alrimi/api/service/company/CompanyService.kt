package com.ssu.capstone.alrimi.api.service.company

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto

interface CompanyService {
    fun getSimpleCompanyList(): List<SimpleCompanyDto>
    fun getCompany(companyId: Long): SimpleCompanyDto
}