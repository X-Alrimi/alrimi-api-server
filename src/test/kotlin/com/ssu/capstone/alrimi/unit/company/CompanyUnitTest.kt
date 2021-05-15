package com.ssu.capstone.alrimi.unit.company

import com.ssu.capstone.alrimi.api.controller.dtos.company.SimpleCompanyDto
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.common.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import java.util.*

class CompanyUnitTest() : UnitTestBase() {

    @Test
    @DisplayName("모든 Company 리스트 리턴")
    fun getAllCompanyList() {
        BDDMockito.given(companyRepository.findAll())
            .willReturn(CompanyFactory.returnMockCompanyList())

        val result = companyService.getSimpleCompanyList()

        Assertions.assertNotNull(result)
        Assertions.assertFalse(result.isEmpty())
    }

    @Test
    @DisplayName("특정 Company 리턴")
    fun getCompany() {
        BDDMockito.given(companyRepository.findById(1))
            .willReturn(CompanyFactory.returnOptionalMockCompany())

        var company: Any? = null
        Assertions.assertDoesNotThrow { company = companyService.getCompany(1) }
        Assertions.assertTrue(company is SimpleCompanyDto)
    }

    @Test
    @DisplayName("존재하지않는 CompanyId 요청시 에러 리턴")
    fun failedWhenRequestInvalidCompanyId() {
        BDDMockito.given(companyRepository.findById(2))
            .willReturn(Optional.empty())

        Assertions.assertThrows(CompanyNotFoundException::class.java) { companyService.getCompany(2) }
    }


}