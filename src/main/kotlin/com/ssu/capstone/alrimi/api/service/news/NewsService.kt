package com.ssu.capstone.alrimi.api.service.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.PagingNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.SimpleNewsDto
import com.ssu.capstone.alrimi.api.model.news.News
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

interface NewsService {
    fun save(companyDto: SimpleCompanyDto, celebritySet: Set<CelebrityInfoTransfer>, news: DetailNewsDto)
    fun getNewsFromCompany(companyId: Long, page: Int): PagingNewsDto
    fun getCriticalNewsFromCompany(companyId: Long, page: Int): PagingNewsDto
    fun changeNewsCritical(dto: List<CriticalNewsDto>): Boolean
    fun getLast3CriticalNews(companyId: Long): List<SimpleNewsDto>
}