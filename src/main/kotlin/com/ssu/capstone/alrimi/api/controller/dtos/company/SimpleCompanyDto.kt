package com.ssu.capstone.alrimi.api.controller.dtos.company

import com.ssu.capstone.alrimi.api.model.company.Company

open class SimpleCompanyDto(company: Company) {
    val id: Long = company.id!!
    val name: String = company.name
    val evaluation: Int = company.evaluation
}