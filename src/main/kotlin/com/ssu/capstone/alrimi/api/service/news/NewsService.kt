package com.ssu.capstone.alrimi.api.service.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsDto
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

interface NewsService {
    fun save(newsDto: NewsDto, company: SimpleCompanyDto, celebrity: CelebrityInfoTransfer)
}