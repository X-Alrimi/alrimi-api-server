package ssu.capstne.alrimi.api.controller.dtos.company

import ssu.capstne.alrimi.api.model.Company

open class SimpleCompanyDto(company: Company) {
    val id = company.id
    val name = company.name
    val evaluation = company.evaluation
}