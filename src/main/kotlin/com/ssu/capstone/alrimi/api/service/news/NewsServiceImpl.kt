package com.ssu.capstone.alrimi.api.service.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.PagingNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.SimpleNewsDto
import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.model.news.News
import com.ssu.capstone.alrimi.api.repository.PageUtil
import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.repository.news.NewsRepository
import com.ssu.capstone.alrimi.api.service.celebrity.exception.CelebrityNotFoundException
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.api.service.news.exception.NewsNotExistException
import com.ssu.capstone.alrimi.core.execption.InvalidPageException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewsServiceImpl(
        private val newsRepository: NewsRepository,
        private val companyRepository: CompanyRepository,
        private val celebrityRepository: CelebrityRepository
) : NewsService {

    override fun save(companyDto: SimpleCompanyDto, celebritySet: Set<CelebrityInfoTransfer>, news: DetailNewsDto) {
        val company: Company =
                companyRepository.findById(companyDto.id).orElseThrow { CompanyNotFoundException() }
        val celebrityList: MutableList<Celebrity> = mutableListOf()

        celebritySet.forEach { celebrity ->
            celebrityList.add(
                    celebrityRepository.findById(celebrity.id).orElseThrow { CelebrityNotFoundException() })
        }
        newsRepository.save(
                News(
                        title = news.title,
                        link = news.link,
                        createdAt = news.createdAt,
                        company = company,
                        critical = false,
                        celebrities = celebrityList
                )
        )
    }

    override fun getNewsFromCompany(companyId: Long, page: Int): PagingNewsDto {
        if (page < 1)
            throw InvalidPageException()

        val company: Company = companyRepository.findById(companyId).orElseThrow { CompanyNotFoundException() }
        val newsList = newsRepository.findAllByCompany(company, PageUtil(page - 1))

        return PagingNewsDto(newsList.content.map { SimpleNewsDto(it) }, newsList.number + 1, newsList.totalPages)
    }

    override fun getCriticalNewsFromCompany(companyId: Long, page: Int): PagingNewsDto {
        if (page < 1)
            throw InvalidPageException()
        val company: Company = companyRepository.findById(companyId).orElseThrow { CompanyNotFoundException() }
        val newsList = newsRepository.findAllByCompanyAndCriticalIsTrue(company, PageUtil(page - 1))
        return PagingNewsDto(newsList.content.map { SimpleNewsDto(it) }, newsList.number + 1, newsList.totalPages)
    }

    override fun changeNewsCritical(dto: List<CriticalNewsDto>): Boolean {
        dto.forEach {
            val news = newsRepository.findByLink(it.news.link).orElseThrow { NewsNotExistException() }
            news.critical = true
        }
        return true
    }


}