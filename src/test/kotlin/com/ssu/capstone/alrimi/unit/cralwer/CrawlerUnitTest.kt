package com.ssu.capstone.alrimi.unit.cralwer

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.service.crawler.CrawlerServiceImpl
import com.ssu.capstone.alrimi.unit.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.celebrity.CelebrityFactory
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory
import com.ssu.capstone.alrimi.common.factory.news.NewsFactory
import com.ssu.capstone.alrimi.core.util.ngram.CustomNgramAnalyzer
import org.apache.lucene.analysis.ko.dict.UserDictionary
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.StringReader

class CrawlerUnitTest : UnitTestBase() {

    private lateinit var crawlerService: CrawlerServiceImpl

    @BeforeEach
    fun init() {
        val sb = StringBuilder()

        NewsFactory.getKeywords().forEach { sb.append(it).append("\n") }
        CustomNgramAnalyzer.createInstance(UserDictionary.open(StringReader(sb.toString())))
        crawlerService = CrawlerServiceImpl(companyService, celebrityService, newsService)
    }

    @Test
    @DisplayName("Ngram 정상 작동 확인")
    fun findKeywordTest() {
        Mockito.doReturn(listOf(SimpleCompanyDto(CompanyFactory.returnMockCompanyList()[0])))
                .`when`(companyService).getSimpleCompanyList()

        Mockito.doReturn(
                CelebrityFactory.makeMockCelebrityInfoTransfer(
                        CelebrityFactory.findAllByCompany_IdAndParentIdIsNullMockingFunction()
                )
        ).`when`(celebrityService).getCelebritiesList(1)
        Mockito.doNothing().`when`(newsService).save(
                any(SimpleCompanyDto::class.java),
                BDDMockito.anySet(),
                any(DetailNewsDto::class.java)
        )

        val crawlerNews = NewsFactory.getNewsCrawlerDto()
        var result: List<AlarmNewsDto>? = null

        Assertions.assertDoesNotThrow { result = crawlerService.findKeyword(crawlerNews) }
        Assertions.assertNotNull(result)
        Assertions.assertTrue(result?.size == 1)
    }
}