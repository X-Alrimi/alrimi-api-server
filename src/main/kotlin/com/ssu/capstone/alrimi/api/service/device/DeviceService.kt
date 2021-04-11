package com.ssu.capstone.alrimi.api.service.device

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.repository.device.DeviceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional



@Service
@Transactional
class DeviceService(private val deviceRepository: DeviceRepository) {

    fun saveToken(dto: TokenDto): Device {
        return deviceRepository.save(Device(dto.token))
    }

    fun deleteToken(dto: TokenDto): Boolean {
        return deviceRepository.deleteByToken(dto.token)
    }
}