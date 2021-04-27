package com.ssu.capstone.alrimi.api.service.device

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device

interface DeviceService {
    fun saveToken(dto: TokenDto): Device
    fun deleteToken(dto: TokenDto): Boolean
}