package com.ssu.capstone.alrimi.api.repository.news

import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.model.news.News
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NewsRepository : JpaRepository<News, Long> {
    fun findAllByCompany(company: Company, pageable: Pageable): Page<News>
    fun findByLink(link: String): Optional<News>
    fun findAllByCompanyAndCriticalIsTrue(company: Company, pageable: Pageable): Page<News>
    fun findTop3ByCompanyAndCriticalIsTrueOrderByIdDesc(company: Company): List<News>
}