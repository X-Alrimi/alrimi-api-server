package com.ssu.capstone.alrimi.api.service.device

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.core.event.AlarmEvent

interface DeviceService {
    fun saveToken(dto: TokenDto): Device
    fun deleteToken(dto: TokenDto): Boolean
    fun sendAlarm(event: AlarmEvent)
}