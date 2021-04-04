package ssu.capstne.alrimi.api.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import ssu.capstne.alrimi.api.model.entity.News

interface NewsRepository : JpaRepository<News, Long> {
    fun findTop4ByCompany_IdOrderByCreatedAtDesc(companyId: Long): List<News>
}