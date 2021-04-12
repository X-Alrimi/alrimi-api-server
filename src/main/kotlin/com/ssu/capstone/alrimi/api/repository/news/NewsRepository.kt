package com.ssu.capstone.alrimi.api.repository.news

import com.ssu.capstone.alrimi.api.model.news.News
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NewsRepository : JpaRepository<News, Long> {
    fun findTop4ByCompany_IdOrderByCreatedAtDesc(companyId: Long): List<News>
    fun deleteAllByCreatedAtBefore(date: Date)
}