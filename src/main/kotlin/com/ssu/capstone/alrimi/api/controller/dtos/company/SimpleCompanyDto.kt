package com.ssu.capstone.alrimi.api.controller.dtos.company

import com.ssu.capstone.alrimi.api.model.company.Company

class SimpleCompanyDto(company: Company) {
    val id: Long = company.id!!
    val name: String = company.name
}