package com.ssu.capstone.alrimi.unit.celebrity

import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.common.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.celebrity.CelebrityFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito

class CelebrityUnitTest : UnitTestBase() {

    @Test
    @DisplayName("연예인 목록 리턴")
    fun getCelebritiesTest() {
        BDDMockito.given(celebrityRepository.findAllByCompany_IdAndParentIdIsNull(1)).willReturn(
            CelebrityFactory.makeMockCelebrityInfoTransfer(CelebrityFactory.findAllByCompany_IdAndParentIdIsNullMockingFunction())
        )

        val celebrities = celebrityService.getCelebritiesList(1)

        Assertions.assertNotNull(celebrities)
        Assertions.assertFalse(celebrities.isEmpty())

        Assertions.assertNotNull(celebrities[0])
        Assertions.assertNotNull(celebrities[0].member)
        Assertions.assertTrue(celebrities[0].member.size == 2)

        Assertions.assertNotNull(celebrities[1])
        Assertions.assertNotNull(celebrities[1].member)
        Assertions.assertTrue(celebrities[1].member.size == 2)
    }

    @Test
    @DisplayName("잘못된 회사 연예인 요청 시 에러 리턴")
    fun failWhenInvalidCompanyRequest() {
        BDDMockito.given(celebrityRepository.findAllByCompany_IdAndParentIdIsNull(2)).willReturn(listOf())
        Assertions.assertThrows(CompanyNotFoundException::class.java) { celebrityService.getCelebritiesList(2) }
    }
}