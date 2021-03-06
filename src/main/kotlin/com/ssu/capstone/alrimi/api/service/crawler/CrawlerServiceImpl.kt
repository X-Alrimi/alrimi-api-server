package com.ssu.capstone.alrimi.api.service.crawler

import com.fasterxml.jackson.databind.ObjectMapper
import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.news.NewsService
import com.ssu.capstone.alrimi.core.util.ngram.CustomNgramAnalyzer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class CrawlerServiceImpl(
    private val companyService: CompanyService,
    private val celebrityService: CelebrityService,
    private val newsService: NewsService,
    @Value("\${machine.url}")
    private val mlServerUrl: String,
    private val restTemplate: RestTemplate
) : CrawlerService {
    override fun findKeyword(crawledData: NewsCrawlerDto): List<AlarmNewsDto> {
        val result: MutableList<AlarmNewsDto> = mutableListOf()
        val companies = companyService.getSimpleCompanyList()

        for (news in crawledData.news) {
            val nGramList = CustomNgramAnalyzer.getNgram(news.text)
            var flag = false
            for (company in companies) {
                val celebritySet = hashSetOf<CelebrityInfoTransfer>()
                val celebrities = celebrityService.getCelebritiesList(company.id)
                for (celebrity in celebrities) {
                    nGramList.forEach { keyword ->
                        if (keyword == celebrity.name) {
                            flag = true
                            celebritySet.add(celebrity)
                        }
                    }
                }
                if (flag) {
                    result.add(AlarmNewsDto(company.name, news))
                    newsService.save(company, celebritySet, news)
                }
            }
        }
        return result
    }

    override fun findCritical(news: List<AlarmNewsDto>): List<CriticalNewsDto>? {
        return try {
            val returnValue = restTemplate.postForObject(
                mlServerUrl,
                news,
                Array<CriticalNewsDto>::class.java
            )
            returnValue?.asList()
        } catch (e: Exception) {
            null
        }
    }
}