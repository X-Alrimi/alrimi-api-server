package com.ssu.capstone.alrimi.api.service.company

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class CompanyServiceImpl(private val companyRepository: CompanyRepository) : CompanyService {

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["company"], key = "#root.methodName")
    override fun getSimpleCompanyList(): List<SimpleCompanyDto> {
        return companyRepository.findAll().map { SimpleCompanyDto(it) }
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["company"], key = "#companyId")
    override fun getCompany(companyId: Long): SimpleCompanyDto {
        val company = companyRepository.findById(companyId).orElseThrow { CompanyNotFoundException() }
        return SimpleCompanyDto(company)
    }
}