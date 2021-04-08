package ssu.capstne.alrimi.api.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import ssu.capstne.alrimi.api.model.entity.Company

interface CompanyRepository : JpaRepository<Company, Long> {
    override fun findAll(): List<Company>
}