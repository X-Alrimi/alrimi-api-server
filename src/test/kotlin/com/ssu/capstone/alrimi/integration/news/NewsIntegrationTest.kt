package com.ssu.capstone.alrimi.integration.news

import com.ssu.capstone.alrimi.api.controller.dtos.news.PagingNewsDto
import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.model.news.News
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.api.service.news.NewsService
import com.ssu.capstone.alrimi.core.execption.InvalidPageException
import com.ssu.capstone.alrimi.integration.IntegrationTestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.get

class NewsIntegrationTest : IntegrationTestBase() {

    private val testCompanyId: Long = 1
    private val invalidTestCompanyId: Long = 999

    private val testPage: Int = 1
    private val invalidTestPage: Int = -1

    @Autowired
    private lateinit var newsService: NewsService


    @BeforeEach
    fun saveNews() {
        var company: Company? = null
        Assertions.assertDoesNotThrow {
            company = companyRepository.findById(testCompanyId).orElseThrow { CompanyNotFoundException() }
        }
        newsRepository.save(News(null, "testNews", "https://testnews.com", "2021-05-17-17:50", company!!, listOf()))
    }

    @Test
    @DisplayName("회사와 연관된 뉴스 가져오기")
    fun getNewsTest() {
        var result: PagingNewsDto? = null
        Assertions.assertDoesNotThrow { result = newsService.getNewsFromCompany(testCompanyId, testPage) }
        Assertions.assertNotNull(result)
        Assertions.assertTrue(result!!.currentPage == 1)
        Assertions.assertFalse(result!!.news.isEmpty())

        mockMvc.get("/news?companyId=${testCompanyId}&page=${testPage}")
            .andExpect {
                status { isOk }
                jsonPath("data") { isNotEmpty }
                jsonPath("data.currentPage") { value(result!!.currentPage) }
                jsonPath("data.news[0].title") { value(result!!.news[0].title) }
                jsonPath("data.news[0].createdAt") { value(result!!.news[0].createdAt) }
                jsonPath("data.news[0].link") {
                    value(result!!.news[0].link)
                }
            }
    }

    @Test
    @DisplayName("존재하지 않는 회사의 뉴스 요청시 실패")
    fun failedWhenRequestInvalidCompanyPk() {
        Assertions.assertThrows(CompanyNotFoundException::class.java) {
            newsService.getNewsFromCompany(
                invalidTestCompanyId,
                testPage
            )
        }

        mockMvc.get("/news?companyId=${invalidTestCompanyId}&page=${testPage}")
            .andExpect {
                status { isBadRequest }
                jsonPath("error") { isNotEmpty }
                jsonPath("error.code") { value("COMPANY_001") }
            }
    }

    @Test
    @DisplayName("잘못된 뉴스 페이징 요청시 실패")
    fun failedWhenRequestInvalidPage() {
        Assertions.assertThrows(InvalidPageException::class.java) {
            newsService.getNewsFromCompany(
                testCompanyId,
                invalidTestPage
            )
        }

        mockMvc.get("/news?companyId=${testCompanyId}&page=${invalidTestPage}")
            .andExpect {
                status { isBadRequest }
                jsonPath("error") { isNotEmpty }
                jsonPath("error.code") { value("SYSTEM_002") }
            }
    }
}