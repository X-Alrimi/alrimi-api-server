package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.api.service.crawler.CrawlerService
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.core.event.AlarmEvent
import io.swagger.annotations.ApiOperation
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/crawler")
class CrawlerController(
    private val crawlerService: CrawlerService,
    private val deviceService: DeviceService,
    private val eventPublisher: ApplicationEventPublisher
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("크롤링한 뉴스 데이터 전달 받는 API")
    fun getCrawledNews(@RequestBody @Valid crawledData: NewsCrawlerDto) {
        val keywordList = crawlerService.findKeyword(crawledData)
        val criticalList: List<CriticalNewsDto> = crawlerService.findCritical(keywordList)

        criticalList.filter { deviceService.canAlarm(it) }.forEach { alarmNews ->
            eventPublisher.publishEvent(
                AlarmEvent(
                    alarmNews.news.company,
                    alarmNews.news.title,
                    alarmNews.news.link
                )
            )
        }
    }
}