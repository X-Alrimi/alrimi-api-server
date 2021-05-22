package com.ssu.capstone.alrimi.integration.crawler

import com.fasterxml.jackson.databind.ObjectMapper
import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.service.crawler.CrawlerService
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.common.factory.news.NewsFactory
import com.ssu.capstone.alrimi.core.event.AlarmEvent
import com.ssu.capstone.alrimi.integration.IntegrationTestBase
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CrawlerIntegrationTest : IntegrationTestBase() {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Mock
    lateinit var crawlerService: CrawlerService

    @Mock
    lateinit var deviceService: DeviceService

    @Autowired
    lateinit var eventPublisher: ApplicationEventPublisher


    @Test
    @Disabled
    @DisplayName("정상적인 크롤링을통한 로직")
    fun getCrawledNewsTest() {
        /*
        Mockito.doReturn(true)
            .`when`(crawlerService).findCritical(any(AlarmNewsDto::class.java))
        */
        Mockito.doNothing()
            .`when`(deviceService).sendAlarm(any(AlarmEvent::class.java))


        mockMvc.perform(
            post("/crawler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(NewsFactory.getNewsCrawlerDto()))
        )
            .andExpect { status().isOk }
    }

}