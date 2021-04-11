package ssu.capstne.alrimi.api.service.company

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ssu.capstne.alrimi.api.controller.dtos.company.SimpleCompanyDto
import ssu.capstne.alrimi.api.service.company.exception.CompanyNotFoundException
import ssu.capstne.alrimi.api.repository.company.CompanyRepository

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