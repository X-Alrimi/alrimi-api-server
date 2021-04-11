package com.ssu.capstone.alrimi.api.controller.dtos.company

import com.ssu.capstone.alrimi.api.model.company.Company

open class SimpleCompanyDto(company: Company) {
    val id = company.id
    val name = company.name
    val evaluation = company.evaluation
}