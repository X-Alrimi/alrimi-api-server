package com.ssu.capstone.alrimi.unit.device

import com.ssu.capstone.alrimi.common.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory
import com.ssu.capstone.alrimi.core.util.common.DateUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import java.util.*

class DeviceUnitTest : UnitTestBase() {
    @Test
    @DisplayName("null 일경우 알람 가능")
    fun canAlarmWhenRecentAlarmIsNull() {
        BDDMockito.given(companyRepository.findByName("MockingCompany1"))
            .willReturn(CompanyFactory.returnOptionalMockCompany(1))

        var result: Boolean? = null
        Assertions.assertDoesNotThrow { result = deviceService.canAlarm("MockingCompany1") }
        Assertions.assertNotNull(result)
        result?.let { Assertions.assertTrue(it) }
    }

    @Test
    @DisplayName("하루 전일 경우 알람 가능")
    fun canAlarmWhenRecentAlarmIsPastMoreThanOneDay() {
        BDDMockito.given(companyRepository.findByName("MockingCompany1"))
            .willReturn(CompanyFactory.returnOptionalMockCompany(1, "2021-05-14-17:30"))

        var result: Boolean? = null
        Assertions.assertDoesNotThrow { result = deviceService.canAlarm("MockingCompany1") }
        Assertions.assertNotNull(result)
        result?.let { Assertions.assertTrue(it) }
    }


    @Test
    @DisplayName("하루 이전일 경우 알람 불가능")
    fun failedAlarmWhenRecentAlarmIsPastSmallerThanOneDay() {
        BDDMockito.given(companyRepository.findByName("MockingCompany1"))
            .willReturn(CompanyFactory.returnOptionalMockCompany(1, DateUtil.getStringFromDate(Date())))

        var result: Boolean? = null
        Assertions.assertDoesNotThrow { result = deviceService.canAlarm("MockingCompany1") }
        Assertions.assertNotNull(result)
        result?.let { Assertions.assertFalse(it) }
    }
}