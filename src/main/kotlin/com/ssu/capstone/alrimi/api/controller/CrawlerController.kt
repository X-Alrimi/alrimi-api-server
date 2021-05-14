package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.AlarmNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.service.crawler.CrawlerService
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.core.event.AlarmEvent
import io.swagger.annotations.ApiOperation
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/crawler")
class CrawlerController(
    private val crawlerService: CrawlerService,
    private val deviceService: DeviceService,
    private val eventPublisher: ApplicationEventPublisher
) {

    @PostMapping
    @ApiOperation("크롤링한 뉴스 데이터 전달 받는 API")
    fun getCrawledNews(@RequestBody @Valid crawledData: NewsCrawlerDto): MutableList<AlarmNewsDto> {
        val keywordList = crawlerService.findKeyword(crawledData)
        val keywordAndCriticalList =
            keywordList.stream().filter { news -> crawlerService.findCritical(news) }.collect(Collectors.toList())

        keywordAndCriticalList.filter { deviceService.canAlarm(it.company) }.forEach { news ->
            eventPublisher.publishEvent(
                AlarmEvent(
                    news.company,
                    news.news.title,
                    news.news.link
                )
            )
        }
        return keywordAndCriticalList
    }
}