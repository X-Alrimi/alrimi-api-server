package com.ssu.capstone.alrimi.unit.celebrity

import com.ssu.capstone.alrimi.common.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.CompanyFactory
import com.ssu.capstone.alrimi.common.factory.celebrity.CelebrityFactory
import org.junit.jupiter.api.*
import org.mockito.Mockito

class CelebrityUnitTest() : UnitTestBase() {


    @BeforeEach
    fun init() {
        Mockito.`when`(celebrityRepository.findAllByCompany_IdAndParentIdIsNull(Mockito.anyLong()))
            .thenReturn(
                CelebrityFactory.makeMockCelebrityInfoTransfer(CelebrityFactory.findAllByCompany_IdAndParentIdIsNullMockingFunction())
            )
    }

    @Test
    @DisplayName("연예인 목록 리턴")
    fun getCelebritiesTest() {
        Mockito.`when`(celebrityRepository.findAllByCompany_IdAndParentIdIsNull(Mockito.anyLong()))
            .thenReturn(
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
}