package ssu.capstne.alrimi.api.repository.news

import org.springframework.data.jpa.repository.JpaRepository
import ssu.capstne.alrimi.api.model.news.News
import java.util.*

interface NewsRepository : JpaRepository<News, Long> {
    fun findTop4ByCompany_IdOrderByCreatedAtDesc(companyId: Long): List<News>
    fun deleteAllByCreatedAtBefore(date: Date)
}