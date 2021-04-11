package ssu.capstne.alrimi.api.service.device

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ssu.capstne.alrimi.api.controller.dtos.token.TokenDto
import ssu.capstne.alrimi.api.model.device.Device
import ssu.capstne.alrimi.api.repository.device.DeviceRepository

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