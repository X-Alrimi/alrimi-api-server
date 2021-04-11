package ssu.capstne.alrimi.api.repository.company

import org.springframework.data.jpa.repository.JpaRepository
import ssu.capstne.alrimi.api.model.company.Company

interface CompanyRepository : JpaRepository<Company, Long> {
    override fun findAll(): List<Company>
}