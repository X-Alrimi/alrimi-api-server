package com.ssu.capstone.alrimi.unit.news

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.model.news.News
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.common.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.celebrity.CelebrityFactory
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory
import com.ssu.capstone.alrimi.common.factory.news.NewsFactory
import com.ssu.capstone.alrimi.core.execption.InvalidPageException
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class NewsUnitTest : UnitTestBase() {

    @Test
    @DisplayName("뉴스 저장 테스트")
    fun saveNewsTest() {
        BDDMockito.given(celebrityRepository.findById(1))
            .willReturn(CelebrityFactory.createOptionalMockCelebrity(1, "MockCelebrity1"))

        BDDMockito.given(celebrityRepository.findById(2))
            .willReturn(CelebrityFactory.createOptionalMockCelebrity(2, "MockCelebrity2"))

        BDDMockito.given(newsRepository.save(BDDMockito.any()))
            .willReturn(null)

        BDDMockito.given(companyRepository.findById(1))
            .willReturn(CompanyFactory.returnOptionalMockCompany(1))

        val companyDto = SimpleCompanyDto(CompanyFactory.createMockCompany(1))
        val celebritiesSet: Set<CelebrityInfoTransfer> =
            CelebrityFactory.makeMockCelebrityInfoTransfer(
                listOf(
                    CelebrityFactory.createMockCelebrity(1, "MockCelebrity1"),
                    CelebrityFactory.createMockCelebrity(2, "MockCelebrity2")
                )
            ).toSet()
        val newsDto = NewsFactory.getNewsDto()

        Assertions.assertDoesNotThrow { newsService.save(companyDto, celebritiesSet, newsDto) }
    }

    @Test
    @DisplayName("회사별 뉴스 가져오기")
    fun getNewsFromCompanyTest() {

        val mockNews: Page<News> = Mockito.mock(Page::class.java) as Page<News>

        BDDMockito.given(companyRepository.findById(1))
            .willReturn(CompanyFactory.returnOptionalMockCompany(1))

        BDDMockito.given(
            newsRepository.findAllByCompany(
                any(Company::class.java),
                any(Pageable::class.java)
            )
        ).willReturn(mockNews)

        var result: Any? = null
        Assertions.assertDoesNotThrow { result = newsService.getNewsFromCompany(1, 1) }
        Assertions.assertNotNull(result)
    }

    @Test
    @DisplayName("페이지가 1보다작을 경우 에러리턴")
    fun failedWhenPageIsSmallerThan1() {
        Assertions.assertThrows(InvalidPageException::class.java) { newsService.getNewsFromCompany(1, -1) }
    }
}