package com.ssu.capstone.alrimi.api.service.api

import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsDto
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.news.NewsService
import com.ssu.capstone.alrimi.core.util.SearchUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ApiServiceImpl(
    private val companyService: CompanyService,
    private val celebrityService: CelebrityService,
    private val newsService: NewsService
) : ApiService {
    override fun findKeyword(crawledData: NewsCrawlerDto): MutableList<NewsDto> {
        var result: MutableList<NewsDto> = mutableListOf()
        val companies = companyService.getSimpleCompanyList()

        for (news in crawledData.news) {
            var flag = false
            for (company in companies) {
                val celebrities = celebrityService.getCelebritiesList(company.id)
                for (celebrity in celebrities) {
                    if (SearchUtil.findKeyword(news.text, celebrity.name)) {
                        newsService.save(news, company, celebrity)
                        flag = true
                    }
                }
            }
            if (flag)
                result.add(news)
        }
        return result
    }
}