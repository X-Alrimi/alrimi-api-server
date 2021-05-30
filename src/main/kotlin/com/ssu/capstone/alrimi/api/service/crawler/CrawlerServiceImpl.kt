package com.ssu.capstone.alrimi.api.service.crawler

import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.api.repository.news.NewsRepository
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.news.NewsService
import com.ssu.capstone.alrimi.api.service.news.exception.NewsNotExistException
import com.ssu.capstone.alrimi.core.util.ngram.CustomNgramAnalyzer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CrawlerServiceImpl(
        private val companyService: CompanyService,
        private val celebrityService: CelebrityService,
        private val newsService: NewsService,
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

    override fun findCritical(news: List<AlarmNewsDto>): List<CriticalNewsDto> {
        return news.map {
            CriticalNewsDto(it, true, listOf(-2.4610e-01,  2.4282e-01,  2.5895e-01,  1.3177e+00, -1.1293e-01,
                1.0996e-01,  8.1748e-02,  5.2254e-01,  6.6422e-01,  1.2092e-01,
                -2.2132e-01,  1.5167e-01,  1.7642e-01, -1.1240e-02,  6.4417e-02,
                -3.3777e-01, -1.0129e-02,  1.4072e-01,  1.2872e-01,  1.6204e-01,
                4.5689e-01, -2.6001e-01,  9.4104e-03,  1.9810e-01, -6.6586e-01,
                -4.1969e-01,  6.2763e-02, -6.6222e-01, -1.0791e-01, -7.6977e-01,
                1.2562e-02,  7.4288e-01, -1.6359e-01,  4.6118e-01,  5.5412e-02))
        }
    }
}