package com.ssu.capstone.alrimi.api.repository.company

import com.ssu.capstone.alrimi.api.model.company.Company
import org.springframework.data.jpa.repository.JpaRepository


interface CompanyRepository : JpaRepository<Company, Long> {
    override fun findAll(): List<Company>
}