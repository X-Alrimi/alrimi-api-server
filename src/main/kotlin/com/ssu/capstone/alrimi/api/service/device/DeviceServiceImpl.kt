package com.ssu.capstone.alrimi.api.service.device

import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.repository.device.DeviceRepository
import com.ssu.capstone.alrimi.core.event.AlarmEvent
import com.ssu.capstone.alrimi.core.util.common.AlarmUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository,
    ) : DeviceService {
    override fun saveToken(dto: TokenDto): Device {
        return deviceRepository.save(Device(dto.token))
    }

    override fun deleteToken(dto: TokenDto): Boolean {
        return deviceRepository.deleteByToken(dto.token)
    }

    override fun sendAlarm(event: AlarmEvent) {
        val devices = deviceRepository.findAll()
        devices.forEach { device ->
            try {
                val message = Message.builder()
                    .setToken(device.token)
                    .setNotification(Notification(AlarmUtil.getMessageTitle(event.company), event.title))
                    .putData("link", event.link)
                    .build()
                FirebaseMessaging.getInstance(FirebaseApp.getInstance("X-Alrimi")).send(message)
            } catch (e: FirebaseMessagingException) {
                e.printStackTrace()
            }
        }
    }
}