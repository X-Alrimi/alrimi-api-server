package com.ssu.capstone.alrimi.api.service.device

import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.ssu.capstone.alrimi.api.controller.dtos.news.CriticalNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.token.KeywordDto
import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.repository.device.DeviceRepository
import com.ssu.capstone.alrimi.api.service.company.exception.CompanyNotFoundException
import com.ssu.capstone.alrimi.api.service.device.exception.TokenNotExistException
import com.ssu.capstone.alrimi.core.event.AlarmEvent
import com.ssu.capstone.alrimi.core.util.common.AlarmUtil
import com.ssu.capstone.alrimi.core.util.common.SimilarityUtil
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit


@Service
@Transactional
class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository,
    private val companyRepository: CompanyRepository,
    private val redisTemplate: RedisTemplate<String, Double>

) : DeviceService {
    /**
     * device 토큰 저장
     */
    override fun saveToken(dto: TokenDto): Device {
        return deviceRepository.save(Device(dto.token))
    }


    /**
     * device 토큰 삭제
     */
    override fun deleteToken(dto: TokenDto): Boolean {
        val token = deviceRepository.findById(dto.token).orElseThrow { TokenNotExistException() }
        deviceRepository.delete(token)
        return true
    }

    override fun sendAlarm(event: AlarmEvent) {
        val company = companyRepository.findByName(event.company).orElseThrow { CompanyNotFoundException() }
        try {
            if (company.devices.size > 0) {
                val multicast = MulticastMessage.builder().addAllTokens(company.devices.map { device -> device.token })
                    .setNotification(Notification(AlarmUtil.getMessageTitle(event.company), event.title))
                    .putData("link", event.link)
                    .build()
                FirebaseMessaging.getInstance(FirebaseApp.getInstance("X-Alrimi")).sendMulticast(multicast).successCount
            }
        } catch (e: FirebaseMessagingException) {
            e.printStackTrace()
        }
    }

    /**
     * device별 키워드 저장
     */
    override fun addKeyword(keywordDto: KeywordDto): Boolean {
        val device = deviceRepository.findById(keywordDto.token).orElseThrow { TokenNotExistException() }
        val company = companyRepository.findByName(keywordDto.keyword).orElseGet { null }
        company?.let {
            if (!company.devices.contains(device))
                company.devices.add(device)
        }
        return true
    }

    /**
     * device별 키워드 삭제
     */
    override fun deleteKeyword(keywordDto: KeywordDto): Boolean {
        val device = deviceRepository.findById(keywordDto.token).orElseThrow { TokenNotExistException() }
        val company = companyRepository.findByName(keywordDto.keyword).orElseGet { null }
        company?.let { company.devices.remove(device) }
        return true
    }

    /**
     * 최근 알람이 하루 이전이면 알람 발송(x)
     */
    override fun canAlarm(dto: CriticalNewsDto): Boolean {
        var flag: Boolean = false
        val listOperation: ListOperations<String, Double> = redisTemplate.opsForList()
        if (redisTemplate.hasKey(dto.news.company)) {
            val recentAlarmSimilarity: List<Double> =
                listOperation.range(dto.news.company, 0, -1) as List<Double>
            val similarityResult = SimilarityUtil.calculateSimilarity(recentAlarmSimilarity, dto.similarity)

            if (similarityResult <= SimilarityUtil.ALARM_SIMILARITY_LEVEL) {
                redisTemplate.delete(dto.news.company)
                flag = true
            }
        } else
            flag = true

        if (flag) {
            redisTemplate.expire(dto.news.company, 3, TimeUnit.DAYS)
            listOperation.rightPushAll(dto.news.company, dto.similarity)
        }
        return flag
    }

    override fun getKeyword(dto: TokenDto): List<String> {
        val device = deviceRepository.findById(dto.token).orElseThrow { TokenNotExistException() }
        return device.companies.map { it.name }
    }
}