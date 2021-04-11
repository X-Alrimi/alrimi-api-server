package com.ssu.capstone.alrimi.api.service.company

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class CompanyService(private val companyRepository: CompanyRepository) {

    @Transactional(readOnly = true)
    fun getSimpleCompanyList(): List<SimpleCompanyDto> {
        return companyRepository.findAll().map { SimpleCompanyDto(it) }
    }

    @Transactional(readOnly = true)
    fun getDetailCompany(id: Long): SimpleCompanyDto {
        val company = companyRepository.findById(id).orElseThrow { CompanyNotFoundException() }
        return SimpleCompanyDto(company)
    }
}