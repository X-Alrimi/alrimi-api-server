package com.ssu.capstone.alrimi.api.service.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsDto
import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.model.news.News
import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.repository.news.NewsRepository
import com.ssu.capstone.alrimi.api.service.celebrity.exception.CelebrityNotFoundException
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewsServiceImpl(
    private val newsRepository: NewsRepository,
    private val celebrityRepository: CelebrityRepository,
    private val companyRepository: CompanyRepository
) : NewsService {

    override fun save(newsDto: NewsDto, companyDto: SimpleCompanyDto, celebrityDto: CelebrityInfoTransfer) {
        val celebrity: Celebrity =
            celebrityRepository.findById(celebrityDto.id).orElseThrow { CelebrityNotFoundException() }

        val company: Company =
            companyRepository.findById(companyDto.id).orElseThrow { CompanyNotFoundException() }

        newsRepository.save(News(
            title= newsDto.title,
            link = newsDto.link,
            createdAt = newsDto.createdAt,
            celebrity = celebrity,
            company = company
        ))
    }
}