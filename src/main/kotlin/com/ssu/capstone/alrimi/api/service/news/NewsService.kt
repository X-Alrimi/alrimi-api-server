package com.ssu.capstone.alrimi.api.service.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.SimpleNewsDto
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

interface NewsService {
    fun save(companyDto: SimpleCompanyDto, celebritySet: Set<CelebrityInfoTransfer>, news: DetailNewsDto)
    fun getNewsFromCompany(companyId: Long): List<SimpleNewsDto>
}