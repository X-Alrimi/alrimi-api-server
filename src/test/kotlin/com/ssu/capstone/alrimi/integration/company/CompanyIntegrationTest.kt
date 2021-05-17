package com.ssu.capstone.alrimi.integration.company

import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.integration.IntegrationTestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.get

class CompanyIntegrationTest : IntegrationTestBase() {

    private val testCompanyId: Long = 1
    private val invalidTestCompanyId: Long = 999

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var celebrityService: CelebrityService

    @Test
    @DisplayName("회사 리스트 가져오기 테스트 ")
    fun getCompanyListTest() {

        val result = companyService.getSimpleCompanyList()

        Assertions.assertFalse(result.isEmpty())
        mockMvc.get("/companies").andExpect {
            status { isOk }
        }.andExpect {
            jsonPath("data") { isNotEmpty }
            jsonPath("data[0]") { isNotEmpty }
            jsonPath("data[0].name") { value(result[0].name) }
        }
    }

    @Test
    @DisplayName("회사 세부정보 가져오기")
    fun getCompanyTest() {

        val companyResult = companyService.getCompany(testCompanyId)
        val celebrityResult = celebrityService.getCelebritiesList(testCompanyId)

        Assertions.assertNotNull(companyResult)
        Assertions.assertNotNull(celebrityResult)

        mockMvc.get("/companies/${testCompanyId}")
            .andExpect {
                status { isOk }
                jsonPath("data") { isNotEmpty }
                jsonPath("data.company") { isNotEmpty }
                jsonPath("data.celebrities") { isNotEmpty }
                jsonPath("data.company.id") { value(companyResult.id) }
                jsonPath("data.company.name") { value(companyResult.name) }
                jsonPath("data.celebrities[0].id") { value(celebrityResult[0].id) }
                jsonPath("data.celebrities[0].name") { value(celebrityResult[0].name) }
            }
    }

    @Test
    @DisplayName("존재하지 않는 회사 정보 요청시 테스트 실패")
    fun failedWhenRequestInvalidCompanyPK() {
        Assertions.assertThrows(CompanyNotFoundException::class.java) { companyService.getCompany(invalidTestCompanyId) }
        Assertions.assertThrows(CompanyNotFoundException::class.java) {
            celebrityService.getCelebritiesList(
                invalidTestCompanyId
            )
        }

        mockMvc.get("/companies/${invalidTestCompanyId}")
            .andExpect {
                status { isBadRequest }
                jsonPath("error") { isNotEmpty }
            }
    }

}