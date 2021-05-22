package com.ssu.capstone.alrimi.unit.device

import com.ssu.capstone.alrimi.unit.UnitTestBase
import com.ssu.capstone.alrimi.common.factory.news.NewsFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DeviceUnitTest : UnitTestBase() {

    @BeforeEach
    fun initRedis() {
        Mockito.doReturn(listOperations)
                .`when`(redisTemplate).opsForList()
    }

    @Test
    @DisplayName("기존에 저장되어있는게 null 일경우에, 알람 가능")
    fun successWhenCacheIsNull() {

        Mockito.doReturn(false)
                .`when`(redisTemplate).hasKey(any(String::class.java))

        Mockito.doReturn(1L)
                .`when`(listOperations).rightPushAll(any(String::class.java), anyCollection(Double::class.java))

        Mockito.doReturn(true).`when`(redisTemplate).expire(Mockito.anyString(), Mockito.anyLong(), Mockito.any())

        var result: Boolean? = null
        Assertions.assertDoesNotThrow { result = deviceService.canAlarm(NewsFactory.getCriticalNewsDto()) }
        Assertions.assertNotNull(result)
        Assertions.assertEquals(true, result)
    }

    @Test
    @DisplayName("이미 저장되어있는데 5를 넘을 경우 실패")
    fun failedWhenSimilarityLevelIsUnder50() {
        Mockito.doReturn(true)
                .`when`(redisTemplate).hasKey(any(String::class.java))

        Mockito.doReturn(NewsFactory.similarityValues)
                .`when`(listOperations).range(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())

        var result: Boolean? = null

        Assertions.assertDoesNotThrow { result = deviceService.canAlarm(NewsFactory.getCriticalNewsDto()) }
        Assertions.assertNotNull(result)
        Assertions.assertEquals(true, result)

    }
}